import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Stage {
	private String stageType;
    private List<String> allPokemon;
    private Random randomPokemon;

    public Stage(String stageType) {
        this.stageType = stageType;
        this.allPokemon = new ArrayList<>();
        this.randomPokemon = new Random();
    }

    public void addPokemon(String pokemonName) {
        allPokemon.add(pokemonName);
    }

    public void chooseStage() {
        System.out.println("You had choose [ "+ getStageType() + " ] Stage!\n" );
        System.out.println("You may encounter these Pokemon: " + allPokemon);
    }

    public void encounterPokemon() {
        int chance = randomPokemon.nextInt(10);

        if (chance < 5) {
            int randomIndex = randomPokemon.nextInt(allPokemon.size());
            String encounteredPokemon = allPokemon.get(randomIndex);
            System.out.println("You encountered a " + encounteredPokemon + "!");
        } else {
            System.out.println("No Pokemon appeared this time.");
        }
    }
    public abstract Pokemon chooseOpponentPokemon();

    public String getStageType() {
        return stageType;
    }

	public void setStageType(String stageType) {
		this.stageType = stageType;
	}

	public List<String> getallPokemon() {
		return allPokemon;
	}

	public void setallPokemon(List<String> allPokemon) {
		this.allPokemon = allPokemon;
	}

	public Random getRandomPokemon() {
		return randomPokemon;
	}

	public void setRandomPokemon(Random randomPokemon) {
		this.randomPokemon = randomPokemon;
	}
}