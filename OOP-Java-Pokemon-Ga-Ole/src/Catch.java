import java.util.*;

public class Catch {
	private Pokemon WildPoke1;
    private Pokemon WildPoke2;
    private Pokemon WildPoke3;
    private Scanner scanner;
    
    
	public Catch(Pokemon wildPoke1, Pokemon wildPoke2, Pokemon wildPoke3) {
		WildPoke1 = wildPoke1;
		WildPoke2 = wildPoke2;
		WildPoke3 = wildPoke3;
	}
	
	public Catch() {
		this.scanner = new Scanner(System.in);
	}

	public Pokemon getWildPoke1() {
		return WildPoke1;
	}

	public Pokemon getWildPoke2() {
		return WildPoke2;
	}

	public Pokemon getWildPoke3() {
		return WildPoke3;
	}
	
	public static void printPokemonDetails(Pokemon wildPoke1, Pokemon wildPoke2, Pokemon wildPoke3) {
	    System.out.println("Wild Pokemon 1 Details:");
	    System.out.println("Name: " + wildPoke1.getName());
	    System.out.println("Type: " + wildPoke1.getType());
	    System.out.println("Move Type: " + wildPoke1.getMoveType());
	    System.out.println("HP: " + wildPoke1.getHp());
	    System.out.println("Attack: " + wildPoke1.getAtk());
	    System.out.println("Defense: " + wildPoke1.getDef());
	    System.out.println("Speed: " + wildPoke1.getSpd());
	
	    System.out.println("\nWild Pokemon 2 Details:");
	    System.out.println("Name: " + wildPoke2.getName());
	    System.out.println("Type: " + wildPoke2.getType());
	    System.out.println("Move Type: " + wildPoke2.getMoveType());
	    System.out.println("HP: " + wildPoke2.getHp());
	    System.out.println("Attack: " + wildPoke2.getAtk());
	    System.out.println("Defense: " + wildPoke2.getDef());
	    System.out.println("Speed: " + wildPoke2.getSpd());
	    
	    System.out.println("\nWild Pokemon 3 Details:");
	    System.out.println("Name: " + wildPoke3.getName());
	    System.out.println("Type: " + wildPoke3.getType());
	    System.out.println("Move Type: " + wildPoke3.getMoveType());
	    System.out.println("HP: " + wildPoke3.getHp());
	    System.out.println("Attack: " + wildPoke3.getAtk());
	    System.out.println("Defense: " + wildPoke3.getDef());
	    System.out.println("Speed: " + wildPoke3.getSpd());
	}
	
	
	public String toString() {
		return "Choose ONE pokemon to catch!\n";
	}
	    
	public void catching(Pokemon wildPoke1, Pokemon wildPoke2, Pokemon wildPoke3) {
		System.out.println("Choose ONE pokemon to catch!\n");
		System.out.println("Enter 1, 2, or 3\n");
		System.out.println("1. " + wildPoke1.getName()+"\n");
		System.out.println("2. " + wildPoke2.getName()+"\n");
		System.out.println("3. " + wildPoke3.getName()+"\n");
		System.out.println("Enter -> ");
		
		int userChoice;
		
		while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice >= 1 && userChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }
        }
	}
}