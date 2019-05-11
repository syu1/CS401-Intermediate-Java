import java.util.Scanner;
import java.io.*;

public class Assig2
{
	public static void main(String[] args) throws IOException
	{
		//instantiates objects
		Die die1 = new Die();
		Die die2 = new Die();
		Scanner sc = new Scanner(System.in);
		Player myPlayer;

		String firstName;
		String lastName;

		//sets default values so logic can run
		boolean continuePlaying = true;
		boolean validCash = false;
		String betChoice = "";
		double totalMoney = 0;
    int roundsPlayed = 0;
    int roundsWon = 0;

		System.out.println("What is your first name?: ");
		firstName = sc.next();
		//checks if the files exists
		File playerFile = new File(firstName);
	  if(playerFile.exists())
		{
			//reads from file line by line and constructs player object from saved information
		  System.out.println("Welcome back: " + firstName);
			Scanner inputFile = new Scanner(playerFile);
			firstName = inputFile.nextLine();
			inputFile.hasNext();
			lastName = inputFile.nextLine();
			inputFile.hasNext();
			totalMoney = inputFile.nextDouble();
			inputFile.hasNext();
			roundsPlayed = inputFile.nextInt();
			inputFile.hasNext();
			roundsWon = inputFile.nextInt();
			inputFile.close();
			myPlayer = new Player(firstName,lastName,totalMoney,roundsPlayed,roundsWon);
	  }
		else
		{
			//creates a new player based information given
		System.out.println("File not found!");
		roundsPlayed = 0;
    roundsWon = 0;
		System.out.println("Our system indicates that this is your first time here!");
		System.out.println("We will need some information so we can remember you next time!\n");
		System.out.println("What is your last name?: ");
		lastName = sc.next();
		//makes sure that they enter with postive amount of dollars
		while(validCash == false)
		{
			System.out.println("How much money are you entering this establishment with?: ");
			totalMoney = sc.nextDouble();
			//greater than 1 penny
			if(totalMoney <= 0.01)
			System.out.println("Please enter an amount greater than zero dollars: ");
			else
			{
				validCash = true;
				System.out.println("\nWelcome to Over Under!");
			}
		}
		//creates player object if there is no existing file
		myPlayer = new Player(firstName,lastName,totalMoney,roundsPlayed,roundsWon);
		}
		System.out.println("Here is your current information");
		System.out.println(myPlayer.toString());
		//Begin game here
		while(continuePlaying == true)
		{
			double betAmount = 0;

			System.out.println("Would you like to play a round(y/n)?");
			String yesNo = sc.next();
			//makes sure that the player has money to play
			if(yesNo.equals("y") && myPlayer.getTotalMoney() > 0)
			{
				//logic to make sure user inputs bet greater than zero
				//logic to make sure user inputs O, S or U
				boolean validBet = false;
				boolean validBetChoice= false;
				while(validBet == false)
				{
					//gets player bet
					System.out.printf("How much would you like to bet(<=%.2f)?: \n",myPlayer.getTotalMoney());
					betAmount = sc.nextDouble();
					if(betAmount <=0)
					{
						System.out.println("Invalid bet must be greater than 0");
					}
					else if(betAmount > myPlayer.getTotalMoney())
					{
						System.out.println("Invalid bet, must less than or equal to your total money");
					}
					else
					{
						//ends loop
						validBet = true;
					}
				}
				//gets the bet choice
				while(validBetChoice == false)
				{
					System.out.println("What is your bet sir: O(ver), U(nder), S(even) (All Capitals Please): ");
					betChoice = sc.next();
					if(betChoice.equals("O") || betChoice.equals("U") || betChoice.equals("S"))
					{
						validBetChoice = true;
					}
					else
					{
						validBetChoice = false;
					}
				}
					//calls rollDie method and gets die numbers
					die1.rollDie();
					die2.rollDie();
					System.out.println("The first die rolls: " + die1.getDieNumber());
					System.out.println("The second die rolls: " + die2.getDieNumber());
					int dieRollNumber = die1.getDieNumber() + die2.getDieNumber();
					System.out.println("The total of the two dies are: "+ dieRollNumber);
					//logic for checking if the player won the round
					if(betChoice.equals("O") && dieRollNumber > 7 )
					{
						myPlayer.addMoney(betAmount);
						myPlayer.roundWon();
					  System.out.println("\nYou have WON this round!");
						System.out.printf("Your winnings are: %.2f!\n",betAmount);
						System.out.println("Your updated money Value is: "+ myPlayer.getTotalMoney());
					}
					else if(betChoice.equals("U") && dieRollNumber < 7)
					{
						myPlayer.addMoney(betAmount);
					  myPlayer.roundWon();
						System.out.println("\nYou have WON this round!");
						System.out.printf("Your winnings are: %.2f !\n",betAmount);
						System.out.println("Your updated money Value is: "+ myPlayer.getTotalMoney());
					}
					else if(betChoice.equals("S") && dieRollNumber == 7)
					{
						myPlayer.addMoney(betAmount*2);
						myPlayer.roundWon();
						System.out.println("\nYou have WON this round!");
						System.out.printf("Your winnings are: %.2f !\n",betAmount*2);
						System.out.println("Your updated money Value is: "+ myPlayer.getTotalMoney());
					}
					else
					{
						myPlayer.subtractMoney(betAmount);
						System.out.println("\nYou have LOST this round!");
						System.out.printf("Your losings are: %.2f !\n",betAmount);
						System.out.println("Your updated money Value is: "+ myPlayer.getTotalMoney());
					}
					myPlayer.roundPlayed();
					System.out.println("Here is your current information");
					System.out.println(myPlayer.toString());

			}
			//if player wishes to quit or has run out of money
			else if(yesNo.equals("n") || myPlayer.getTotalMoney() == 0)
			{
				continuePlaying = false;
				//print statement if player has run out of money
				if(myPlayer.getTotalMoney() == 0)
				{
					System.out.println("Sorry you have run out of money! You connot continue.");
				}

					System.out.println("Thank you for playing");
					System.out.println("Here is your current information");
					System.out.println(myPlayer.toString());
					//makes sure all player information is correct
					firstName= myPlayer.getFirstName();
					lastName= myPlayer.getLastName();
					totalMoney= myPlayer.getTotalMoney();
					roundsPlayed= myPlayer.getRoundsPlayed();
					roundsWon= myPlayer.getRoundsWon();
					//overwrites any existing file and writes the current information
					//regardless if player has existed or not.
					PrintWriter outputFile = new PrintWriter(firstName);
					outputFile.println(firstName);
					outputFile.println(lastName);
					outputFile.println(totalMoney);
					outputFile.println(roundsPlayed);
					outputFile.println(roundsWon);
					outputFile.close();

			}
			//check if user enters invalid statement when asking if player wishes to continue
			else
			{
				System.out.println("Please enter a valid statement(y/n):\n ");
			}
		}
	}
}
