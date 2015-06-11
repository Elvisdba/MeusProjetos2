<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table border="0" cellpadding="0" cellspacing="0" width="780">
	<tr bgcolor="#2B54C4">
		<td width="250">
			<img src="${pageContext.request.contextPath}/img/logo.jpg" border="0" />
		</td>
		<td align="left">
			<font color="#FFFFFF" size="4">
				<b>Universidade Cat&oacute;lica de Bras&iacute;lia</b>
			</font>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			${usuarioLogado.nome}
			<c:if test="${usuarioLogado != null}">
				(<a href="${pageContext.request.contextPath}/login?acao=logoff">logoff</a>)
			</c:if>
		</td>
	</tr>
</table>
<p />