package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.DAO.AlunoDAO;
import br.ucb.modelo.DAO.CursoDAO;
import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.negocio.AlunoBean;
import br.ucb.modelo.negocio.CursoBean;

@WebServlet("/alunocrud")
public class AlunoCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String acao=request.getParameter("acao"), pagina=null;
		AlunoBean aluno = new AlunoBean();
		AlunoDAO alunoDao;
		CursoDAO cursoDao;
		List <AlunoBean> alunos;
		List <CursoBean> cursos;
		
		try {
			alunoDao = new AlunoDAO();
			cursoDao = new CursoDAO();
			if ((acao == null) || (acao.equals("listar"))) {
				alunos = alunoDao.listar();
				request.setAttribute("alunos", alunos);
				pagina = "/entidades/alunoLista.jsp";
			}
			if ((acao != null) && (acao.equals("incluir"))) {
				cursos = cursoDao.listar();
				if (cursos.size() == 0) {
					request.setAttribute("erro", "Necessário incluir curso antes de incluir aluno");
					pagina = "/entidades/alunoLista.jsp";
				}
				else {
					request.setAttribute("aluno", aluno);
					request.setAttribute("cursos", cursos);
					request.setAttribute("estadosCivis", EstadoCivil.SEPARADO);
					pagina = "/entidades/alunoEntrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("alterar"))) {
				aluno = alunoDao.consultar(Long.valueOf(request.getParameter("id")));
				if (aluno == null)
					request.setAttribute("erro", "Erro ao localizar para alteração");
				else {
					cursos = cursoDao.listar();
					request.setAttribute("aluno", aluno);
					request.setAttribute("cursos", cursos);
					request.setAttribute("estadosCivis", EstadoCivil.CASADO);
					pagina = "/entidades/alunoEntrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("salvar"))) {
				boolean erro=false;
				try {
					// Recebe valores do formulario
					if (request.getParameter("nome") != null)
						aluno.setNome(request.getParameter("nome"));
					if (request.getParameter("email") != null)
						aluno.setEmail(request.getParameter("email"));
					if (request.getParameter("estCivil") != null)
						aluno.setEstadoCivil(EstadoCivil.valueOf(request.getParameter("estCivil")));
					if (request.getParameter("dtaNasc") != null) {
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						df.setLenient(false);
						Date data = df.parse(request.getParameter("dtaNasc"));
						aluno.setDtaNasc(data);
					}
					if (request.getParameter("matricula") != null)
						aluno.setMatricula(Integer.valueOf(request.getParameter("matricula")));
					if (request.getParameter("ativo") != null)
						aluno.setAtivo(true);
					else
						aluno.setAtivo(false);
					if (request.getParameter("cursoId") != null)
						(aluno.getCurso()).setId(Long.valueOf(request.getParameter("cursoId")));
					// Salva: inclui ou altera
					if (request.getParameter("id").equals("0")) { // Incluir
						if (alunoDao.incluir(aluno) > 0)
							request.setAttribute("mensagem", "Incluído com sucesso");
						else
							request.setAttribute("erro", "Erro de inclusão");
					}
					else { // Alterar
						aluno.setId(Long.valueOf(request.getParameter("id")));
						if (alunoDao.alterar(aluno) > 0)
							request.setAttribute("mensagem", "Alterado com sucesso");
						else
							request.setAttribute("erro", "Erro de alteração");
					}
					alunos = alunoDao.listar();
					request.setAttribute("alunos", alunos);
					pagina = "/entidades/alunoLista.jsp";
				}
				catch (NumberFormatException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de numero");
				}
				catch (ParseException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de data");
				}
				if (erro) {
					cursos = cursoDao.listar();
					request.setAttribute("cursos", cursos);
					request.setAttribute("estadosCivis", EstadoCivil.CASADO);
					pagina = "/entidades/alunoEntrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("excluir"))) {
				aluno = alunoDao.consultar(Long.valueOf(request.getParameter("id")));
				if (alunoDao.excluir(aluno) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
				alunos = alunoDao.listar();
				request.setAttribute("alunos", alunos);
				pagina = "/entidades/alunoLista.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
			pagina = "/entidades/alunoLista.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
