import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MountainStage extends Stage {
	 private List<Pokemon> mountainPokemons;

	    public MountainStage(Pokemon[] pokemonList) {
	        super("Mountain");
	        mountainPokemons = new ArrayList<>(Arrays.asList(pokemonList));
	        addPokemon("Clefairy");
	        addPokemon("Mankey");
	        addPokemon("Geodude");
	    }

	    @Override
	    public Pokemon chooseOpponentPokemon() {
	        List<Pokemon> availableOpponents = new ArrayList<>();
	        for (Pokemon pokemon : mountainPokemons) {
	            if (pokemon.getName().equals("Clefairy") ||
	                pokemon.getName().equals("Mankey") ||
	                pokemon.getName().equals("Geodude")) {
	                availableOpponents.add(pokemon);
	            }
	        }

	        if (!availableOpponents.isEmpty()) {
	            Collections.shuffle(availableOpponents);
	            return availableOpponents.get(0);
	        } else {
	            // If none of the specific Pokemon are available, return a default Pokemon
	            return new Pokemon("Clefairy", "Fairy", "Moonblast", "Fairy", 70.0, 45.0, 48.0, 35.0);
	        }
	    }
}