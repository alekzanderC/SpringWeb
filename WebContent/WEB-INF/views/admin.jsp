<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<h1>ADMIN PAGE</h1>
	<br>
	<a href='<c:url value="/about"></c:url>'>ACERCA DE </a>
	<br>
	<br> Atributos del modelo
	<br>

	<br>Mensaje:
	<c:out value="${mensaje}" />
	<br>Resultado:
	<c:out value="${sessionScope.resultado}" />

</body>
</html>