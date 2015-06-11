<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Professor</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/calendario/jquery.click-calendario-1.0.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery.validate.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/maskedinput/jquery.maskedinput-1.3.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/jquery.click-calendario-1.0.css" />
	<link type="text/css" rel="stylesheet" href="${raiz}/css/geral.css" />
	<script type="text/javascript">
		// http://www.tidbits.com.br/plugin-de-mascara-para-jquery-masked-input
		// a - letra (A-Z,a-z); 9 - numero (0-9); * - caractere (A-Z,a-z,0-9)
		$(document).ready(function(){
			$("#dtaNasc").mask("99/99/9999");
		});
		$(document).ready(function(){
			$('#dtaNasc').focus(function(){
				$(this).calendario({
					target:'#dtaNasc'
	    		});
		    });
	  	});
		$(document).ready( function() {
	    	$("#frmProfessor").validate({
	      		rules:{
	        		nome:{
	          			required: true,
	          			minlength: 2
	        		},
	        		email:{
	            		required: true,
	            		email: true
	        		},
	        		dtaNasc:{
	            		required: true,
	            		dateBR: true
	        		}
	      		},
	      		messages:{
	        		nome:{
	          			required: "Nome obrigatorio",
	          			minlength: "Minimo 2 caracteres"
	        		},
	        		email:{
	          			required: "E-mail obrigatorio",
	          			email: "E-mail invalido"
	              	},
	        		dtaNasc:{
	          			required: "Data de nascimento obrigatoria",
	          			dateBR: "Data de nascimento invalida"
	        		}
	      		}
	    	});
	  	});
	</script>
</head>
<body>
	<jsp:include page="/template/cabecalho.jsp"/>
	<font color="#FF0000">${erro}</font>
	<form id="frmProfessor" method="get" action="${raiz}/professorcrud">
		<fieldset>
			<legend>
				<b>${(professor.id==0)||(param.id==0)?'Incluis&atilde;o':'Altera&ccedil;&atilde;o'}	de Professor</b>
			</legend>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="id" value="${professor.id==null?param.id:professor.id}" />
			<label>Nome</label><br/>
			<input type="text" name="nome" value="${professor.nome==null?param.nome:professor.nome}" size="50" /><p />
			<label>Estado Civil</label><br/>
			<select name="estCivil">
				<c:forEach var="estadoCivil" items="${estadosCivis.valores}">
					<option ${(aluno.estadoCivil==estadoCivil)||(param.estCivil==estadoCivil)?'selected':''}>${estadoCivil}</option>
				</c:forEach>
			</select><p />
			<label>E-mail</label><br/>
			<input type="text" name="email" value="${professor.email==null?param.email:professor.email}" size="40" /><p />
			<label>Data de Nascimento (DD/MM/AAAA)</label><br/>
			<input type="text" name="dtaNasc" id="dtaNasc" value="${professor.fmtDtaNasc==null?param.dtaNasc:professor.fmtDtaNasc}" size="10" maxlength="10" /><p />
			<label>Cursos</label><br/>
			<c:forEach var="curso" items="${cursos}">
				<c:set var="achou" value="false" />
				<c:forEach var="cursoProf" items="${professor.cursos}">
					<c:if test="${curso.id == cursoProf.id}">
						<c:set var="achou" value="true" />
					</c:if>
				</c:forEach>
				<input name="cursos" type="checkbox" ${achou?'checked':''} value="${curso.id}">${curso.nome}<br />
			</c:forEach>
			<p />
			<input type="submit" value="Salvar" />
		</fieldset>
	</form>
	<br/><a href="${raiz}/professorcrud">Voltar</a>
	<jsp:include page="/template/rodape.jsp"/>
</body>
</html>