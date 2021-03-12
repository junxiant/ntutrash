//import java.math.BigDecimal;
import java.lang.Math;

public class VendingMachine {

    private double balance;
    private double drinkCost;

    public VendingMachine() {}

    public double drinkCost(int choice)
    {
        switch(choice)
        {
            case 1: this.drinkCost = 3.00;
                break;
            case 2: this.drinkCost = 1.00;
                break;
            case 3: this.drinkCost = 5.00;
                break;
        }
        //System.out.println(this.drinkCost);
        return this.drinkCost;
    }

    public double balance() {
        return this.balance;
    }


    public void insertCoins(double coinVal)
    {

        Math.round(this.balance += coinVal);
        System.out.println("\nCurrent Amount: \n"+this.balance);
    }

    public double checkChange()
    {
        double change = Math.round(this.drinkCost - this.balance);

        if(change > 0)
        {
            return change;
        }
        else if(change < 0)
        {
            return Math.abs(change);
        }
        else
            return 0;
    }

    public void printReceipt()
    {
        System.out.println("Change: "+this.checkChange());
    }

}
