<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Curso</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
</head>
<body>
	<h1>Listagem de Cursos</h1>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${mensagem}</font>
	<form method="post" action="${raiz}/cursocrud">
		<input type="hidden" name="acao" value="filtrar" />
		<fieldset>
			<legend class="interno"><b>Filtro</b></legend>
			<label>Nome</label><br/>
			<input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50"/>
			<input type="submit" value="Filtrar"/>
		</fieldset>
	</form>
	<p />
	<table border="1" width="780">
		<thead>
			<tr bgcolor='#BBBBBB'>
				<th width="20" align="center">N</th>
				<th width="200" align="center">Nome</th>
				<th width="70" align="center">Semestres</th>
				<th width="80" align="center">Valor</th>
				<th width="60">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="linha" value="0" />
			<c:forEach var="curso" items="${cursos}">
			    <c:set var="linha" value="${linha+1}" />
				${linha%2==0?"<tr bgcolor='#EEEEEE'>":"<tr bgcolor='#FFFFFF'>"}
					<td align="center">${linha}</td>
					<td align="left">${curso.nome}</td>
					<td align="center">${curso.semestres}</td>
					<td align="right"><fmt:formatNumber value="${curso.valor}" type="currency" /></td>
					<td align="center">
						<a href="${raiz}/cursocrud?acao=alterar&id=${curso.id}"><img src="${raiz}/img/alterar.png" border="0" width="15" alt="Alterar Curso"></a>&nbsp;
						<a href="${raiz}/cursocrud?acao=excluir&id=${curso.id}"><img src="${raiz}/img/excluir.png" border="0" width="15" alt="Excluir Curso"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	<a href="${raiz}/cursocrud?acao=incluir">Incluir Curso</a>&nbsp;
	<a href="${raiz}/index.jsp">Voltar</a>
</body>
</html>