/**
 * Classe que efetua o CRUD com os dados do Jogador cadastrado no Banco de Dados.
 */

package com.centuri123.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centuri123.connection.ConnectionFactory;
import com.centuri123.jogo.Jogador;


public class DAO {
	
	public List<Object> selectJogador() throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Object> listaJogadores = new ArrayList<Object>();

		String query = "";
		query += "SELECT j.nome, r.qtd_vit, r.qtd_der, r.pontuacao FROM jogador j, ranking r ";
		query += "WHERE j.RA = r.RA_aluno";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				listaJogadores.add(rs.getString("nome"));
				listaJogadores.add(rs.getString("qtd_vit"));
				listaJogadores.add(rs.getString("qtd_der"));
				listaJogadores.add(rs.getString("pontuacao"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return listaJogadores;
	}
	
	public Jogador selectJogador(int raAluno) throws ClassNotFoundException{ //Em andamento, vou utilizar ArrayList nesse select.
	//public List<Jogador> selectJogador(int raAluno) throws ClassNotFoundException{ //Em andamento, vou utilizar ArrayList nesse select.
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//List<Jogador> listaJogadores = new ArrayList<Jogador>();
		Jogador jogador = new Jogador();
		//String nome = "";
		//int RA = 0, senha = 0;
		
		String query = "";
		query += "SELECT * FROM jogador ";
		query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, raAluno);
			rs = stmt.executeQuery();
			rs.next();
			//while(rs.next()) {
				//RA = rs.getInt(1);
				//nome = rs.getString(2);
				//senha = rs.getInt(3);
				jogador.setRA(rs.getInt(1));
				jogador.setNome(rs.getString(2));
				jogador.setSenha(rs.getInt(3));
				//listaJogadores.add(jogador);
			//}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		//return resultado;
		return jogador;
	}
	
	public void insertJogador(Jogador jogador) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO jogador(RA,nome,senha)" + " VALUES(?,?,?)";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,jogador.getRA());
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, jogador.getSenha());
			stmt.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	public void deleteJogador(Jogador jogador) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query = "";
		      
		query += "DELETE FROM jogador ";
		query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, jogador.getRA());
			stmt.execute();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void updateJogador(Jogador jogador, int senha) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query = ""; 
				
		query += "UPDATE jogador ";
		query += "SET senha = ? ";
		query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, senha);
			stmt.setInt(2, jogador.getRA());
			stmt.execute();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public int getCountPalavras() throws ClassNotFoundException{
		int countRegistros = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = ""; 
				
		query += "SELECT count(id) as qtd FROM palavras ";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			countRegistros = rs.getInt("qtd");
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}		
		return countRegistros;
	}
}

