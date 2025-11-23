import java.util.*;
public class Game {
    public static void main(String[] args) {
    	Pokemon [] pokemonList = new Pokemon[33];
    	pokemonList[0] = new Pokemon("Pikachu", "Electric", "Thunderbolt", "Electric", 50.0, 55.0, 40.0, 90.0);
    	pokemonList[1] = new Pokemon("Clefairy", "Fairy", "Moonblast", "Fairy", 70.0, 45.0, 48.0, 35.0);
    	pokemonList[2] = new Pokemon("Pidgeotto", "Flying", "Wing Attack", "Flying", 70.0, 45.0, 55.0, 50.0);
    	pokemonList[3] = new Pokemon("Butterfree", "Bug", "Bug Bite", "Bug", 60.0, 45.0, 50.0, 70.0);
    	pokemonList[4] = new Pokemon ("Ekans", "Poison", "Toxic", "Poison", 60.0, 55.0, 40.0, 50.0);
    	pokemonList[5] = new Pokemon("Vulpix", "Fire", "Fire Spin", "Fire", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[6] = new Pokemon("Rattata", "Normal", "Tackle", "Normal", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[7] = new Pokemon("Diglett", "Ground", "Dig", "Ground", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[8] = new Pokemon("Meowth", "Normal", "Scratch", "Normal", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[9] = new Pokemon("Psyduck", "Water", "Water Gun", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[10] = new Pokemon("Mankey", "Fighting", "Seismic Toss", "Fighting", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[11] = new Pokemon("Polywhirl", "Water", "Water Pulse", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[12] = new Pokemon("Growlithe", "Fire", "Fire Fang", "Fire", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[13] = new Pokemon("Kadabra", "Psychic", "Psyshock", "Psychic", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[14] = new Pokemon("Machoke", "Fighting", "Mach Punch", "Fighting", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[15] = new Pokemon("Geodude", "Rock", "Rock Slide", "Rock", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[16] = new Pokemon("Haunter", "Ghost", "Shadow Ball", "Ghost", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[17] = new Pokemon("Dratini", "Dragon", "Dragon Tail", "Dragon", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[18] = new Pokemon("Tentacool", "Water", "Bubble Beam", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[19] = new Pokemon("Ponyta", "Fire", "Flame Wheel", "Fire", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[20] = new Pokemon("Dewgong", "Ice", "Ice Beam", "Ice", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[21] = new Pokemon("Onix", "Rock", "Rock Tomb", "Rock", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[22] = new Pokemon("Krabby", "Water", "Water Gun", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[23] = new Pokemon("Voltorb", "Electric", "Spark", "Electric", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[24] = new Pokemon("Cubone", "Normal", "Headbutt", "Normal", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[25] = new Pokemon("Horsea", "Water", "Whirlpool", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[26] = new Pokemon("Goldeen", "Water", "Waterfall", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[27] = new Pokemon("Staryu", "Water", "Brine", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[28] = new Pokemon("Pinsir", "Bug", "Bug Bite", "Bug", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[29] = new Pokemon("Lapras", "Water", "Surf", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[30] = new Pokemon("Drowzee", "Psychic", "Confusion", "Psychic", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[31] = new Pokemon("Shellder", "Water", "Chilling Water", "Water", 70.0, 55.0, 40.0, 50.0);
    	pokemonList[32] = new Pokemon("Rhyhorn", "Rock", "Rock Blast", "Rock", 70.0, 55.0, 40.0, 50.0);
        Scanner input = new Scanner(System.in);
        
        Trainer player = new Trainer();
        System.out.println("Please enter your name");
        String enteredName = input.nextLine();
        player.setName(enteredName);
        System.out.println("\nWelcome Trainer " + player.getName() + "\n");
        Battle battle = new Battle();
        Ball trainer = new Ball();
        trainer.setPlayer(player);
        
        String chosenStage;
        Stage currentStage = null;
        
        while(true) {
        	System.out.println("Choose a stage (Forest / Mountain / Sea): ");
        	chosenStage = input.nextLine().toLowerCase();
        	if(chosenStage.equals("forest")) {
        		currentStage = new ForestStage(pokemonList);
        		break;
        	}
        	else if(chosenStage.equals("sea")) {
        		currentStage = new SeaStage(pokemonList);
        		break;
        	}
        	else if(chosenStage.equals("mountain")) {
        		currentStage = new MountainStage(pokemonList);
        		break;
        	}
        }
        
        currentStage.chooseStage();
        System.out.println("\nYou've encountered 3 wild Pokemon!");
        System.out.println(".\n\n.\n\n.\n\n");
        
        Catch wp = new Catch(Pokemon.getRandomPokemonCatch(pokemonList), Pokemon.getRandomPokemonCatch(pokemonList), Pokemon.getRandomPokemonCatch(pokemonList));
        Catch.printPokemonDetails(wp.getWildPoke1(), wp.getWildPoke2(), wp.getWildPoke3());
        
        System.out.println("\nChoose a pokemon to catch!\n");
		System.out.println("Enter 1, 2, or 3\n");
		System.out.println("1. " + wp.getWildPoke1().getName()+"\n");
		System.out.println("2. " + wp.getWildPoke2().getName()+"\n");
		System.out.println("3. " + wp.getWildPoke3().getName()+"\n");
		System.out.println("Enter -> ");
		
		int Choice;
		
		while(true) {
			try {
				Choice = Integer.parseInt(input.nextLine());
				if (Choice >= 1 && Choice <= 3) {
					break;
				}
				else {
					System.out.println("Invalid input. Please only enter a number between 1 and 3.");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid input. Please only enter a number between 1 and 3.");	
			}
		}
		
		switch(Choice) {
		case 1:
			player.catchPokemon(wp.getWildPoke1());
			break;
		case 2:
			player.catchPokemon(wp.getWildPoke2());
			break;
		case 3:
			player.catchPokemon(wp.getWildPoke3());
			break;
		}
		
		System.out.println(".\n\n.\n\n.\n\n");
		while(true) {
			System.out.println("Enter \"Roar\" to roar and attract nearby Pokemon!");
			String roar = input.nextLine();
			if("Roar".equalsIgnoreCase(roar)) {
				String lastCaughtPokemon = player.lastCaughtPokemonName();
				System.out.println(lastCaughtPokemon + " roared loudly! Wild Pokemon are approaching...");
				break;
			}
			else {
			}
		}
		
		Battle op = new Battle();
		op.setAIpkmn1(currentStage.chooseOpponentPokemon());
		op.setAIpkmn2(Pokemon.getRandomPokemonCatch(pokemonList));
		
		System.out.println("");
		System.out.println("\n--------------------------------------\n");
		System.out.println(op.getAIpkmn1().getName() + " appeared!");
		System.out.println(op.getAIpkmn2().getName() + " appeared!");
		System.out.println("\n--------------------------------------\n");
		
		System.out.println("");
		player.displayCaughtPokemons();
		while(player.getpartyPokemon().size() < 2) {
			System.out.println("You need at least 2 Pokemon to start a battle.");
			while(true) {
				System.out.println("Borrow a Pokemon from the game? (yes/no)");
				String ans = input.nextLine();
				if(ans.equalsIgnoreCase("yes")) {
					System.out.println("Giving you a random Pokemon in \n3.\n2.\n1.\n:");
					player.catchPokemon(Pokemon.getRandomPokemonCatch(pokemonList));
					break;
				}
				else if(ans.equalsIgnoreCase("no")) {
					System.out.println("\nYou can't play without 2 Pokemon!");
				}
				else {
					System.out.println("Invalid answer!");
				}
			}
		}
		
		int choice1 = 0, choice2 = 0;
		
		System.out.println("Choose your Pokemon to battle:");
		player.displayCaughtPokemons();
		
		try {
			System.out.println("Choose 2 Pokemon for battle (enter the corresponding numbers):");
			choice1 = input.nextInt();
			choice2 = input.nextInt();
			if(!(choice1 >= 1 && choice1 <= player.getpartyPokemon().size() && choice2 >= 1 && choice2 <= player.getpartyPokemon().size() && choice1 != choice2)){
				System.out.println("Invalid choices. Please choose a different Pokemon for battle.");
			}		
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid choice, enter a valid integer.");
			input.hasNext();
		}
		
		Pokemon pokemon1 = player.getpartyPokemon().get(choice1 - 1);
		Pokemon pokemon2 = player.getpartyPokemon().get(choice2 - 1);
		
		System.out.println("You have sent out " + pokemon1.getName() + " and " + pokemon2.getName() + " to battle!");
		System.out.println(".\n\n.\n\n.\n\n");
		System.out.println("\n----------Battle Start!----------\n");
		System.out.println("");
		while (true) {
		    System.out.println("Choose Heads or Tails: ");
		    String coinSide = input.nextLine();
		    String coinResult = Pokemon.flipCoin();

		    if (!coinSide.equalsIgnoreCase("Heads") && !coinSide.equalsIgnoreCase("Tails")) {
		        System.out.println("Invalid choice, choose again.");
		        continue; // Restart the loop for a valid choice
		    }

		    boolean playerStartsFirst = coinSide.equalsIgnoreCase(coinResult);
		    System.out.println(playerStartsFirst ? "\n-------YOU START FIRST!-------\n" : "\n-------OPPONENT STARTS FIRST!-------\n");

		    // Display opponent Pokemon details
		    Battle.AIpkmndetailBattle(op.getAIpkmn1(), op.getAIpkmn2());

		    boolean playerWin = false;

		    while (true) {
		        if (playerStartsFirst) {
		            System.out.println("\n-------Your turn to attack!-------\n");
		            Pokemon.checkhp(pokemon1, pokemon2);
		            Battle mt = new Battle();
		            mt.MyTurnChoose(pokemon1, pokemon2, op.getAIpkmn1(), op.getAIpkmn2());
		            
		            if ((op.getAIpkmn1().getHp() <= 0) && (op.getAIpkmn2().getHp() <= 0)) {
		                Battle.displayPokemonHealth(op.getAIpkmn1(), op.getAIpkmn2());
		                System.out.println("You've defeated " + op.getAIpkmn1().getName() + " and " + op.getAIpkmn2().getName());
		                System.out.println("\n----------Battle End-----------\n");
		                playerWin = true;
		                break;
		            } else {
		                Battle.displayPokemonHealth(op.getAIpkmn1(), op.getAIpkmn2());
		            }

		            Battle at = new Battle();
		            at.AITurnChoose(pokemon1, pokemon2, op.getAIpkmn1(), op.getAIpkmn2());

		            if (pokemon1.getHp() <= 0 && pokemon2.getHp() <= 0) {
		                System.out.println("You are defeated......");
		                Battle.displayPokemonHealth(pokemon1, pokemon2);
		                System.out.println("\n----------Battle End-----------\n");
		                playerWin = false;
		                break;
		            } else {
		                Battle.displayPokemonHealth(pokemon1, pokemon2);
		            }
		        } else {
		            Battle at = new Battle();
		            at.AITurnChoose(pokemon1, pokemon2, op.getAIpkmn1(), op.getAIpkmn2());

		            if (pokemon1.getHp() <= 0 && pokemon2.getHp() <= 0) {
		                Battle.displayPokemonHealth(pokemon1, pokemon2);
		                System.out.println("You are defeated......");
		                System.out.println("\n----------Battle End-----------\n");
		                playerWin = false;
		                break;
		            } else {
		                Battle.displayPokemonHealth(pokemon1, pokemon2);
		            }

		            System.out.println("\n-------Your turn to attack!-------\n");
		            Pokemon.checkhp(pokemon1, pokemon2);
		            Battle mt = new Battle();
		            mt.MyTurnChoose(pokemon1, pokemon2, op.getAIpkmn1(), op.getAIpkmn2());

		            if ((op.getAIpkmn1().getHp() <= 0) && (op.getAIpkmn2().getHp() <= 0)) {
		                Battle.displayPokemonHealth(op.getAIpkmn1(), op.getAIpkmn2());
		                System.out.println("You've defeated " + op.getAIpkmn1().getName() + " and " + op.getAIpkmn2().getName());
		                System.out.println("\n----------Battle End-----------\n");
		                playerWin = true;
		                break;
		            } else {
		                Battle.displayPokemonHealth(op.getAIpkmn1(), op.getAIpkmn2());
		            }
		        }
		        playerStartsFirst = !playerStartsFirst;
		    }

		    if (playerWin) {
		        Ball.generateMysteryPokeball(true, op.getAIpkmn1(), op.getAIpkmn2());
		    } else {
		        Ball.generateMysteryPokeball(false, op.getAIpkmn1(), op.getAIpkmn2());
		    }

		    break;
		}

		battle.setAIpkmn1(pokemon1);
		battle.setAIpkmn2(pokemon2);

		player.displayCaughtPokemons();
		Battle points = new Battle();
		int battleScore = battle.calScore(player.getpartyPokemon().size());
		System.out.println("\nBattle Score: " + battleScore);
		battle.displayTopScores();
    }
}
