import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class BattleShipsGame {
	
	public static void printBoard(String [] [] BattleShipBoard) {
		
		// print the column numbers of the grid above
		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
		}
		// go to a new line
		System.out.print("\n");
		// loop through each row in the board and print out the index followed by the row number
		for (int row = 0; row < BattleShipBoard.length; row++) {
			System.out.print(row + "|");
			// loop through each column in the board
			for (int col = 0; col < BattleShipBoard[row].length; col++) {
				// print spaces when the position is free otherwise print the value
				if (BattleShipBoard[row][col] == null) {
					System.out.print(" ");
				} else {
					System.out.print(BattleShipBoard[row][col]);
				}
			}
			System.out.println("|" + row);
		}
		// print the column numbers of the grid below
		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
		}
		
		System.out.print("\n");
	}
	
	public static void compshipDeployment(String [] [] BattleShipBoard, ArrayList<String> compShipsList) {
		// create the random class
		Random random = new Random();
		// initialize a compshipPlacement variable to keep count of how many ships the computer has deployed
		int compshipPlacement = 1;
		// computer deploy ships randomly
		while (compshipPlacement <= 5) {
			// select the x and y coordinates for the computer randomly
			int x = random.nextInt(9);
			int y = random.nextInt(9);
			// convert the integers to string
			String x_str = Integer.toString(x);
			String y_str = Integer.toString(y);
			String compShip = x_str + y_str;
			//System.out.println(compShip);
			// check if the value is within the range
			// check if the place has already been taken
			if (BattleShipBoard[x][y] == null ) {
				// add the compShip to the compShipList if the position has not been taken
				compShipsList.add(compShip);
				//compShips.put(x, y);
				// place the ship on the grid if the position has not been taken
				//BattleShipBoard[x][y] = String.valueOf('#');
				// let the player know when ships are getting deployed
				System.out.println("Ship " + compshipPlacement + " deployed");
				// increase compshipPlacement by 1
				compshipPlacement++;
			}
		}
		//System.out.println(compShipsList);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// welcome the user to the game and print the instructions
		System.out.println("*** Welcome to the Battle Ships game ***");
		System.out.println("** Instructions **");
		System.out.println("You and the computer have five ships each to deploy.\n"
				+ "After the battle starts, choose coordinates to attack the computer's ships.\n"
				+ "Enter the coordinates within the range of 0 and 9 inclusive, while deploying ships as well as during the battle.\n"
				+ "You win the game by sinking all the computer's ships before all your ships are sunk by the computer. Good luck!\n");
		System.out.println("The sea is empty right now");
		System.out.print("\n");
		// create a battleship board with size 10 by 10 using a 2D array
		String [] [] PlayerBattleShipBoard = new String [10][10];

		// call the function printBoard
		BattleShipsGame.printBoard(PlayerBattleShipBoard);
		// Ask the player to deploy ships
		System.out.println("Please Deploy your Ships ");
		Scanner input = new Scanner(System.in);
		// set a boolean valid_entry to false
		//boolean valid_entry = false;
		int playershipPlacement = 1;
		// allow the player to place the ships
		while (playershipPlacement <= 5) {
			// get the input of x and y coordinates from the player
			System.out.println("Enter X coordinate for your " + playershipPlacement + ". ship: ");
			int x = input.nextInt();
			System.out.println("Enter Y coordinate for your " + playershipPlacement + ". ship: ");
			int y = input.nextInt();
			// check if the value is within the range
			if ((0 <= x && x <= 9) && (0 <= y && y <= 9)) {
				// check if the place has already been taken
				if (PlayerBattleShipBoard[x][y] == null ) {
					// place the ship on the grid if the place is empty
					PlayerBattleShipBoard[x][y] = String.valueOf('@');
					// increase the value of shipPlacement by one
					playershipPlacement++;		
					// print the board for the player to view the shipPlacement
					BattleShipsGame.printBoard(PlayerBattleShipBoard);
				} else {
					// if the place has already been taken, let the player know
					System.out.println("A ship already exists in this position. Please choose another position!");
				}
			} else {
				// if the values are out of range, let the player know
			    System.out.println("You have entered a coordiante out of range. Please try again!");
			}
		}
		System.out.println("\n");
		// let the user know that its the computer's turn
		System.out.println("Now its the computer's turn!");
		System.out.println("\n");
		// create a BattleShipBoard for the computer
		//String [] [] ComputerBattleShipBoard = new String [10][10];
		// create an ArrayList to pass in to the compshipsDeployment method
		ArrayList<String> compShipsList = new ArrayList<String>();
		// make the computer deploy ships using the compshipDeployment method
		// pass in the PlayerBattleShipBoard and the compShipsList
		BattleShipsGame.compshipDeployment(PlayerBattleShipBoard, compShipsList);
		System.out.println("\n");
		// print the board for the player
		BattleShipsGame.printBoard(PlayerBattleShipBoard);
		//BattleShipsGame.printBoard(ComputerBattleShipBoard);
		System.out.println("\n");
		System.out.println("Now lets start the battle!");
		// initialize variables indicating how many ships are left for the computer and player
		int playershipsLeft = 5;
		int compshipsLeft = 5;
		// keep the battle going on until all the ships for one of the player or computer are destroyed
		while (playershipsLeft != 0 && compshipsLeft != 0) {
			// create a scanner class to get inputs from the player
			Scanner input_guess = new Scanner(System.in);
			// create a variable to check if the input is within the range
			boolean player_input_inrange = false;
			// create a variable to check if a ship has already been sunk in that position
			boolean ship_not_sunk = false;
			while (player_input_inrange != true || ship_not_sunk != true) {
				// get the coordinate guesses from the player
				System.out.println("Enter the X coordinate: ");
				int player_x = input_guess.nextInt();
				System.out.println("Enter the Y coordinate: ");
				int player_y = input_guess.nextInt();
				String player_input = Integer.toString(player_x) + Integer.toString(player_y);
				// check if the value is within the range
				if ((0 <= player_x && player_x <= 9) && (0 <= player_y && player_y <= 9)) {
					// the player input is in range
					player_input_inrange = true;
					// check if a ship has already been sunk in that place
					if (PlayerBattleShipBoard[player_x][player_y] == null || PlayerBattleShipBoard[player_x][player_y].equals(String.valueOf('@'))) {
						// if the ship has not been sunk
						ship_not_sunk = true;
						// when the player sinks the computers ship
						if (compShipsList.contains(player_input)){
							// let the player know
							System.out.println("Boom!, you sunk the ship!");
							// indicate it in the board
							//ComputerBattleShipBoard[player_x][player_y] = String.valueOf('!');
							PlayerBattleShipBoard[player_x][player_y] = String.valueOf('!');
							// the computer loses one ship
							compshipsLeft -= 1;
							// print the board
							BattleShipsGame.printBoard(PlayerBattleShipBoard);
						}
						// when the player hits an empty space
						else if (PlayerBattleShipBoard[player_x][player_y] == null) {
							// let the player know
							System.out.println("Sorry, you missed");
							// indicate it in the map
							PlayerBattleShipBoard[player_x][player_y] = String.valueOf('-');
							// print the board
							BattleShipsGame.printBoard(PlayerBattleShipBoard);
						}
						// when the player sinks his own ship
						else if (PlayerBattleShipBoard[player_x][player_y].equals(String.valueOf('@'))) {
							// let the player know
							System.out.println("Oh no, you sunk your own ship :(");
							// indicate it in the board
							PlayerBattleShipBoard[player_x][player_y] = String.valueOf('x');
							// the player loses one ship
							playershipsLeft -= 1;
							// print the board
							BattleShipsGame.printBoard(PlayerBattleShipBoard);
						}
					} // otherwise let the player know
					else {
						System.out.println("You have already entered this position. Please choose another position.");
						}
				} // otherwise let the player know 
				else {
					System.out.println("The coordinate you entered does not exist. Please enter a coordinate between 0 and 9 inclusive.");
				}
			}

			System.out.println("Now its the computer's turn");
			//create an ArrayList recording the positions entered by the computer is an empty space
			ArrayList<String> compemptyspaceList = new ArrayList<String>();
			// create a random class
			Random random = new Random();
			// create a variable to check if a ship has already been sunk in the position chosen or if its an already chosen empty space
			boolean comp_pos_ok = false;
			while (comp_pos_ok != true) {
				//make the computer choose coordinates randomly
				int comp_x = random.nextInt(9);
				int comp_y = random.nextInt(9);
				String comp_input = Integer.toString(comp_x) + Integer.toString(comp_y);
				// check if a ship has already been sunk in that place or the computer has already hit that empty space
				if (PlayerBattleShipBoard[comp_x][comp_y] == null || PlayerBattleShipBoard[comp_x][comp_y].equals(String.valueOf('@')) || compemptyspaceList.contains(comp_input)) {
					comp_pos_ok = true;
					// when the computer sinks one of its own ships
					if (compShipsList.contains(comp_input)) {
						// let the player know
						System.out.println("The Computer sunk one of its own ships");
						// indicate it in the board
						//ComputerBattleShipBoard[comp_x][comp_y] = String.valueOf('!');
						PlayerBattleShipBoard[comp_x][comp_y] = String.valueOf('!');
						// the computer loses one ship
						compshipsLeft -= 1;
						//System.out.println(compshipsLeft);
						// print the board
						BattleShipsGame.printBoard(PlayerBattleShipBoard);
					}
					// when the computer hits an empty space
					else if (PlayerBattleShipBoard[comp_x][comp_y] == null) {
						// add that position to compemptyspaceList
						compemptyspaceList.add(comp_input);
						// let the player know
						System.out.println("Computer missed");
						// indicate it in the map
						//PlayerBattleShipBoard[comp_x][comp_y] = String.valueOf('-');
						// print the board
						BattleShipsGame.printBoard(PlayerBattleShipBoard);
					}
					// when the computer sinks the players ship
					else if (PlayerBattleShipBoard[comp_x][comp_y].equals(String.valueOf('@'))) {
						// let the player know
						System.out.println("The Computer sunk one of your ships");
						// indicate it in the board
						PlayerBattleShipBoard[comp_x][comp_y] = String.valueOf('x');
						// the player loses one ship
						playershipsLeft -= 1;
						//System.out.println(playershipsLeft);
						// print the board
						BattleShipsGame.printBoard(PlayerBattleShipBoard);
					}
				}
			}
		}
		if (compshipsLeft == 0) {
			System.out.println("Hooray! You win the battle :)");
		}
		else if (playershipsLeft == 0) {
			System.out.println("You lost the battle");
		}
	}
}
