//$표기로 page loading시 바로 시작하도록 function 선언
$(function() {
	showList()
});//page loading function end

//read.jsp에서 class="chat"으로 지정된 ul 태그를 replyUL에 넣는다 
var replyUL = $(".chat");
function showList() {
	//replyService의 getList라는 이름의 함수를 접근, 불러서 getList를 호출한것임.
	//breply.js에서 정의했던 getList()의 첫번째 param에 전달되는 것:
	replyService.getList({ bbsno: bbsno, sno: sno, eno: eno },
		//param에 bbsno, sno, eno 넘겨주는것
		//getList()의 두번째 param: callback에 전달되는 것:
		//list 매개변수가 data가 되는 것임.
		function(list) {
			var str = "";
			if (list == null || list.length == 0) {
				//list없거나 비어있으면 그냥 나가.
				return;
			}
			for (var i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='list-group-item' data-rnum='" + list[i].rnum + "'>";
				str += "<div><div class='header'><strong class='primary-font'>" + list[i].id + "</strong>";
				str += "<small class='pull-right text-muted'>" + list[i].regdate + "</small></div>";
				str += replaceAll(list[i].content, '\n', '<br>') + "</div></li>";
			}
			//.html(): jquery함수 (innerHTML와 동일함)
			//즉, read.jsp의 ul태그안에 html내용을 str로 채운다.
			replyUL.html(str);

			showReplyPage();
		} //function end
	); //getList end
}//showList end



function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

var replyPageFooter = $(".panel-footer");

var param = "nPage=" + nPage;
param += "&nowPage=" + nowPage;
param += "&bbsno=" + bbsno;
param += "&col=" + colx;
param += "&word=" + wordx;

function showReplyPage() {
	//param에 nPage, nowPage, colx, wordx를 넣어서 getPage의 첫번째 매개변수로 전달
	replyService.getPage(param, 
		function(paging) {
			console.log(paging);
			var str = "<div><small class='text-muted'>"+paging+"</small></div>";
			console.log(str);
			replyPageFooter.html(str);
	})
}//showReplyPage end

var modal = $(".modal");
var modalInputContent = modal.find("textarea[name='content']");
var modalModBtn = $("#modalModBtn");
var modalRemoveBtn = $("#modalRemoveBtn");
var modalRegisterBtn = $("#modalRegisterBtn");

$("#modalCloseBtn").on("click", function(e) {
	modal.modal('hide');
});

$("#addReplyBtn").on("click", function(e) {
	
	if(session_id==""){ //로그인이 안된 상태라면
		if(confirm('로그인해야 댓글을 쓸 수 있습니다.')){ //confirm에는 확인,취소 선택가능. (확인=true, 취소=false)
			var url = "../member/login"; // ../의 의미: 현 bbs폴더에서 상위 경로로 간다(member 폴더로 가기위해)
			url += "?col="+colx; //?앞에까지만 url
			url +="&word="+wordx; //&로 여러 parameter를 연결
			url +="&nowPage="+nowPage;
			url +="&nPage="+nPage;
			url +="&bbsno="+bbsno;
			url +="&rurl=../bbs/read"; //rurl(reply url):가려는 url을 지정하는 parameter 
			location.href = url; //
		}//confirm end
	}else{
		modalInputContent.val("");
		modal.find("button[id != 'modalCloseBtn']").hide();
		modalRegisterBtn.show();
	
		$(".modal").modal("show");
	} //session_id end
}); //addReplyBtn on end

modalRegisterBtn.on("click", function(e) {
	if (modalInputContent.val() == '') {
		alert("댓글을 입력하세요.");
		return;
	}
	var reply = {
		content: modalInputContent.val(),
		id: session_id,
		bbsno: bbsno
	};

	//비동기 통신 요청 (add function 호출) 하면서 reply에 3개의 JSON객체를 담아서 전달
	replyService.add(reply, function(result) {
		modal.find("input").val("");
		modal.modal("hide");

		showList();

	}); //add end

}) //button click end

//내가 chat class의 li를 클릭했을대때 실행
$(".chat").on("click", "li", function(e) {
	var rnum = $(this).data("rnum");
	replyService.get(rnum, function(reply) {
		modalInputContent.val(reply.content);
		modal.data("rnum", reply.rnum);
		modal.find("button[id != 'modalCloseBtn']").hide()
		
		if(session_id==reply.id){
			modalModBtn.show();
			modalRemoveBtn.show();			
		}
		modal.modal("show");
	})//get end
}) //.chat click end


modalModBtn.on("click", function(e) {
	var reply = {
		rnum: modal.data("rnum"),
		content: modalInputContent.val()
	};
	replyService.update(reply, function(result) {
		modal.modal("hide");
		showList();
	}); //update end
}); //modalModBtn on end

modalRemoveBtn.on("click", function(e){
	var rnum = modal.data("rnum");
	replyService.remove(rnum, function(result){
		modal.modal("hide");
		showList();
	}); //remove end
}); //modalRemoveBtn on end 
