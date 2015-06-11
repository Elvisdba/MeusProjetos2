<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Curso</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
</head>
<body>
	<h1>Sistema Escolar</h1>
	<font color="#FF0000">${erro}</font>
	<form method="post" action="${raiz}/cursocrud">
		<fieldset>
			<legend>
				<b>${(curso.id==0)||(param.id==0)?'Incluis&atilde;o':'Altera&ccedil;&atilde;o'} de Curso</b>
			</legend>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="id" value="${curso.id==null?param.id:curso.id}" />
			<label>Nome</label><br />
			<input type="text" name="nome" value="${curso.nome==null?param.nome:curso.nome}" size="50" maxlength="50" /><p />
			<label>Semestres (QTD)</label><br />
			<input type="text" name="semestres" value="${curso.semestres==null?param.semestres:curso.semestres}" size="2" maxlength="2" /><p />
			<label>Valor (R$)</label><br />
			<input type="text" name="valor" value="${curso.valor==null?param.valor:curso.valor}" size="10" maxlength="10" /><p />
			<input type="submit" value="Salvar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/cursocrud">Voltar</a>
</body>
</html>