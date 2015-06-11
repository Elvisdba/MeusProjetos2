<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Trocar senha</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery.validate.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
	<script type="text/javascript">
		$(document).ready( function() {
	    	$("#frmTroca").validate({
	      		rules:{
	      			senhaAtual:{
	          			required: true,
	          			minlength: 4
	        		},
	        		senhaNova1:{
	            		required: true,
	            		minlength: 4
	        		},
	        		senhaNova2:{
	            		required: true,
	            		minlength: 4
	        		}
	      		},
	      		messages:{
	      			senhaAtual:{
	          			required: "Senha obrigatoria",
	          			minlength: "Minimo 4 caracteres"
	        		},
	        		senhaNova1:{
	          			required: "Senha obrigatoria",
	          			minlength: "Minimo 4 caracteres"
	        		},
	        		senhaNova2:{
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
	<font color="#00FF00">${mensagem}</font>
	${usuario.nome}
	<form id="frmTroca" method="post" action="${raiz}/usuariocrud">
		<fieldset>
			<input type="hidden" name="acao" value="trocarSenha" />
			<input type="hidden" name="id" value="${usuario.id==null?param.id:usuario.id}" />
			<legend><b>Troca senha</b></legend>
			<c:if test="${usuarioLogado.perfil != 'ADMINISTRADOR'}">
				<label>Senha atual</label><br />
				<input type="password" name="senhaAtual" value="${param.senhaAtual}" size="10" maxlength="10" /><p />
			</c:if>
			<label>Nova senha</label><br />
			<input type="password" name="senhaNova1" value="${param.senhaNova1}" size="10" maxlength="10" /><p />
			<label>Repita nova senha</label><br />
			<input type="password" name="senhaNova2" value="${param.senhaNova2}" size="10" maxlength="10" /><p />
			<input type="submit" value="Trocar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/usuariocrud">Voltar</a>
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>