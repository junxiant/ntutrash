import java.util.Scanner;
import java.lang.*;

public class VendingMachineApp {
    public static void main(String[] args) {

        VendingMachine v = new VendingMachine();

        Scanner sc = new Scanner(System.in);

        System.out.println("====== Vending Machine ======" +
                "\n|1. Buy Beer ($3.00) \t\t |" +
                "\n|2. Buy Coke ($1.00) \t\t |" +
                "\n|3. Buy Green Tea ($5.00) \t |" +
                "\n=========================" +
                "\n");
        System.out.println("Please enter selection: \n");

        int inp = sc.nextInt();

        System.out.println("Please insert coins: \n");
        System.out.println("====== Coins Input ======" +
                "\n |Enter 'Q' for ten cents input \t |" +
                "\n |Enter 'T' for twenty cents input \t |" +
                "\n |Enter 'F' for fifty cents input \t |" +
                "\n |Enter 'N' for a dollar input \t\t |" +
                "\n=====================");

        double drinkPrice = v.drinkCost(inp);

        while(v.balance() < drinkPrice) {
            char coinInp = sc.next().charAt(0);
            char ucCoinInp = Character.toUpperCase(coinInp);

            switch (ucCoinInp) {
                case 'Q':
                    v.insertCoins(0.1);
                    break;
                case 'T':
                    v.insertCoins(0.2);
                    break;
                case 'F':
                    v.insertCoins(0.5);
                    break;
                case 'N':
                    v.insertCoins(1.0);
                    break;
            }
        }
        v.printReceipt();
    }
}
