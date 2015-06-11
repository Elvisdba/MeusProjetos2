<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Professor</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
</head>
<body>
	<h1>Listagem de Professores</h1>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${mensagem}</font>
	<p />
	<table border="1" width="780">
		<thead>
			<tr bgcolor='#BBBBBB'>
				<th width="20" align="center">N</th>
				<th width="200" align="center">Nome</th>
				<th width="80" align="center">Estado Civil</th>
				<th width="120" align="center">E-mail</th>
				<th width="80" align="center">Nasc</th>
				<th width="100" align="center">Cursos</th>
				<th width="40">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="linha" value="0" />
			<c:forEach var="professor" items="${professores}">
			    <c:set var="linha" value="${linha+1}" />
				${linha%2==0?"<tr bgcolor='#EEEEEE'>":"<tr bgcolor='#FFFFFF'>"}
					<td align="center">${linha}</td>
					<td>${professor.nome}</td>
					<td align="center">${professor.estadoCivil}</td>
					<td>${professor.email}</td>
					<td align="center"><fmt:formatDate value="${professor.dtaNasc}" /></td>
					<td align="center">
						<c:forEach var="cursoProf" items="${professor.cursos}">
							${cursoProf.nome}<br/>
						</c:forEach>
					</td>
					<td align="center">
						<a href="${raiz}/professorcrud?acao=alterar&id=${professor.id}"><img src="${raiz}/img/alterar.png" border="0" width="15" alt="Alterar Professor"></a>&nbsp;
						<a href="${raiz}/professorcrud?acao=excluir&id=${professor.id}"><img src="${raiz}/img/excluir.png" border="0" width="15" alt="Excluir Professor"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	<a href="${raiz}/professorcrud?acao=incluir">Incluir Professor</a>&nbsp;
	<a href="${raiz}/index.jsp">Voltar</a>
</body>
</html>