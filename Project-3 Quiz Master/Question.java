import java.util.Scanner;
import java.io.*;

public class Question
{

	private String question;
  private int numAnswers;
	private String[] answers;
	private int correctAnswer;
	private int timesTried;
	private int timesCorrect;
	private int guess;

	public Question(String question, int numAnswers, String[] answers, int correctAnswer, int timesTried, int timesCorrect, int guess)
	{
		this.question = question;
		this.numAnswers = numAnswers;

		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.timesTried = timesTried;
		this.timesCorrect = timesCorrect;
		this.guess = guess;
	}
	public String toQuestion()
	{
		return question;
	}
	public int toNumAnswers()
	{
		return numAnswers;
	}
	public String[] toAnswers()
	{
		return answers;
	}
	public int toCorrectAnswer()
	{
		return correctAnswer;
	}

	public int returnTimesTried()
	{
		return timesTried;
	}
	public int returnTimesCorrect()
	{
		return timesCorrect;
	}

	public String returnQuestion()
	{
		return ("The question was: "+question);
	}
	public String returnAnswer()
	{
		String myAnswer = answers[correctAnswer];
		return ("The Correct Answer was: "+myAnswer);
	}
	public String returnGuess()
	{
		String myGuess = answers[guess];
		return ("Your guess was: "+myGuess);
	}
	public String checkAnswer()
	{
		if(answers[guess].equals(answers[correctAnswer]))
		{
			return "\tCorrect! Nice Work!	";
		}
		else
		{
			return "\tIncorrect! Sorry!";
		}
	}
	public boolean checkAnswerTrue()
	{
		if(answers[guess].equals(answers[correctAnswer]))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		public void incrementTimesTried()
		{
			timesTried++;
		}
		public void incrementTimesCorrect()
		{
			timesCorrect++;
		}


	public String returnStatistcs()
	{
		double percentage = (timesCorrect/(timesTried*1.0))*100;
		return ("Times tried: "+ timesTried+"\nTimes correct: "+ timesCorrect+ "\nPercent Correct: "+percentage);
	}
	public double returnPercent()
	{
		double percentage = (timesCorrect/(timesTried*1.0))*100;
		return percentage;
	}

}
