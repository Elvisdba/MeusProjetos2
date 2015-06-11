<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Usuario</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/geral/grid.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
</head>
<body>
	<jsp:include page="/template/cabecalho.jsp"/>
	<h1>Listagem de Usuarios</h1>
	<font color="#FF0000">${erro}</font>
	<font color="#00FF00">${mensagem}</font>
	<table id="gride" class="gride" width="780">
		<thead class="grideCabeca">
			<tr>
				<th width="20" align="center">N</th>
				<th width="200" align="center">Nome</th>
				<th width="80" align="center">Usuario</th>
				<th width="80" align="center">Perfil</th>
				<th width="60">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="linha" value="0" />
			<c:forEach var="usuario" items="${usuarios}">
			    <c:set var="linha" value="${linha+1}" />
				${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
					<td align="center">${linha}</td>
					<td align="left">${usuario.nome}</td>
					<td align="center">${usuario.usuario}</td>
					<td align="center">${usuario.perfil}</td>
					<td align="center">
						<c:if test="${((usuarioLogado.perfil=='ADMINISTRADOR')||(usuarioLogado.id==usuario.id))}">
							<a href="${raiz}/usuariocrud?acao=alterar&id=${usuario.id}"><img src="${raiz}/img/alterar.png" border="0" width="15" alt="Alterar Usuario"></a>&nbsp;
						</c:if>
						<c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
							<a href="${raiz}/usuariocrud?acao=excluir&id=${usuario.id}"><img src="${raiz}/img/excluir.png" border="0" width="15" alt="Excluir Usuario"></a>
						</c:if>
						<c:if test="${((usuarioLogado.perfil=='ADMINISTRADOR')||(usuarioLogado.id==usuario.id))}">
							<a href="${raiz}/usuariocrud?acao=informarSenha&id=${usuario.id}"><img src="${raiz}/img/senha.png" border="0" width="15" alt="Alterar Senha"></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	<c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
		<a href="${raiz}/usuariocrud?acao=incluir">Incluir Usuario</a>&nbsp;
	</c:if>
	<a href="${raiz}/index.jsp">Voltar</a>
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>