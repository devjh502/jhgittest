<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅창</title>
</head>
<body>



<script >
	const server=new WebSocket ("ws://localhost:9090/chat");
	
	server.onopen=e=>{
		console.log(e);
	});

</script>

</body>
</html>