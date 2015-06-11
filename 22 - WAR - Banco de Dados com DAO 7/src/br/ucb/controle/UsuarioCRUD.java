package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucb.modelo.DAO.UsuarioDAO;
import br.ucb.modelo.enumerador.Perfil;
import br.ucb.modelo.negocio.UsuarioBean;

@WebServlet("/usuariocrud")
public class UsuarioCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao=request.getParameter("acao"), pagina=null;
		UsuarioBean usuario = new UsuarioBean();
		UsuarioDAO usuarioDao;
		List <UsuarioBean> usuarios = null;
		UsuarioBean usuarioLogado = (UsuarioBean) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null) {
			request.setAttribute("erro", "Necessário logar para ter acesso");
			pagina = "/login.jsp";
		}
		else {
			try {
				usuarioDao = new UsuarioDAO();
				if ((acao == null) || (acao.equals("listar"))) {
					usuarios = usuarioDao.listar();
					request.setAttribute("usuarios", usuarios);
					pagina = "/entidades/usuarioLista.jsp";
				}
				if ((acao != null) && (acao.equals("incluir"))) {
					if (usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						request.setAttribute("usuario", usuario);
						request.setAttribute("perfis", Perfil.USUARIO);
						pagina = "/entidades/usuarioEntrada.jsp";
					}
				}
				if ((acao != null) && (acao.equals("alterar"))) {
					long id = Long.valueOf(request.getParameter("id"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getId() != id)) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(id);
						request.setAttribute("usuario", usuario);
						request.setAttribute("perfis", Perfil.USUARIO);
						pagina = "/entidades/usuarioEntrada.jsp";
					}
				}
				if ((acao != null) && (acao.equals("salvar"))) {
					long id = Long.valueOf(request.getParameter("id"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getId() != id)) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						try {
							// Recebe valores do formulario
							if (request.getParameter("nome") != null)
								usuario.setNome(request.getParameter("nome"));
							if (request.getParameter("usuario") != null)
								usuario.setUsuario(request.getParameter("usuario"));
							if (request.getParameter("senha") != null)
								usuario.setSenha(request.getParameter("senha"));
							if (request.getParameter("perfil") != null)
								usuario.setPerfil(Perfil.valueOf(request.getParameter("perfil")));
							// Salva: inclui ou altera
							if (request.getParameter("id").equals("0")) { // Incluir
								if (usuarioDao.consultar(usuario.getUsuario()) == null) {
									if (usuarioDao.incluir(usuario) > 0)
										request.setAttribute("mensagem", "Incluído com sucesso");
									else
										request.setAttribute("erro", "Erro de inclusão");
									usuarios = usuarioDao.listar();
									request.setAttribute("usuarios", usuarios);
									pagina = "/entidades/usuarioLista.jsp";
								}
								else {
									request.setAttribute("erro", "Usuario já existe");
									request.setAttribute("perfis", Perfil.USUARIO);
									pagina = "/entidades/usuarioEntrada.jsp";		
								}
							}
							else { // Alterar
								usuario.setId(Long.valueOf(request.getParameter("id")));
								if (usuarioDao.alterar(usuario) > 0)
									request.setAttribute("mensagem", "Alterado com sucesso");
								else
									request.setAttribute("erro", "Erro de alteração");
								usuarios = usuarioDao.listar();
								request.setAttribute("usuarios", usuarios);
								pagina = "/entidades/usuarioLista.jsp";
							}
						}
						catch (NumberFormatException e) {
							request.setAttribute("erro", "Erro de conversao de numero");
							request.setAttribute("perfis", Perfil.USUARIO);
							pagina = "/entidades/usuarioEntrada.jsp";		
						}
					}
				}
				if ((acao != null) && (acao.equals("excluir"))) {
					if (usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(Long.valueOf(request.getParameter("id")));
						if (usuarioDao.excluir(usuario) > 0)
							request.setAttribute("mensagem", "Excluído com sucesso");
						else
							request.setAttribute("erro", "Erro de exclusão");
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						pagina = "/entidades/usuarioLista.jsp";
					}
				}
				if ((acao != null) && (acao.equals("informarSenha"))) {
					long id = Long.valueOf(request.getParameter("id"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getId() != id)) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(id);
						request.setAttribute("usuario", usuario);
						pagina = "/trocaSenha.jsp";
					}
				}
				if ((acao != null) && (acao.equals("trocarSenha"))) {
					long id = Long.valueOf(request.getParameter("id"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getId() != id)) {
						usuarios = usuarioDao.listar();
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/entidades/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(Long.valueOf(request.getParameter("id")));
						String senhaAtual = request.getParameter("senhaAtual");
						String senhaNova1 = request.getParameter("senhaNova1");
						String senhaNova2 = request.getParameter("senhaNova2");
						if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (!usuario.validarSenha(senhaAtual))) {
							request.setAttribute("erro", "Senha nao confere");
							pagina = "/trocaSenha.jsp";
						}
						else {
							if (!senhaNova1.equals(senhaNova2)) {
								request.setAttribute("erro", "Os valores informados para a nova senha estao diferentes");
								pagina = "/trocaSenha.jsp";
							}
							else {
								usuario.setSenha(senhaNova1);
								usuarioDao.trocaSenha(usuario);
								request.setAttribute("mensagem", "Senha alterada com sucesso");
								usuarios = usuarioDao.listar();
								request.setAttribute("usuarios", usuarios);
								pagina = "/entidades/usuarioLista.jsp";
							}
						}
					}
				}
			} catch (SQLException e) {
				request.setAttribute("erro", "Erro de banco de dados ("+ e.getMessage() +")");
				pagina = "/entidades/usuarioLista.jsp";
			}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}