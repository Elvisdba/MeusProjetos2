<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Usuario</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery.validate.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
	<script type="text/javascript">
		$(document).ready( function() {
	    	$("#frmUsuario").validate({
	      		rules:{
	        		nome:{
	          			required: true,
	          			minlength: 10
	        		},
	        		usuario:{
	          			required: true,
	          			minlength: 5
	        		},
	        		senha:{
	            		required: true,
	            		minlength: 4
	        		}
	      		},
	      		messages:{
	        		nome:{
	          			required: "Nome obrigatorio",
	          			minlength: "Minimo 10 caracteres"
	        		},
	      			usuario:{
	          			required: "Nome obrigatorio",
	          			minlength: "Minimo 5 caracteres"
	        		},
	        		senha:{
	          			required: "Senha obrigatoria",
	          			minlength: "Minimo 4 caracteres"
	        		}
	      		}
	    	});
	  	});
	</script>
</head>
<body>
	<jsp:include page="/template/cabecalho.jsp"/>
	<font color="#FF0000">${erro}</font>
	<form id="frmUsuario" method="post" action="${raiz}/usuariocrud">
		<fieldset>
			<legend>
				<b>${(usuario.id==0)||(param.id==0)?'Incluis&atilde;o':'Altera&ccedil;&atilde;o'} de Usuario</b>
			</legend>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="id" value="${usuario.id==null?param.id:usuario.id}" />
			<label>Nome</label><br />
			<input type="text" name="nome" value="${usuario.nome==null?param.nome:usuario.nome}" size="50" maxlength="50" /><p />
			<label>Usuario</label><br />
			<input type="text" name="usuario" ${((usuario.id==0)||(param.id==0))?'':'disabled'} value="${usuario.usuario==null?param.usuario:usuario.usuario}" size="10" maxlength="10" /><p />
			<c:if test="${((usuario.id==0)||(param.id==0))}">
				<label>Senha</label><br />
				<input type="password" name="senha" value="${param.senha}" size="10" maxlength="10" /><p />
			</c:if>
			<label>Perfil</label><br />
			<c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
				<select name="perfil">
					<c:forEach var="perfil" items="${perfis.valores}">
						<option ${(usuario.perfil==perfil)||(param.perfil==perfil)?'selected':''}>${perfil}</option>
					</c:forEach>
				</select><p />
			</c:if>
			<c:if test="${usuarioLogado.perfil!='ADMINISTRADOR'}">
				<input type="text" disabled="disabled" value="${usuario.perfil==null?param.perfil:usuario.perfil}" size="15" maxlength="15" /><p />
				<input type="hidden" name="perfil" value="${usuario.perfil==null?param.perfil:usuario.perfil}"  />
			</c:if>
			<input type="submit" value="Salvar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/usuariocrud">Voltar</a>
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>