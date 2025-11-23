import java.util.*;

public class Pokemon {
    // Attributes
    private String name;
    private String type;
    private String moveName;
    private String moveType;
    private double hp;
    private double atk;
    private double def;
    private double spd;
    
    // Constructor
    public Pokemon(String name, String type, String moveName, String moveType, double hp, double atk, double def, double spd) {
        this.name = name;
        this.type = type;
        this.moveName = moveName;
        this.moveType = moveType;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getMoveName() {
        return moveName;
    }

    public String getMoveType() {
        return moveType;
    }
    
    public double getHp() {
        return hp;
    }
    
    public double getAtk() {
        return atk;
    }
    
    public double getDef() {
        return def;
    }
    
    public double getSpd() {
        return spd;
    }
    
    // Setters
    public void setHp(double hp) {
        this.hp = hp;
    }
    
    public void setAtk(double atk) {
        this.atk = atk;
    }
    
    // Methods
    public static String flipCoin() {
        Random random = new Random();     
        boolean Heads = random.nextBoolean();
        
        if (Heads) {
            return "Heads";
        } else {
            return "Tails";
        }
    }
    
    public static Pokemon getRandomPokemonCatch(Pokemon[] pokemonList) {
        Random random = new Random();
        int randomIndex = random.nextInt(pokemonList.length);
        return pokemonList[randomIndex];
    }
	
	public static Pokemon getRandomPokemonBattle(Pokemon[] pokemonList) {
        Random random = new Random();
        int randomIndex = random.nextInt(pokemonList.length);
        return pokemonList[randomIndex];
    }
    
	public static int generateAI() {
	    Random random = new Random();
	    return random.nextInt(2) + 1;
	}
	
	public static void checkhp (Pokemon pokemon1, Pokemon pokemon2) {
		if (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
			System.out.println("Pokemon 1:");
			pokemon1.displayPokemonDetails(pokemon1);
			System.out.println("");
        	System.out.println("Pokemon 2:");
        	pokemon2.displayPokemonDetails(pokemon2);
		}
		
		else if (pokemon1.getHp() > 0 && pokemon2.getHp() <= 0) {
			System.out.println("Pokemon 1:");
			pokemon1.displayPokemonDetails(pokemon1);
			System.out.println("");
		}
		
		else if (pokemon1.getHp() <= 0 && pokemon2.getHp() > 0) {
			System.out.println("");
        	System.out.println("Pokemon 2:");
        	pokemon2.displayPokemonDetails(pokemon2);
		}
		else if(pokemon1.getHp() <= 0 && pokemon2.getHp() <= 0) {
			System.out.println(pokemon1.getName() + "has fainted");
			System.out.println(pokemon2.getName() + "has fainted");
		}
	}
    
	public static int generateRandomNumber() {
	    Random random = new Random();
	    return random.nextInt(10) + 1;
	}
    
    public void displayPokemonDetails(Pokemon pokemon) {
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Move Type: " + getMoveType());
        System.out.println("HP: " + getHp());
        System.out.println("Attack: " + getAtk());
        System.out.println("Defense: " + getDef());
        System.out.println("Speed: " + getSpd());
    }
}
