<%@ page import="org.example.jspexception.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	UserDto user = (UserDto) request.getSession().getAttribute("user");
	pageContext.setAttribute("user", user);
	if (user == null) {
		out.print("<script>alert('로그인 정보가 없습니다.'); location.href='/'</script>");
		return;
	}
	user.setUserName("홍길동");
%>
<div style="display: flex; justify-content: center; margin-top: 400px;">
	<div style="display: flex; flex-direction: column">
		<p><span>아이디 : </span>${user.userId}</p>
		<p><span>이름 : </span>${user.userName}</p>
	</div>
</div>