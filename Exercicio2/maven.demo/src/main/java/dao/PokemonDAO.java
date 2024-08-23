package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pokemon;

public class PokemonDAO extends DAO {
	
	public PokemonDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Pokemon pok) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			@SuppressWarnings("static-access")
			String sql = "INSERT INTO Pokemon (Nome, Tipo, Codigo) "
				       + "VALUES ("+pok.getNome()+ ", '" + pok.getTipo() + "', '"  
				       + pok.getCodigo()+"');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Pokemon get(int codigo) {
		Pokemon pok = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 pok = new Pokemon(rs.getInt("Codigo"), rs.getString("Tipo"),rs.getString("Nome"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pok;
	}
	
	
	public List<Pokemon> get() {
		return get("");
	}

	
	public List<Pokemon> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Pokemon> getOrderByNome() {
		return get("Nome");		
	}
	
	
	public static List<Pokemon> getOrderByTipo() {
		return get("Tipo");		
	}
	
	
	private static List<Pokemon> get(String orderBy) {	
	
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Pokemon u = new Pokemon(rs.getInt("Codigo"), rs.getString("Tipo"),rs.getString("Nome"));
	            pokemons.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pokemons;
	}


	public List<Pokemon> getSexoMasculino() {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Pokemon u = new Pokemon(rs.getInt("Codigo"), rs.getString("Tipo"),rs.getString("Nome"));
	            pokemons.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pokemons;
	}
	
	
	public static boolean update(Pokemon pok) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			@SuppressWarnings("static-access")
			String sql = "UPDATE usuario SET Nome = '" + pok.getNome()+ "', Tipo = '"  
				       + pok.getTipo() + "', "
					   + " WHERE codigo = " + pok.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM Pokemon WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public static boolean autenticar(String Nome, String Tipo) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Pokemon WHERE Nome LIKE '" + Nome + "' AND Tipo LIKE '" + Tipo  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	
}