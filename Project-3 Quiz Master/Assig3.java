import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class Assig3
{
	public static void main(String[] args) throws IOException
	{
		//delarces variables needed in the program
		String question;
		int numAnswers;
		String[] answers;
		int correctAnswer;
		int timesTried;
		int timesCorrect;
		int guess = 100;
		int questionNumber= 0;
		String stringQuestionNumber;
		int easiest = 0;
		int hardest = 0;
		int totalCorrect = 0;
		boolean correctInput;
		ArrayList<Question> quizArray = new ArrayList<Question>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Quiz Program!");
		System.out.print("What is the name of your quiz file(SamQuiz.txt for default): ");
		String quizName = sc.next();

		//begins reading from file in a loop
		File quizFile = new File(quizName);
		Scanner inputFile = new Scanner(quizFile);
	  if(quizFile.exists())
		{
			//reads from file line by line and constructs Question object from saved information, also functions as quiz itself
			while(inputFile.hasNext())
			{
				question = inputFile.nextLine();
				System.out.println("OK! Question Number: "+questionNumber);
				System.out.println(question);

				numAnswers = inputFile.nextInt();
				inputFile.nextLine();
				answers = new String[numAnswers];
				System.out.println("Here are your Answer Choices");
				for(int i = 0; i < numAnswers; i++)
				{
					answers[i] = inputFile.nextLine();
					System.out.println(i+": "+answers[i]);
				}
				correctAnswer = inputFile.nextInt();
				inputFile.nextLine();
				timesTried = inputFile.nextInt();
				inputFile.nextLine();
				timesCorrect = inputFile.nextInt();
				inputFile.nextLine();
				System.out.print("What is your Answer: ");
				correctInput = false;
				//checks for incorrect input
				while(correctInput == false)
				{
					guess = sc.nextInt();
					if(guess>=0 && guess<=numAnswers-1)
					{
						correctInput = true;
					}
					else
					{
						System.out.println("Please enter valid input");
					}
				}
				//creates question object to go into arraylist
				Question questionObject  = new Question(question,numAnswers,answers,correctAnswer,timesTried,timesCorrect,guess);
				questionObject.incrementTimesTried();
				questionNumber+=1;
				quizArray.add(questionObject);

			}
			inputFile.close();
			System.out.println("\nHere are your results");
			//finished the quiz displays results
			for(int j = 0; j <quizArray.size();j++)
			{
				Question myQuestion = quizArray.get(j);
				System.out.println(myQuestion.returnQuestion());
				System.out.println(myQuestion.returnAnswer());
				System.out.println(myQuestion.returnGuess());
				System.out.println(myQuestion.checkAnswer());
				if(myQuestion.checkAnswerTrue()==true)
				{
					myQuestion.incrementTimesCorrect();
					totalCorrect++;
				}
				else
				{

				}

	  	}
			//provides some statistics
			System.out.println("Your overall performance:");
			System.out.println("Right: "+ totalCorrect);
			System.out.println("Wrong: "+ (quizArray.size()-totalCorrect));
			System.out.println("Pct: "+(totalCorrect*1.0)/quizArray.size());
			System.out.println("Here are some cumulative statistics");

			double easiestPercent = quizArray.get(0).returnPercent();
			double hardestPercent = quizArray.get(0).returnPercent();

			for(int k = 0; k <quizArray.size();k++)
			{
				Question aQuestion = quizArray.get(k);
				System.out.println(aQuestion.returnQuestion());
				System.out.println(aQuestion.returnStatistcs());
				if(aQuestion.returnPercent() > easiestPercent )
				{
					easiestPercent = aQuestion.returnPercent();
					easiest = k;
				}
				else
				{
				}
				if(aQuestion.returnPercent() < hardestPercent)
				{

					hardestPercent = aQuestion.returnPercent();
					hardest = k;

				}
				else
				{}
			}
			//code to create easiest question info and hardest question
			System.out.println("Easiest Question: ");
			Question easyQuestion  = quizArray.get(easiest);
			System.out.println(easyQuestion.returnQuestion());
			System.out.println("\tTimes Tried: "+ easyQuestion.returnTimesTried());
			System.out.println("\tTimes Correct: " +easyQuestion.returnTimesCorrect());
			System.out.println("\tPercentage Correct: "+easyQuestion.returnPercent());

			System.out.println("Hardest Question: ");
			Question hardQuestion  = quizArray.get(hardest);
			System.out.println(hardQuestion.returnQuestion());
			System.out.println("\tTimes Tried: "+ hardQuestion.returnTimesTried());
			System.out.println("\tTimes Correct: " +hardQuestion.returnTimesCorrect());
			System.out.println("\tPercentage Correct: "+hardQuestion.returnPercent());
			//sends outputFile to txt. uses 2 for loops to iterate all the way through
			PrintWriter outputFile = new PrintWriter(quizName);
			for(int g=0; g<quizArray.size();g++)
			{
				Question outPutQuestion = quizArray.get(g);
				outputFile.println(outPutQuestion.toQuestion());
				outputFile.println(outPutQuestion.toNumAnswers());
				for(int a= 0; a < outPutQuestion.toNumAnswers();a++)
				{
					String[] outPutArray = outPutQuestion.toAnswers();
					outputFile.println(outPutArray[a]);
				}
				outputFile.println(outPutQuestion.toCorrectAnswer());
				outputFile.println(outPutQuestion.returnTimesTried());
				outputFile.println(outPutQuestion.returnTimesCorrect());
			}
			outputFile.close();
		}
		//file was not found
		else
		{
			System.out.println("File not found!");
		}
	}
}
