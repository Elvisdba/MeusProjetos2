<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Escolar</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
</head>
<body>
	<h1>Sistema Escolar</h1>
	<ul>
  		<li><a href="${raiz}/cursocrud">Curso</a></li>
		<li><a href="${raiz}/alunocrud">Aluno</a></li>
		<li><a href="${raiz}/professorcrud">Professor</a></li>
	</ul>
</body>
</html>