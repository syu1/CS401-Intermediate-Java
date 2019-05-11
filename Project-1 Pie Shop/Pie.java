//Sam Yu
//Wednesday, September 30, 2015
//Professor Laboon
//This program simulates a cash register
//at a pizza, pie and jewelry shop.
//This program takes orders tabulates the bill
//and dispenses change.
import java.util.Scanner;
public class Pie
{

  public static void main(String args[])
  {
    //declared variables
    Scanner sc = new Scanner(System.in);
    boolean isCustomer = true;
    boolean applyCardDiscount = false;
    boolean correctCardInput = false;
    int menuChoice;
    int plain;
    int pep;
    int cherryPies;
    int cherrySlices;
    int gCharms;
    int orderNum;
    int hasCustomer;
    double subTotal;
    double total;
    double tax;
    double payment;
    double change;
    while(isCustomer == true)
    {
      //assigned variables so it refreshes everytime loop runs
      applyCardDiscount = false;
      correctCardInput = false;
      menuChoice = 0;
      plain = 0;
      pep = 0;
      cherryPies = 0;
      cherrySlices = 0;
      gCharms = 0;
      orderNum = 0;
      hasCustomer = 0;
      subTotal = 0;
      total = 0;
      tax = 0;
      payment = 0;
      change = 0;

      System.out.println("\nIs there a customer in line?(1=yes, 2=no): ");
      hasCustomer = sc.nextInt();

      //start of customer check
      if (hasCustomer == 1)
      {
        //Start of card check
        while(correctCardInput == false)
        {
          System.out.println("Does the customer have a pie card?(1=yes, 2=no): ");
          int hasCard = sc.nextInt();
          if(hasCard == 1)
          {
            System.out.println("\nYou get a discount, here is the discounted menu\n");
            System.out.println("Plain: $10.00");
            System.out.println("Pepperoni: $10.00(saved $2) ");
            System.out.println("Cherry Pies,\n$1.75 per slice(saved $.25/slice) or $8.00 per Pie(saved $2 a pie) [6 slices per Pie]");
            System.out.println("Charms: $45.00(saved 10%) [14k gold]\n");
            System.out.println("Finally any orders $100 or more will recive a additional 10% discount!\n");

            applyCardDiscount= true;
            correctCardInput =true;
            //Show what discounts the customers get!
          }
          else if(hasCard == 2)
          {
            //show normal menu
            System.out.println("\nSorry no discounts, here is the menu\n");
            System.out.println("Plain: $10.00");
            System.out.println("Pepperoni: $12.00");
            System.out.println("Cherry Pies,\n$2.00 per slice or $10.00 per Pie [6 slices per Pie]");
            System.out.println("Charms: $50.00 [14k gold]\n");
            correctCardInput = true;
          }
          else
          {
          }
        }

        //menu choices start here
        while(menuChoice != 4)
        {
          //add discount system
          System.out.print("Please choose an option: \n1) Update Pizza Order\n2) Update Pie Order\n3) Update Charms Order\n4) Checkout\n");
          menuChoice = sc.nextInt();
          if(menuChoice==1)
          {
            System.out.println("Here is your current order: \n"+pep+" Pepperoni and "+plain+" plain pizzas ordered.\n");

            System.out.println("How many plain pizzas would you like for $10.00 each: ");
            orderNum = sc.nextInt();
            plain+= checkNegative(orderNum);

            if(applyCardDiscount == true)
              System.out.println("How many pepperoni pizzas would you like for $10.00 each: ");
            else
              System.out.println("How many pepperoni pizzas would you like for $12.00 each: ");
            //neg number check
            orderNum = sc.nextInt();
            pep+= checkNegative(orderNum);

          }
          else if(menuChoice==2)
          {
            System.out.println("Here is your current order: "+cherrySlices+" slices and "+cherryPies+" pies.");

            if(applyCardDiscount == true)
            System.out.println("\nHow many slices of cherry pie would you like for\n$1.75 a slice\n$8.00 per pie(6 slices) ");
            else
            System.out.println("\nHow many slices of cherry pie would you like for\n$2.00 a slice\n$10.00 per pie(6 slices) ");

            orderNum = sc.nextInt();
            cherrySlices+=checkNegative(orderNum);

            if(cherrySlices > 0)
            {
              //mathematics to turn cherryslices into pies
              cherryPies = cherryPies + cherrySlices/6;
              cherrySlices = cherrySlices%6;
            }
          }
          else if(menuChoice==3)
          {
            System.out.println("Here is your current order pie charms: "+ gCharms);

            if(applyCardDiscount == true)
            System.out.println("How many charms would you like for $45: ");
            else
            System.out.println("How many charms would you like for $50: ");

            orderNum = sc.nextInt();
            gCharms += checkNegative(orderNum);
          }
          else if(menuChoice==4)
          {
            //Begin subtotal, total, tax and discount calculations here
            System.out.println("Here is your subtotal\n");
            if(applyCardDiscount == true)
            {
                System.out.println("\t"+plain+" plain pizzas at $10.00ea:\t\t$"+plain*10.0);
                System.out.println("\t"+pep+" pepperoni at $10.00ea:\t\t$"+pep*10.0);
                System.out.println("\t"+cherrySlices+" cherry slices at $1.75ea:\t\t$"+cherrySlices*1.75);
                System.out.println("\t"+cherryPies+" cherry pies at $8.00ea:\t\t$"+cherryPies*8.0);
                System.out.println("\t"+gCharms+" gold charms at $45.00ea:\t\t$"+gCharms*45.0);

                System.out.println("\t\t\t\t\t\t-------");

                subTotal = plain*10.0 + pep*10.0 + cherrySlices*1.75 + cherryPies*8.0 + gCharms*45.0;
                if(subTotal < 100)
                System.out.printf("\tSubtotal:\t\t\t\t$%.2f\n",subTotal);
                else
                {
                  System.out.printf("\tSubtotal:\t\t\t\t$%.2f\n",subTotal);
                  double discount = subTotal*.10;
                  System.out.printf("\tBonus discount of 10%%:\t\t\t$%.2f\n",discount);
                  subTotal = subTotal*.90;
                  System.out.println("\t\t\t\t\t\t-------");

                  System.out.printf("\tNew subtotal:\t\t\t\t$%.2f\n",subTotal);
                }

                double savedTotal = (plain*10.0 + pep*12.0 + cherrySlices*2.00 + cherryPies*10.0 + gCharms*50.0) - subTotal;
                tax = subTotal *.07;
                System.out.printf("\tTax:\t\t\t\t\t$%.2f\n",tax);
                System.out.println("\t\t\t\t\t\t-------");

                total = subTotal + tax;
                System.out.printf("\tTotal:\t\t\t\t\t$%.2f\n",total);
                System.out.printf("\tAmount saved by using your pie card:\t$%.2f\n",savedTotal);

            }
            else
            {
              System.out.println("\t"+plain+" plain pizzas at $10.00ea:\t\t$"+plain*10.0);
              System.out.println("\t"+pep+" pepperoni at $12.00ea:\t\t$"+pep*12.0);
              System.out.println("\t"+cherrySlices+" cherry slices at $2.00ea:\t\t$"+cherrySlices*2.00);
              System.out.println("\t"+cherryPies+" cherry pies at $10.0ea:\t\t$"+cherryPies*10.0);
              System.out.println("\t"+gCharms+" gold charms at $50.00ea:\t\t$"+gCharms*50.0);

              System.out.println("\t\t\t\t\t\t-------");

              subTotal = plain*10.0 + pep*12.0 + cherrySlices*2.00 + cherryPies*10.0 + gCharms*50.0;

              System.out.printf("\tSubtotal:\t\t\t\t$%.2f\n",subTotal);
              tax = subTotal *.07;
              System.out.printf("\tTax:\t\t\t\t\t$%.2f\n",tax);

              System.out.println("\t\t\t\t\t\t-------");

              total = subTotal + tax;
              System.out.printf("\tTotal:\t\t\t\t\t$%.2f\n",total);

            }
            //code to calculate change and payment
            while(payment < total)
            {
              System.out.print("\n\tPlease enter your payment amount:\t$");
              payment = sc.nextDouble();
            }
            change = payment-total;
            System.out.printf("\tChange amount:\t\t\t\t$%.2f\n",change);
          }
          else
          {
          }
        }

        //end of first check
      }
      //start of second check
      else if(hasCustomer == 2)
      {
        isCustomer = false;
      }
      else
      {
      }
    }
  }
  //method to check for negative numbers during ordering pizzas, pie and charms
  public static int checkNegative(int orderN)
  {
    if (orderN < 0)
    {
      System.out.println("Negative order number detected input is read as 0.\n");
      return 0;
    }
    else
    {
      return orderN;
    }
  }
}
