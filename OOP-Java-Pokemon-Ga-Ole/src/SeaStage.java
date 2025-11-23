import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class SeaStage extends Stage {
    private List<Pokemon> seaPokemons;

    public SeaStage(Pokemon[] pokemonList) {
        super("Sea");
        seaPokemons = new ArrayList<>(Arrays.asList(pokemonList));
        addPokemon("Lapras");
        addPokemon("Staryu");
        addPokemon("Krabby");
    }

    @Override
    public Pokemon chooseOpponentPokemon() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        for (Pokemon pokemon : seaPokemons) {
            if (pokemon.getName().equals("Lapras") ||
                pokemon.getName().equals("Staryu") ||
                pokemon.getName().equals("Krabby")) {
                availableOpponents.add(pokemon);
            }
        }

        if (!availableOpponents.isEmpty()) {
            Collections.shuffle(availableOpponents);
            return availableOpponents.get(0); // Return a random opponent from available ones
        } else {
            // If none of the specific Pokemon are available, return a default Pokemon
            return new Pokemon("Lapras", "Water", "Surf", "Water", 70.0, 55.0, 40.0, 50.0);
        }
    }
}
