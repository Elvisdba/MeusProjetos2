<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema Escolar</title>
</head>
<body>
	<h2>Listagem de Cursos</h2>
	<font color="#FF0000">${erro}</font>
	<table border="1">
		<tr bgcolor="#AAAAAA">
			<th width="200" align="center">Nome</th>
			<th width="70" align="center">Semestres</th>
			<th width="80" align="center">Valor</th>
		</tr>
		<c:forEach var="curso" items="${cursos}">
			<tr>
				<td align="left">${curso.nome}</td>
				<td align="center">${curso.semestres}</td>
				<td align="right"><fmt:formatNumber value="${curso.valor}" type="currency" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="index.html">Voltar</a>
</body>
</html>