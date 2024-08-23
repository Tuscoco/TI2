package app;

import java.util.List;
import dao.DAO;
import dao.PokemonDAO;
import model.Pokemon;

public class Aplicacao {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		
		PokemonDAO pokDAO = new PokemonDAO();
		
		System.out.println("\n\n==== Inserir Pokemon === ");
		Pokemon pok = new Pokemon(10, "Caterpie", "Inseto");
		if(pokDAO.insert(pok) == true) {
			System.out.println("Inserção com sucesso -> " + pok.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + pok.getNome() + "): " + PokemonDAO.autenticar("Charmander", "Fogo"));
			
		System.out.println("\n\n==== Mostrar usuários do tipo fogo === ");
		List<Pokemon> pokemons = PokemonDAO.getOrderByTipo();
		for (Pokemon u: pokemons) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar Tipo (código (" + pok.getCodigo() + ") === ");
		pok.setTipo(DAO.toMD5("pablo"));
		PokemonDAO.update(pok);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + pok.getNome() + "): " + PokemonDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + pok.getNome() + "): " + PokemonDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar Pokemons ordenados por código === ");
		pok = (Pokemon) pokDAO.getOrderByCodigo();
		for (Pokemon u: pokemons) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + pok.getCodigo() + ") === ");
		pokDAO.delete(pok.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		pok = (Pokemon) pokDAO.getOrderByNome();
		for (Pokemon u: pokemons) {
			System.out.println(u.toString());
		}
	}
}