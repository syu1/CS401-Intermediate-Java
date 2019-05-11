import java.util.Random;

public class Die
{
  private int dieRoll;
  //nothing in the constructer
  public Die()
  {
  }
  //accesor method
  public int getDieNumber()
  {
    return dieRoll;
  }
  //mutator method to get randomized die number
  public void rollDie()
  {
    Random roll = new Random();
    dieRoll = roll.nextInt(6)+1;
  }

}
