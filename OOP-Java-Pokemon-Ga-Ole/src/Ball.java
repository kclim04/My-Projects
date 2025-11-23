import java.util.*;

public class Ball {
    private static Trainer player;
    private static int ballCount = 0;

    public void setPlayer(Trainer player) {
        this.player = player;
    }

    public static void generateMysteryPokeball(boolean playerWin, Pokemon AIpkmn1, Pokemon AIpkmn2) {
    	Random random = new Random();
    	int numBall = playerWin ? 2 : 1;
        if (playerWin) {
            System.out.println("Congratulations! You've won! You've earned two random Pokeballs");
            numBall = 2;
        } else {
            System.out.println("You lost... But you still got one Pokeball.");
            numBall = 1;
        }
        System.out.println("\nPress [ Enter ] to reveal the Pokeball you are going to get!");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        Battle points = new Battle();
        
        for (int i = 0; i < numBall; i++) {
        	int ballType = random.nextInt(4);
        	
        	String ballName;
        	double catchRate;
        	switch (ballType) {
        		case 0:
        			ballName = "Pokeball";
        			catchRate = 0.4;
        			break;
        		case 1:
        			ballName = "Great Ball";
        			catchRate = 0.6;
        			break;
        		case 2:
        			ballName = "Ultra Ball";
        			catchRate = 0.8;
        			break;
        		case 3:
        			ballName = "Master Ball";
        			catchRate = 1.0;
        			break;
        		default:
        			ballName = "Pokeball";
        			catchRate = 0.4;
        			break;
        	}
        System.out.println("----------------------------------------------------------------");
        System.out.println("Let's find out which kind of PokeBall you are going to get!");
        System.out.println("Gotcha! You've received a {" + ballName + "} !\n");
        if (ballCount %2 == 0) {
        	displayPkmnDetail1(AIpkmn1);
        }
        else {
        	displayPkmnDetail2(AIpkmn2);
        }
        
        String userChoice;
        do {
        	System.out.println("\nDo you want to try catching this Pokemon? (yes/no)");
        	userChoice = input.nextLine().toLowerCase();
        	if(!userChoice.equals("yes") && !userChoice.equals("no")) {
        		System.out.println("Invalid input. Please only enter 'yes' or 'no'.");
        	}
        }
        while(!userChoice.equals("yes") && !userChoice.equals("no"));
        if (userChoice.equals("yes")) {
        	System.out.println("\nYou threw " + ballName + " to catch the Pokemon......\n.\n.\n.\n");
        	double randomProb = random.nextDouble();
        	if(randomProb <= catchRate) {
        		System.out.println("You've caught { " + (ballCount == 0 ? AIpkmn1.getName() : AIpkmn2.getName()) + " } ");
        		player.catchPokemon((ballCount % 2 == 0 ? AIpkmn1 : AIpkmn2));
        	}
        	else {
        		System.out.println("You've failed to catch { " + (ballCount % 2  == 0 ? AIpkmn1.getName() : AIpkmn2.getName()) + " }...");
        	}
        }
        else {
        	System.out.println("You chose not to catch the Pokemon... Maybe next time!");
        }
        ballCount++;
        }
    }

    public static void displayPkmnDetail1(Pokemon AIpkmn1) {
        System.out.println("Pokemon: " + AIpkmn1.getName());
    }

    public static void displayPkmnDetail2(Pokemon AIpkmn2) {
        System.out.println("Pokemon: " + AIpkmn2.getName());
    }
}