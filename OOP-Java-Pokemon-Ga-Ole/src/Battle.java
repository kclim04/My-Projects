import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Battle {
	//Attributes
	private Pokemon AIpkmn1;
	private Pokemon AIpkmn2;
	private String stage;
	private Scanner input;
	public String playerCommand;
	private Random random;
	
	//Constructor
	public Battle(Pokemon AIpkmn1, Pokemon AIpkmn2) {
		this.AIpkmn1 = AIpkmn1;
		this.AIpkmn2 = AIpkmn2;
		this.input = new Scanner(System.in);
		this.random = new Random();
	}
	
	public Battle(String stage) {
		this.stage = stage;
		this.input = new Scanner(System.in);
		this.random = new Random();
	}
	
	public Battle() {
		this.input = new Scanner(System.in);
		this.random = new Random();
	}
	
	//getter
	public Pokemon getAIpkmn1() {
		return AIpkmn1;
	}
	
	public Pokemon getAIpkmn2() {
		return AIpkmn2;
	}
	
	public String getStage() {
		return stage;
	}
	
	public Random getRandom() {
		return random;
	}
	
	//setter
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public void setRandom(Random random) {
		this.random = random;
	}
	
	//method for get pkmn detail in battle
	public static void AIpkmndetailBattle(Pokemon AIpkmn1, Pokemon AIpkmn2) {
		System.out.println("\nOpponent Pokemon 1 Details:");
	    System.out.println("Name: " + AIpkmn1.getName());
	    System.out.println("Type: " + AIpkmn1.getType());
	    System.out.println("Move Type: " + AIpkmn1.getMoveType());
	    System.out.println("HP: " + AIpkmn1.getHp());
	    System.out.println("Attack: " + AIpkmn1.getAtk());
	    System.out.println("Defense: " + AIpkmn1.getDef());
	    System.out.println("Speed: " + AIpkmn1.getSpd());
		
		System.out.println("\nOpponent Pokemon 2 Details:");
	    System.out.println("Name: " + AIpkmn2.getName());
	    System.out.println("Type: " + AIpkmn2.getType());
	    System.out.println("Move Type: " + AIpkmn2.getMoveType());
	    System.out.println("HP: " + AIpkmn2.getHp());
	    System.out.println("Attack: " + AIpkmn2.getAtk());
	    System.out.println("Defense: " + AIpkmn2.getDef());
	    System.out.println("Speed: " + AIpkmn2.getSpd());
	}
	
	//method for generate pkmn in battle
	public static Pokemon getRandomBattle(Pokemon[] pokemonArray, String stage) {
		Random random = new Random();
		int randomIndex = random.nextInt(pokemonArray.length);
		return pokemonArray[randomIndex];
	}
	
	public static boolean miniGame() {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int userGuess;
		int randomNum = random.nextInt(4) + 1;
		System.out.println("Its mini-game time! Guess the correct number and you will gain stat boost!");
		while(true) {
			try {
				System.out.println("Guess a number between 1 and 4 inclusive:");
				userGuess = input.nextInt();
				boolean miniGameResult = userGuess == randomNum;
				if(miniGameResult) {
					System.out.println("Congratulations! You guessed correctly.");
				}
				else {
					System.out.println("Oops! You've guessed incorrectly. The correct number was: " + randomNum);
				}
				return miniGameResult;
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a valid integer.");
				input.next();
			}
		}
	}
	
	public void MyTurnChoose(Pokemon MYpkmn1, Pokemon MYpkmn2, Pokemon AIpkmn1, Pokemon AIpkmn2) {
		int pkchoice;
		Scanner input = new Scanner(System.in);
		Battle mt = new Battle();
		while(true) {
			try {
				System.out.println("\nWhich pokemon would you like to send out? (1 or 2)");
				pkchoice = input.nextInt();
				if (pkchoice == 1 && MYpkmn1.getHp()>0) {
					mt.myTurn(AIpkmn1, AIpkmn2, MYpkmn1);
					break;
				}
				else if (pkchoice == 1 && MYpkmn1.getHp()<=0) {
					System.out.println(MYpkmn1.getName() + "has fainted!");
					System.out.println("Please choose another Pokemon.");
				}
				else if (pkchoice == 2 && MYpkmn2.getHp()>0) {
					mt.myTurn(AIpkmn1, AIpkmn2, MYpkmn2);
					break;
				}
				else if (pkchoice == 2 && MYpkmn2.getHp()<=0) {
					System.out.println(MYpkmn2.getName() + "has fainted!");
					System.out.println("Please choose  another Pokemon.");
				}
				else {
					System.out.println("Invalid choice, please choose again.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a valid integer");
				input.next();
			}
		}
	}
	
	public void TurnAI(Pokemon MYpkmn1, Pokemon MYpkmn2, Pokemon AIpkmn1) {
		Battle mt = new Battle();
		int OneTwo = (Pokemon.generateAI());
		if (OneTwo == 1 && MYpkmn1.getHp()>0) {
			mt.AITurn(AIpkmn1, MYpkmn1);
		}
		else if (OneTwo == 1 && MYpkmn1.getHp()<=0) {
			mt.AITurn(AIpkmn1, MYpkmn2);
		}
		else if (OneTwo == 2 && MYpkmn2.getHp()>0) {
			mt.AITurn(AIpkmn1, MYpkmn2);
		}
		else {
			mt.AITurn(AIpkmn1, MYpkmn1);
		}
	}
	
	public void AITurnChoose(Pokemon MYpkmn1, Pokemon MYpkmn2, Pokemon AIpkmn1, Pokemon AIpkmn2) {
		System.out.println("\n-------Opponent's turn to attack!-------\n");
		Battle at = new Battle();
		int TwoOne = Pokemon.generateAI();
		if (TwoOne == 1 && AIpkmn1.getHp()>0) {
			at.TurnAI(MYpkmn1, MYpkmn2, AIpkmn1);
		}
		else if (TwoOne == 1 && AIpkmn1.getHp()<=0) {
			at.TurnAI(MYpkmn1, MYpkmn2, AIpkmn2);
		}
		else if (TwoOne == 2 && AIpkmn2.getHp()>0) {
			at.TurnAI(MYpkmn1, MYpkmn2, AIpkmn2);
		}
		else if (TwoOne == 2 && AIpkmn2.getHp()<=0) {
			at.TurnAI(MYpkmn1, MYpkmn2, AIpkmn1);
		}
		else {
			System.out.println("Heh? You shouldnt be here :D (Easter Egg(?))");
		}
	}
	
	public static void displayPokemonHealth(Pokemon op1, Pokemon op2) {
		System.out.println("\n----------------------------------------------------------------------------------------");
		System.out.println(op1.getName() + " has " + op1.getHp() + " HP remaining!");
		System.out.println(op2.getName() + " has " + op2.getHp() + " HP remaining!");
		System.out.println("----------------------------------------------------------------------------------------\n");
	}
	
	//method to determine type effectiveness
	public static double determineTypeEffective(String attackerType, String defenderType) {
		//default
		double effectiveness = 1.0;
		//super effective
        if (attackerType.contains("Fire") && defenderType.contains("Grass") || 
            attackerType.contains("Fire") && defenderType.contains("Ice") ||     
            attackerType.contains("Fire") && defenderType.contains("Bug") ||   
            attackerType.contains("Fire") && defenderType.contains("Steel") ||    
            attackerType.contains("Water") && defenderType.contains("Fire") ||    
            attackerType.contains("Water") && defenderType.contains("Ground") ||   
            attackerType.contains("Water") && defenderType.contains("Rock") ||   
            attackerType.contains("Grass") && defenderType.contains("Water") ||  
            attackerType.contains("Grass") && defenderType.contains("Ground") ||   
            attackerType.contains("Grass") && defenderType.contains("Rock") ||     
            attackerType.contains("Electric") && defenderType.contains("Water") || 
            attackerType.contains("Electric") && defenderType.contains("Flying") ||  
           	attackerType.contains("Ice") && defenderType.contains("Grass") ||    
            attackerType.contains("Ice") && defenderType.contains("Ground") ||   
            attackerType.contains("Ice") && defenderType.contains("Flying") ||   
            attackerType.contains("Ice") && defenderType.contains("Dragon") ||  
            attackerType.contains("Fighting") && defenderType.contains("Normal") || 
            attackerType.contains("Fighting") && defenderType.contains("Ice") || 
            attackerType.contains("Fighting") && defenderType.contains("Rock") ||
            attackerType.contains("Fighting") && defenderType.contains("Dark") ||  
           	attackerType.contains("Fighting") && defenderType.contains("Steel") || 
            attackerType.contains("Poison") && defenderType.contains("Fairy") || 
            attackerType.contains("Poison") && defenderType.contains("Grass") ||   
            attackerType.contains("Psychic") && defenderType.contains("Fighting") || 
            attackerType.contains("Psychic") && defenderType.contains("Poison") ||   
            attackerType.contains("Ground") && defenderType.contains("Rock") || 
            attackerType.contains("Ground") && defenderType.contains("Fire") || 
            attackerType.contains("Ground") && defenderType.contains("Electric") || 
            attackerType.contains("Ground") && defenderType.contains("Poison") ||   
           	attackerType.contains("Ground") && defenderType.contains("Steel") ||  
           	attackerType.contains("Rock") && defenderType.contains("Flying") ||   
           	attackerType.contains("Rock") && defenderType.contains("Fire") ||   
           	attackerType.contains("Rock") && defenderType.contains("Ice") || 
           	attackerType.contains("Rock") && defenderType.contains("Bug") ||   
           	attackerType.contains("Flying") && defenderType.contains("Fighting") || 
           	attackerType.contains("Flying") && defenderType.contains("Grass") ||   
           	attackerType.contains("Flying") && defenderType.contains("Bug") ||    
           	attackerType.contains("Bug") && defenderType.contains("Grass") ||  
           	attackerType.contains("Bug") && defenderType.contains("Psychic") || 
           	attackerType.contains("Bug") && defenderType.contains("Dark") ||     
           	attackerType.contains("Ghost") && defenderType.contains("Ghost") || 
           	attackerType.contains("Ghost") && defenderType.contains("Psychic") || 
           	attackerType.contains("Dragon") && defenderType.contains("Dragon") ||
           	attackerType.contains("Dark") && defenderType.contains("Psychic") ||  
           	attackerType.contains("Dark") && defenderType.contains("Ghost") ||              	
           	attackerType.contains("Steel") && defenderType.contains("Fairy") ||  
            attackerType.contains("Steel") && defenderType.contains("Ice") ||  
            attackerType.contains("Steel") && defenderType.contains("Rock") || 
            attackerType.contains("Fairy") && defenderType.contains("Fighting") ||  
            attackerType.contains("Fairy") && defenderType.contains("Dragon") || 
            attackerType.contains("Fairy") && defenderType.contains("Dark")) {
            effectiveness = 2.0;     
            System.out.println("Super Efective!!");
        }
        //not very effective
        else if(defenderType.contains("Grass") && attackerType.contains("Water") ||
       		 defenderType.contains("Water") && attackerType.contains("Water") ||
       		 defenderType.contains("Dragon") && attackerType.contains("Water") ||
       		 defenderType.contains("Fire") && attackerType.contains("Fire") ||
       		 defenderType.contains("Water") && attackerType.contains("Fire") ||
       		 defenderType.contains("Rock") && attackerType.contains("Fire") ||
       		 defenderType.contains("Dragon") && attackerType.contains("Fire") ||
       		 defenderType.contains("Fire") && attackerType.contains("Grass") ||
       		 defenderType.contains("Grass") && attackerType.contains("Grass") ||
       		 defenderType.contains("Poison") && attackerType.contains("Grass") ||
       		 defenderType.contains("Flying") && attackerType.contains("Grass") ||
       		 defenderType.contains("Bug") && attackerType.contains("Grass") ||
       		 defenderType.contains("Dragon") && attackerType.contains("Grass") ||
       		 defenderType.contains("Steel") && attackerType.contains("Grass") ||
       		 defenderType.contains("Electric") && attackerType.contains("Electric") ||
       		 defenderType.contains("Grass") && attackerType.contains("Electric") ||
       		 defenderType.contains("Dragon") && attackerType.contains("Electric") ||
       		 defenderType.contains("Fire") && attackerType.contains("Ice") ||
	       	 defenderType.contains("Water") && attackerType.contains("Ice") ||
	       	 defenderType.contains("Ice") && attackerType.contains("Ice") ||
	       	 defenderType.contains("Steel") && attackerType.contains("Ice") ||
	       	 defenderType.contains("Poison") && attackerType.contains("Fighting") ||
	       	 defenderType.contains("Flying") && attackerType.contains("Fighting") ||
	       	 defenderType.contains("Psychic") && attackerType.contains("Fighting") ||
	       	 defenderType.contains("Bug") && attackerType.contains("Fighting") ||
	       	 defenderType.contains("Fairy") && attackerType.contains("Fighting") ||
	       	 defenderType.contains("Poison") && attackerType.contains("Poison") ||
	       	 defenderType.contains("Ground") && attackerType.contains("Poison") ||
	       	 defenderType.contains("Rock") && attackerType.contains("Poison") ||
	         defenderType.contains("Ghost") && attackerType.contains("Poison") ||
	       	 defenderType.contains("Grass") && attackerType.contains("Ground") ||
	       	 defenderType.contains("Bug") && attackerType.contains("Ground") ||
	       	 defenderType.contains("Electric") && attackerType.contains("Flying") ||
       		 defenderType.contains("Rock") && attackerType.contains("Flying") ||
       		 defenderType.contains("Steel") && attackerType.contains("Flying") ||
       		 defenderType.contains("Psychic") && attackerType.contains("Psychic") ||
       		 defenderType.contains("Steel") && attackerType.contains("Psychic") ||
       		 defenderType.contains("Fire") && attackerType.contains("Bug") ||
       		 defenderType.contains("Fighting") && attackerType.contains("Bug") ||
       		 defenderType.contains("Poison") && attackerType.contains("Bug") ||
       		 defenderType.contains("Flying") && attackerType.contains("Bug") ||
       		 defenderType.contains("Ghost") && attackerType.contains("Bug") ||
       		 defenderType.contains("Steel") && attackerType.contains("Bug") ||
       		 defenderType.contains("Fairy") && attackerType.contains("Bug") ||
       		 defenderType.contains("Rock") && attackerType.contains("Fighting") ||
       		 defenderType.contains("Ground") && attackerType.contains("Fighting") ||
       		 defenderType.contains("Steel") && attackerType.contains("Fighting") ||
       		 defenderType.contains("Dark") && attackerType.contains("Fighting") ||
       		 defenderType.contains("Dark") && attackerType.contains("Dark") ||
       		 defenderType.contains("Fairy") && attackerType.contains("Dark") ||
       		 defenderType.contains("Fire") && attackerType.contains("Steel") ||
       		 defenderType.contains("Water") && attackerType.contains("Steel") ||
       		 defenderType.contains("Electric") && attackerType.contains("Steel") ||
       		 defenderType.contains("Steel") && attackerType.contains("Steel") ||
       		 defenderType.contains("Fairy") && attackerType.contains("Fire") ||
       		 defenderType.contains("Poison") && attackerType.contains("Fire") ||
       		 defenderType.contains("Steel") && attackerType.contains("Fire") ||
       		 defenderType.contains("Dark") && attackerType.contains("Ghost") ||
       		 defenderType.contains("Steel") && attackerType.contains("Dragon") ||
       		 defenderType.contains("Rock") && attackerType.contains("Normal") ||
       		 defenderType.contains("Steel") && attackerType.contains("Normal")) {
       		 effectiveness = 0.5;
       		 System.out.println("Not very Effective");
       	 }
        //no effect
        else if(attackerType.contains("Electric") && defenderType.contains("Ground") ||
       		attackerType.contains("Fighting") && defenderType.contains("Ghost") ||
       		attackerType.contains("Poison") && defenderType.contains("Steel") ||
       		attackerType.contains("Dark") && defenderType.contains("Psychic") ||
       		attackerType.contains("Flying") && defenderType.contains("Ground") ||
       		attackerType.contains("Normal") && defenderType.contains("Ghost") ||
       		attackerType.contains("Dragon") && defenderType.contains("Fairy") || 
       		attackerType.contains("Rock") && defenderType.contains("Normal")){
        	effectiveness = 0.0;
        	System.out.println("No Effect");
        }
        else {
        	System.out.println("Effective");
        }
        return effectiveness;
	}
	
	@SuppressWarnings("unused")
	private void displayPokemonDetail(Pokemon pokemon) {
		System.out.println("Type:" + pokemon.getType());
		System.out.println("Move Type:" + pokemon.getMoveType());
		System.out.println("HP:" + pokemon.getHp());
		System.out.println("Attack:" + pokemon.getAtk());
		System.out.println("Defense:" + pokemon.getDef());
		System.out.println("Speed:" + pokemon.getSpd());
	}
	
	public void setAIpkmn1(Pokemon chooseAIpkmn) {
		this.AIpkmn1 = chooseAIpkmn;
	}
	
	public void setAIpkmn2(Pokemon chooseAIpkmn) {
		this.AIpkmn2 = chooseAIpkmn;
	}
	
	public void myTurnCalDMG(double stat, double newhp, Pokemon AIpkmn, Pokemon pk) {
		double playerEffective = Battle.determineTypeEffective(pk.getMoveType(), AIpkmn.getType());
		int playerDamage = (int) (stat*pk.getAtk()/AIpkmn.getDef()*playerEffective);
		System.out.println("Damage received: " + playerDamage + "HP");
		newhp = Math.max(AIpkmn.getHp() - playerDamage, 0);
		AIpkmn.setHp(newhp);
	}
	
	public void myTurn(Pokemon AIpkmn1, Pokemon AIpkmn2, Pokemon pk) {
		Scanner input = new Scanner(System.in);
		Pokemon.checkhp(AIpkmn1, AIpkmn2);
		while(true) {
			try {
				double newhp = 0;
				double newhp2 = 0;
				System.out.println("\nChoose a pokemon to attack. (1 or 2)");
				int opkchoice = input.nextInt();
				if (opkchoice == 1 && AIpkmn1.getHp()>0) {
					boolean miniGameResult = miniGame();
					if(miniGameResult) {
						System.out.println("Congratulations! You've won the mini-game. Your attack damage has increased!");
						Battle mt = new Battle();
						System.out.println("\nGo " + pk.getName() + "! Use " + pk.getMoveName() + "!\n");
						mt.myTurnCalDMG(30.0, newhp, AIpkmn1, pk);
					}
					else {
						System.out.println("You've lost the mini-game. Proceeding with the attack.");
						Battle mt = new Battle();
						System.out.println("\nGo " + pk.getName() + "! Use " + pk.getMoveName() + "!\n");
						mt.myTurnCalDMG(20.0, newhp, AIpkmn1, pk);
					}
					break;
				}
				else if(opkchoice == 2 && AIpkmn2.getHp()>0) {
					boolean miniGameResult = miniGame();
					if (miniGameResult) {
						System.out.println("Congratulations! You've won the mini-game. Your attack damage has increased!");
						Battle mt = new Battle();
						System.out.println("\nGo " + pk.getName() + "! Use " + pk.getMoveName() + "!\n");
						mt.myTurnCalDMG(30.0, newhp2, AIpkmn2, pk);
					}
					else {
						System.out.println("You've lost the mini-game. Proceeding with the attack.");
						Battle mt = new Battle();
                    	System.out.println("\nGo " + pk.getName() + "! Use " + pk.getMoveName() + "\n");
                        mt.myTurnCalDMG(20.0, newhp2, AIpkmn2, pk);
					}
					break;
				}
				else if(opkchoice == 1 && AIpkmn1.getHp()<=0) {
					System.out.println(AIpkmn1.getName() + "has already fainted.\nChoose again.");
				}
				else if (opkchoice == 2 && AIpkmn2.getHp()<=0) {
					System.out.println(AIpkmn2.getName() + "has already fainted.\nChoose again.");
				}
				else {
					System.out.println("Invalid choice, choose again.");
				}
			}
			catch(InputMismatchException e){
				System.out.println("Invalid choice, please enter a valid integer.");
				input.hasNext();
			}
		}
	}
	
	public void AITurn(Pokemon AIpkmn, Pokemon pk) {
		double newhp = 0;
		boolean miniGameResult = miniGame();
		if(miniGameResult) {
			System.out.println("Congratulatons! You won the mini-game. Your defense stats has increased.");
			System.out.println("\n" + AIpkmn.getName() + " attacks " + pk.getName() + ".\n");
			Battle mt = new Battle();
			mt.myTurnCalDMG(5.0, newhp, pk, AIpkmn);
		}
		else {
			System.out.println("You've lost the mini-game. No increase in defense stats.");
			Battle mt = new Battle();
			System.out.println("\n" + AIpkmn.getName() + " attacks " + pk.getName() + ".\n");
			mt.myTurnCalDMG(10.0, newhp, pk, AIpkmn);
		}
	}
	
	public int points(int point) {
		return point;
	}
	
	public int calScore(int point) {
		Pokemon playerpkmn1 = getAIpkmn1();
		Pokemon playerpkmn2 = getAIpkmn2();
		Battle pt = new Battle();
		double totalremainhp = playerpkmn1.getHp() + playerpkmn2.getHp();
		int score = (int) totalremainhp;
		if (totalremainhp >= 100) {
			score = 100000;
		}
		else if (totalremainhp >= 90) {
			score = 90000;
		}
		else if (totalremainhp >= 80) {
			score = 80000;
		}
		else if (totalremainhp >= 70) {
			score = 70000;
		}
		else if (totalremainhp >= 60) {
			score = 60000;
		}
		else if (totalremainhp >= 50) {
			score = 55000;
		}
		else if (totalremainhp >= 40) {
			score = 50000;
		}
		else if (totalremainhp >= 30) {
			score = 30000;
		}
		else if (totalremainhp >= 20) {
			score = 20000;
		}
		else if (totalremainhp >= 10) {
			score = 10000;
		}
		else if (totalremainhp >= 5) {
			score = 8000;
		}
		score = score + (pt.points(point)*1000);
		saveTopScore(score);
		return score;
	}
	
	public void displayTopScores() {
		try {
			File file = new File("top_scores.txt");
			if(!file.exists()) {
				System.out.println("Top scores file doesn't exist yet.");
				return;
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			List<Integer>topScores = new ArrayList<>();
			while((line = reader.readLine())!=null) {
				topScores.add(Integer.parseInt(line));
			}
			reader.close();
			//sort score in descending order
			topScores.sort(Collections.reverseOrder());
			System.out.println("Top 5 Scores:");
			for (int i = 0; i < Math.min(5, topScores.size()); i++) {
				System.out.println((i+1) + "." + topScores.get(i));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveTopScore(int score) {
		try{
			File file = new File("top_scores.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<Integer>topScores = new ArrayList<>();
			String line;
			while((line=reader.readLine())!=null) {
				topScores.add(Integer.parseInt(line));
			}
			reader.close();
			topScores.add(score);
			topScores.sort(Collections.reverseOrder());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < Math.min(5, topScores.size()); i++) {
				writer.write(topScores.get(i) + "\n");
			}
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
