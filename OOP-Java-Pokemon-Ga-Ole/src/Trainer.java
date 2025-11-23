import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Trainer {
	private String name;
	private List <Pokemon> partyPokemon;
	private Scanner input;
	
	public Trainer() {
		
	}
	
	public Trainer(String name) {
		this.name = name;
		this.partyPokemon = new ArrayList<>();
		this.input = new Scanner(System.in);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Pokemon> getpartyPokemon(){
		return partyPokemon;
	}
	
	public void catchPokemon(Pokemon pokemon) {
	    if (partyPokemon == null) {
	       partyPokemon = new ArrayList<>();
	    }
	    partyPokemon.add(pokemon);
	    System.out.println(name + " caught a new Pokemon: " + pokemon.getName());
	 }
	
	public String lastCaughtPokemonName() {
        int lastIndex = partyPokemon.size() - 1;
        if (lastIndex >= 0) {
            Pokemon lastpartyPokemon = partyPokemon.get(lastIndex);
            return lastpartyPokemon.getName();
        } else {
            return "No Pokemon caught yet.";
        }
    }

    public void displayCaughtPokemons() {
        System.out.println("\nPokemon caught by " + name + ":");
        for (Pokemon pokemon : partyPokemon) {
            System.out.println(pokemon.getName());
        }
	}
}
