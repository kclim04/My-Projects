import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ForestStage extends Stage {
    private List<Pokemon> forestPokemons;

    public ForestStage(Pokemon[] pokemonList) {
        super("Forest");
        forestPokemons = new ArrayList<>(Arrays.asList(pokemonList));
        addPokemon("Haunter");
        addPokemon("Pikachu");
        addPokemon("Butterfree");
    }

    @Override
    public Pokemon chooseOpponentPokemon() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        for (Pokemon pokemon : forestPokemons) {
            if (pokemon.getName().equals("Haunter") ||
                pokemon.getName().equals("Pikachu") ||
                pokemon.getName().equals("Butterfree")) {
                availableOpponents.add(pokemon);
            }
        }

        if (!availableOpponents.isEmpty()) {
            Collections.shuffle(availableOpponents);
            return availableOpponents.get(0); // Return a random opponent from available ones
        } else {
            // If none of the specific Pokemon are available, return a default Pokemon
            return new Pokemon("Haunter", "Ghost", "Shadow Ball", "Ghost", 70.0, 55.0, 40.0, 50.0);
        }
    }
}
