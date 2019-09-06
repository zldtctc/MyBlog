$(function(){
	$("input[name='id']").hide();
});
$(".contain-ul").children().click(function(){
	$(".contain-ul").children().attr("style",false);
	$(this).attr("style","list-style-type: disc; list-style-position: inside; background-color: #2c3e50");
});

$(".contact-btn").click(function(){
	$.ajax({
		"url":"users/modifyMsg",
		"data":$(".contact-form").serialize(), 
		"type":"get",
		"dataType":"json",
		"success":function(result){
			if(result.state==1){
				alert("保存成功!");
				$("input[name='username']").val(result.data.username);
				$("input[name='nickname']").val(result.data.nickname);
				$("input[name='email']").val(result.data.email);
				$("textarea[name='resume']").val(result.data.resume);
			}else{
				alert("保存失败:"+result.message);
				l/*ocation.href ="index.html";*/
			}
		}
	});
});