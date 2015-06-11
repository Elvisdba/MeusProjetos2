<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <font color="#00FF00">${mensagem}</font>
	<form method="post" action="cursoCRUD">
	    <input type="hidden" name="acao" value="filtrar" />
	    <label>Nome</label><br />
	    <input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50" /><p />
	    <input type="submit" value="Filtrar" />
	</form>
	<table border="1">
		<tr bgcolor="#AAAAAA">
			<th>Nome</th>
			<th>Semestres</th>
			<th>Valor</th>
			<th></th>
		</tr>
		<c:forEach var="curso" items="${cursos}">
			<tr>
				<td>${curso.nome}</td>
				<td>${curso.semestres}</td>
				<td><fmt:formatNumber value="${curso.valor}" type="currency" /></td>
				<td>
					<a href="cursoCRUD?acao=excluir&id=${curso.id}"><img src="img/excluir.png" border=0 width=25></a>
					<a href="cursoCRUD?acao=alterar&id=${curso.id}"><img src="img/alterar.png" border=0 width=25></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="cursoFormulario.jsp">Incluir</a>&nbsp;
	<a href="index.html">Voltar</a>
</body>
</html>