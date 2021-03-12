import java.util.Scanner;

public class DiceApp {

    public static Dice d1 = new Dice();
    public static Dice d2 = new Dice();

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Press 1 to roll the first dice");
        int inp1 = sc.nextInt();
        if(inp1 == 1)
        {
            d1.setDiceValue();
            d1.printDiceValue();
        }

        System.out.println("Press 2 to roll the second dice");
        int inp2 = sc.nextInt();
        if(inp2 == 2)
        {
            d2.setDiceValue();
            d2.printDiceValue();
        }

        int total = d1.getDiceValue() + d2.getDiceValue();
        System.out.println("Total Value: " +total);

    }
}
