<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery.validate.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
	<script type="text/javascript">
		$(document).ready( function() {
	    	$("#frmLogin").validate({
	      		rules:{
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
	<font color="#00FF00">${mensagem}</font>
	<form id="frmLogin" method="post" action="${raiz}/login">
		<fieldset>
			<legend><b>Login</b></legend>
			<label>Usuario</label><br />
			<input type="text" name="usuario" value="${param.usuario}" size="10" maxlength="10" /><p />
			<label>Senha</label><br />
			<input type="password" name="senha" size="10" maxlength="10" /><p />
			<input type="submit" value="Logar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/index.jsp">Voltar</a>
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>