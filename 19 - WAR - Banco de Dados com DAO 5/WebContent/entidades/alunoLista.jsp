<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Aluno</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
</head>
<body>
	<h1>Listagem de Alunos</h1>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${mensagem}</font>
	<p />
	<table border="1" width="780">
		<thead>
			<tr bgcolor='#BBBBBB'>
				<th width="20" align="center">N</th>
				<th width="160" align="center">Nome</th>
				<th width="80" align="center">Estado Civil</th>
				<th width="110" align="center">E-mail</th>
				<th width="70" align="center">Nasc</th>
				<th width="30" align="center">Mat</th>
				<th width="40" align="center">Ativo</th>
				<th width="160" align="center">Curso</th>
				<th width="40">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="linha" value="0" />
			<c:forEach var="aluno" items="${alunos}">
			    <c:set var="linha" value="${linha+1}" />
				${linha%2==0?"<tr bgcolor='#EEEEEE'>":"<tr bgcolor='#FFFFFF'>"}
					<td align="center">${linha}</td>
					<td>${aluno.nome}</td>
					<td align="center">${aluno.estadoCivil}</td>
					<td>${aluno.email}</td>
					<td align="center"><fmt:formatDate value="${aluno.dtaNasc}" /></td>
					<td align="right">${aluno.matricula}</td>
					<td align="center">${aluno.ativo?"Sim":"Nao"}</td>
					<td>${aluno.curso.nome}</td>
					<td align="center">
						<a href="${raiz}/alunocrud?acao=alterar&id=${aluno.id}"><img src="${raiz}/img/alterar.png" border="0" width="15" alt="Alterar Aluno"></a>&nbsp;
						<a href="${raiz}/alunocrud?acao=excluir&id=${aluno.id}"><img src="${raiz}/img/excluir.png" border="0" width="15" alt="Excluir Aluno"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	<a href="${raiz}/alunocrud?acao=incluir">Incluir Aluno</a>&nbsp;
	<a href="${raiz}/index.jsp">Voltar</a>
</body>
</html>