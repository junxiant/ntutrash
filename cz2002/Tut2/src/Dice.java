import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int valueOfDice;

    public Dice()
    {

    }

    public void setDiceValue()
    {
        int r = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        this.valueOfDice = r;
    }

    public int getDiceValue()
    {
        return this.valueOfDice;
    }

    public void printDiceValue()
    {
        System.out.println("Rolled: "+this.valueOfDice);
    }

}
