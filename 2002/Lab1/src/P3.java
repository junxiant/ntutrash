import java.util.*;

public class P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double sgd = 0;
        double exchange = 1.82;
        int starting, ending, increment;

        System.out.print("Starting : ");
        starting = sc.nextInt();
        System.out.print("Ending : ");
        ending = sc.nextInt();
        System.out.print("Increment : ");
        increment = sc.nextInt();
        if (starting > ending) {
            System.out.println("Invalid start and end values");
        } else {
            System.out.println("US$ \t S$");
            System.out.println("------------");
            for (int i = starting; i <= ending; i += increment) {
                sgd = i * exchange;
                System.out.println(i + "\t" + sgd);
            }
            System.out.println("US$ \t S$");
            System.out.println("------------");
            int i = starting;
            while (i <= ending) {
                sgd = i * exchange;
                System.out.println(i + "\t" + sgd);
                i += increment;
            }
            System.out.println("US$ \t S$");
            System.out.println("------------");
            i = starting;
            do {
                sgd = i * exchange;
                System.out.println(i + "\t" + sgd);
                i += increment;
            } while (i <= ending);

        }
    }
}