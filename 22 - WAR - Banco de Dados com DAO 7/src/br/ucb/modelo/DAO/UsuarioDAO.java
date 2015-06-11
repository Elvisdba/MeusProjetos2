package br.ucb.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.enumerador.Perfil;
import br.ucb.modelo.negocio.UsuarioBean;

public class UsuarioDAO {
	private Connection con;

	public UsuarioDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(UsuarioBean usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="INSERT INTO usuario (nome, usuario, senha, perfil) values (?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getUsuario());
		stmt.setString(3, usuario.getSenha());
		stmt.setString(4, usuario.getPerfil().toString());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public List<UsuarioBean> listar() throws SQLException {
		String sql = "SELECT * FROM usuario";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
		while (rs.next()) {
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(rs.getString("perfil")));
			usuarios.add(usuario);
		}
		rs.close();
		stmt.close();
		return usuarios;	
	}

	public UsuarioBean consultar(Long id) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		UsuarioBean usuario = null;
		if (rs.next()) {
			usuario = new UsuarioBean();
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(rs.getString("perfil")));
		}
		rs.close();
		stmt.close();
		return usuario;	
	}

	public UsuarioBean consultar(String user) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE usuario=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		UsuarioBean usuario = null;
		if (rs.next()) {
			usuario = new UsuarioBean();
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setPerfil(Perfil.valueOf(rs.getString("perfil")));
		}
		rs.close();
		stmt.close();
		return usuario;	
	}

	// Nao altera usuario (coluna usuario = username) e senha
	public int alterar(UsuarioBean usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="UPDATE usuario SET nome=?, perfil=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getPerfil().toString());
		stmt.setLong(3, usuario.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	// Nao altera usuario (coluna usuario = username) e senha
	public int trocaSenha(UsuarioBean usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql="UPDATE usuario SET senha=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getSenha());
		stmt.setLong(2, usuario.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int excluir(UsuarioBean usuario) throws SQLException {
		if (usuario == null)
			return 0;
		String sql = "DELETE FROM usuario WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, usuario.getId());
		int retorno = stmt.executeUpdate();		
		stmt.close();
		return retorno;
	}

}
