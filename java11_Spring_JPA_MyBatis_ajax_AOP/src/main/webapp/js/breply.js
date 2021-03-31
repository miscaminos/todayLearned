console.log('*******Reply Module........')
//breply.js는 ajax 구동을 위해 server에서 댓글을 가져오는 방법을 선언한다

//replyService에 함수 호출을 할당했기때문에. 함수 실행(getList + getPage의 선언)되고 return값을 replyService가 받음.
//실행이되면 함수에서 return되는 getList, getPage JSON 객체가 replyService안으로 들어가는것임.
var replyService = (function() {
	
	function remove(rnum, callback, error){
		$.ajax({
			type:"delete",
			url: "./reply/"+rnum,
			success: function(result, status, xhr){
				if(callback){
					callback(result)
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			} //error end
		}) //ajax end
	} //remove end

	function update(reply, callback, error) {
		console.log("rnum: " + reply.rnum);
		$.ajax({
			type: 'put',
			url: './reply/' + reply.rnum,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		}) // ajax end
	} //update end

	function add(reply, callback, error) {
		console.log("add reply...............");
		$.ajax({
			type: 'post',
			url: './reply/create',
			//stringify: 매개변수로 보낼때 문자열로 바꾸어서 보낸다.
			data: JSON.stringify(reply),
			contentType: "application/json;chars=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	function get(rnum, callback, error) {
		$.get("./reply/" + rnum + ".json", function(result) {
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}

	//결과를 받아오기
	function getList(param, callback) {
		var bbsno = param.bbsno;
		var sno = param.sno;
		var eno = param.eno;
		alert(param.bbsno);
		//jquery method getJSON(): server에게 data를 요청 (ReplyController를 호출)
		//경로상(url)에 prameter를 담아서 요청. 이 요청 url이 (ReplyController)RestController에 표기되어야함 
		$.getJSON("./reply/list/" + bbsno + "/" + sno + "/" + eno + ".json",
			//data: getJSON가 url로 요청해서 server에게서 받은 데이터를 data라고 하면 됨.
			//getJSON() 함수의 첫번째 매개변수가 url. param에서 받은 bbsno, sno, eno를 넣어서 url을 완성
			function(data) {
				alert(data);
				//if(callback): ""callback 함수가 정의가 되어있다면""을 뜻함!
				if (callback) {
					//callback함수를 실행
					callback(data);
				}
			}
		);
	}

	//위에 getJSON이기때문에 type:get
	function getPage(param, callback, error) {
		//jquery method ajax()
		$.ajax({
			type: 'get',
			//이 요청 url이 (ReplyController)RestController에 표기되어야함
			url: './reply/page',
			data: param,
			contentType: "application/text;charset=utf-8",
			//success이면 아래 function을 실행한다.
			success: function(result, status, xhr) {
				if (callback) {
					callback(result)
				}
			},
			//error라면 아래 function을 실행한다.
			error: function(xhr, status, er) {
				if (error) {
					error(er)
				}
			}
		});
	}
	;//위 함수들과, return값을 구분하기 위해 ; 넣는다

	return {
		//이름: 값 (e.g., getPage(문자열,label):getPage(위에서 define한 값(value) 즉 getPage함수))
		//즉, 각 label로 함수 이름이 반환되는것임
		getList: getList,
		getPage: getPage,
		add: add,
		get: get,
		update: update,
		remove: remove
	};

})(); //function() 호출(실행), 선언만 하는게 아니라 호출한는거임!
//선언된 함수들을 갖고있는것인 실행된것임. 자체 내용이 다 실행된것은 아니다. 아직!!

