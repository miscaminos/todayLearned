console.log('*****Reply Module..........')
var replyService = (function(){
	
	function remove(rnum, callback, error){
		$.ajax({
			type:"delete",
			url:"./reply/"+rnum,
			success:function(result, status,xhr){
				if(callback){
					callback(result)
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		})//ajax end
	}//remove end
	
	function update(reply, callback,error){
		console.log("rnum:"+reply.rnum);
		
		$.ajax({
			type:'put',
			url:'./reply/'+reply.rnum,
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		})//ajax end
	}//updat end
	
	function add(reply,callback,error){
		console.log("add reply..............");
		
		$.ajax({
			type:'post',
			url:'./reply/create',
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result)
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er)
				}
			}
		});
	}
	
	function get(rnum,callback,error){
		//alert("호출")
		$.get("./reply/"+rnum+"", function(result){
			//alert("result:"+result)
			if(callback){
				callback(result)
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function getList(param,callback){
		var contentsno = param.contentsno;
		var sno = param.sno;
		var eno = param.eno;
		//alert(param.contentsno);
		$.getJSON("./reply/list/"+contentsno+"/"+sno+"/"+eno+"",
				function(data){
					//alert(data);
					if(callback){					
						callback(data);
					}
			     }
		);
	}
	
	function getPage(param,callback, error){
		$.ajax({
			type:'get',
			url:'./reply/page',
			data:param,
			contentType : "applicaton/text;charset=utf-8",
			success : function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	;
	
	return {
		getList:getList,
		getPage:getPage,
		add:add,
		get:get,
		update:update,
		remove:remove
	};
	
})();//function()호출(실행)





