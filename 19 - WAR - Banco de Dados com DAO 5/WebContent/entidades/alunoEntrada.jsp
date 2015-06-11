<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Aluno</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
<body>
	<h1>Sistema Escolar</h1>
	<font color="#FF0000">${erro}</font>
	<form method="post" action="${raiz}/alunocrud">
		<fieldset>
			<legend>
				<b>${(aluno.id==0)||(param.id==0)?'Incluis&atilde;o':'Altera&ccedil;&atilde;o'}	de Aluno</b>
			</legend>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="id" value="${aluno.id==null?param.id:aluno.id}" />
			<label>Nome</label><br />
			<input type="text" name="nome" value="${aluno.nome==null?param.nome:aluno.nome}" size="50" /><p />
			<label>Estado Civil</label><br/>
			<select name="estCivil">
				<c:forEach var="estadoCivil" items="${estadosCivis.valores}">
					<option ${(aluno.estadoCivil==estadoCivil)||(param.estCivil==estadoCivil)?'selected':''}>${estadoCivil}</option>
				</c:forEach>
			</select><p />
			<label>E-mail</label><br/>
			<input type="text" name="email" value="${aluno.email==null?param.email:aluno.email}" size="40" /><p />
			<label>Data de Nascimento (DD/MM/AAAA)</label><br/>
			<input type="text" name="dtaNasc" value="${aluno.fmtDtaNasc==null?param.dtaNasc:aluno.fmtDtaNasc}" size="10" maxlength="10" /><p />
			<label>Matricula</label><br/>
			<input type="text" name="matricula" value="${aluno.matricula==null?param.matricula:aluno.matricula}" size="8" /><p />
			<label>Ativo</label><br/>
			<input type="checkbox" name="ativo" ${(aluno.ativo)||(param.ativo=='on')?'checked':''} /><p />
			<label>Curso</label><br/>
			<select name="cursoId">
				<c:forEach var="curso" items="${cursos}">
					<option ${(aluno.curso.id==curso.id)||(param.cursoId==curso.id)?'selected':''} value="${curso.id}">${curso.nome}</option>
				</c:forEach>
			</select><p />
			<input type="submit" value="Salvar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/alunocrud">Voltar</a>
</body>
</html>