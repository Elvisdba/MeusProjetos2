package br.ucb.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.negocio.CursoBean;
import br.ucb.modelo.negocio.ProfessorBean;

public class ProfessorDAO {
	private Connection con;
	
	public ProfessorDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(ProfessorBean professor) throws SQLException {
		if (professor == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO professor (nome, dtaNasc, estCivil, email) values (?, ?, ?, ?)");
		stmt.setString(1, professor.getNome());
		java.sql.Date dta = new java.sql.Date(professor.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (professor.getEstadoCivil()).toString());
		stmt.setString(4, professor.getEmail());
		int retorno = stmt.executeUpdate();
		// Pegando o último valor de auto incremento inserido no mysql
		stmt = this.con.prepareStatement("SELECT LAST_INSERT_ID() AS id;");
		ResultSet rs = stmt.executeQuery();
		rs.first();
		long idProfessor = rs.getLong("id");
		if (retorno == 1) {
			stmt = con.prepareStatement("INSERT INTO curso_professor (idProfessor, idCurso) values (?, ?)");
			stmt.setLong(1, idProfessor);
			for (CursoBean curso : professor.getCursos()) {
				stmt.setLong(2, curso.getId());
				stmt.executeUpdate();
			}
		}
		stmt.close();
		return retorno;
	}		

	public ProfessorBean consultar(Long id) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM professor WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		ProfessorBean professor = null;
		if (rs.first()) {
			professor = new ProfessorBean();
		    professor.setId(rs.getLong("id"));
		    professor.setNome(rs.getString("nome"));
		    professor.setDtaNasc(rs.getDate("dtaNasc"));
		    professor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    professor.setEmail(rs.getString("email"));
		    
			stmt = this.con.prepareStatement("SELECT * FROM curso_professor WHERE idProfessor=?");
			stmt.setLong(1, rs.getLong("id"));
			rs = stmt.executeQuery();
		    while (rs.next())
		    	professor.getCursos().add(new CursoDAO().consultar(rs.getLong("idCurso")));
		}
		rs.close();
		stmt.close();
		return professor;	
	}
	
	public List<ProfessorBean> listar() throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM professor");
		ResultSet rs = stmt.executeQuery();
	    List<ProfessorBean> professors = new ArrayList<ProfessorBean>();
	    while (rs.next()) {
		    ProfessorBean professor = new ProfessorBean();
		    professor.setId(rs.getLong("id"));
		    professor.setNome(rs.getString("nome"));
		    professor.setDtaNasc(rs.getDate("dtaNasc"));
		    professor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    professor.setEmail(rs.getString("email"));
			
		    stmt = this.con.prepareStatement("SELECT * FROM curso_professor WHERE idProfessor=?");
			stmt.setLong(1, rs.getLong("id"));
			ResultSet rs2 = stmt.executeQuery();
		    while (rs2.next())
		    	professor.getCursos().add(new CursoDAO().consultar(rs2.getLong("idCurso")));

		    professors.add(professor);
		}
		rs.close();
		stmt.close();
		return professors;	
	}
	
	public int alterar(ProfessorBean professor) throws SQLException {
		if (professor == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE professor SET nome=?, dtaNasc=?, estCivil=?, email=? WHERE id=?");
		stmt.setString(1, professor.getNome());
		java.sql.Date dta = new java.sql.Date(professor.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (professor.getEstadoCivil()).toString());
		stmt.setString(4, professor.getEmail());
		stmt.setLong(5, professor.getId());
		int retorno = stmt.executeUpdate();
		// Exclui todos os cursos
		stmt = this.con.prepareStatement("DELETE FROM curso_professor WHERE idProfessor=?");
		stmt.setLong(1, professor.getId());
		stmt.executeUpdate();
		// Inclui novamente
		stmt = con.prepareStatement("INSERT INTO curso_professor (idProfessor, idCurso) values (?, ?)");
		stmt.setLong(1, professor.getId());
		for (CursoBean curso : professor.getCursos()) {
			stmt.setLong(2, curso.getId());
			stmt.executeUpdate();
		}
		stmt.close();
		return retorno;
	}
	
	public int excluir(ProfessorBean professor) throws SQLException {
		if (professor == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM curso_professor WHERE idProfessor=?");
		stmt.setLong(1, professor.getId());
		stmt.executeUpdate();
		stmt = this.con.prepareStatement("DELETE FROM professor WHERE id=?");
		stmt.setLong(1, professor.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
	
}