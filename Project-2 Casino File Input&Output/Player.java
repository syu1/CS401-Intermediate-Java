public class Player
{
  //creates private varibles used in this class
  private String firstName;
  private String lastName;
  private double totalMoney;
  private int roundsPlayed;
  private int roundsWon;
  //constructor which takes several variables to create player object
  public Player(String firstName, String lastName, double totalMoney, int roundsPlayed,int roundsWon)
  {
    //takes input from construction and assigns input to private variables
    //needs this.ect to avoid any scope confusion, this represents the private variables
    this.firstName = firstName;
    this.lastName = lastName;
    this.totalMoney = totalMoney;
    this.roundsPlayed = roundsPlayed;
    this.roundsWon = roundsWon;
  }
  //accesor
  public String getFirstName()
  {
    return firstName;
  }
  //accesor
  public String getLastName()
  {
    return lastName;
  }
  //mutator
  public void roundWon()
  {
    roundsWon++;
  }
  //mutator
  public void roundPlayed()
  {
    roundsPlayed++;
  }
  //mutator
  public void addMoney(double newMoney)
  {
    totalMoney+=newMoney;
  }
  //mutator
  public void subtractMoney(double newMoney)
  {
    totalMoney-=newMoney;
  }
  //accesor
  public double getTotalMoney()
  {
    return totalMoney;
  }
  //accesor
  public int getRoundsPlayed()
  {
    return roundsPlayed;
  }
  //accesor
  public int getRoundsWon()
  {
    return roundsWon;
  }
  //accesor, compiles player information to pleasent txt formation
  public String toString()
  {
    
     String str = String.format("\tName: %s %s\n\tMoney left: %.2f\n\tTotal Rounds Played: %d\n\tTotal Rounds Won: %d\n",firstName,lastName,totalMoney,roundsPlayed,roundsWon);
     return str;
  }
}
