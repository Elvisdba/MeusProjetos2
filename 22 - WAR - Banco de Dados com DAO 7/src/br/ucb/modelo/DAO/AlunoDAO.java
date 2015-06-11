package br.ucb.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.negocio.AlunoBean;

public class AlunoDAO {
	private Connection con;
	
	public AlunoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(AlunoBean aluno) throws SQLException {
		if (aluno == null) return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO aluno (nome, dtaNasc, estCivil, email, matricula, ativo, idCurso) values (?, ?, ?, ?, ?, ?, ?)");
		stmt.setString(1, aluno.getNome());
		java.sql.Date dta = new java.sql.Date(aluno.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (aluno.getEstadoCivil()).toString());
		stmt.setString(4, aluno.getEmail());
		stmt.setInt(5, aluno.getMatricula());
		stmt.setBoolean(6, aluno.getAtivo());
		stmt.setLong(7, aluno.getCurso().getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}		

	public AlunoBean consultar(Long id) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM aluno WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		AlunoBean aluno = null;
		if (rs.next()) {
			aluno = new AlunoBean();
		    aluno.setId(rs.getLong("id"));
		    aluno.setNome(rs.getString("nome"));
		    aluno.setDtaNasc(rs.getDate("dtaNasc"));
		    aluno.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    aluno.setEmail(rs.getString("email"));
		    aluno.setMatricula(rs.getInt("matricula"));
		    aluno.setAtivo(rs.getBoolean("ativo"));
		    aluno.setCurso(new CursoDAO().consultar(rs.getLong("idCurso")));
		}
		rs.close();
		stmt.close();
		return aluno;	
	}
	
	public List<AlunoBean> listar() throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM aluno");
		ResultSet rs = stmt.executeQuery();
	    List<AlunoBean> alunos = new ArrayList<AlunoBean>();
	    while (rs.next()) {
		    AlunoBean aluno = new AlunoBean();
		    aluno.setId(rs.getLong("id"));
		    aluno.setNome(rs.getString("nome"));
		    aluno.setDtaNasc(rs.getDate("dtaNasc"));
		    aluno.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    aluno.setEmail(rs.getString("email"));
		    aluno.setMatricula(rs.getInt("matricula"));
		    aluno.setAtivo(rs.getBoolean("ativo"));
		    aluno.setCurso(new CursoDAO().consultar(rs.getLong("idCurso")));
		    alunos.add(aluno);
		}
		rs.close();
		stmt.close();
		return alunos;	
	}
	
	public int alterar(AlunoBean aluno) throws SQLException {
		if (aluno == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE aluno SET nome=?, dtaNasc=?, estCivil=?, email=?, matricula=?, ativo=?, idCurso=? WHERE id=?");
		stmt.setString(1, aluno.getNome());
		java.sql.Date dta = new java.sql.Date(aluno.getDtaNasc().getTime());
		stmt.setDate(2, dta);
		stmt.setString(3, (aluno.getEstadoCivil()).toString());
		stmt.setString(4, aluno.getEmail());
		stmt.setInt(5, aluno.getMatricula());
		stmt.setBoolean(6, aluno.getAtivo());
		stmt.setLong(7, aluno.getCurso().getId());
		stmt.setLong(8, aluno.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
	
	public int excluir(AlunoBean aluno) throws SQLException {
		if (aluno == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM aluno WHERE id=?");
		stmt.setLong(1, aluno.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

}