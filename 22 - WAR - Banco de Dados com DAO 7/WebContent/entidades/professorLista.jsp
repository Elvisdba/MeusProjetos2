<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Professor</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/geral/grid.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
</head>
<body>
	<jsp:include page="/template/cabecalho.jsp"/>
	<h1>Listagem de Professores</h1>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${mensagem}</font>
	<p />
	<table id="gride" class="gride" width="780">
		<thead class="grideCabeca">
			<tr>
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
				${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
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
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>