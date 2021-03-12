import java.util.Scanner;

public class CircleApp {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Circle Computation ====" +
                "\n|1. Create a new circle |" +
                "\n|2. Print Area          |" +
                "\n|3. Print circumference |" +
                "\n|4. Quit                |");

        System.out.println("Choose option (1-3): ");

        int inp = sc.nextInt();


        System.out.println("Enter the radius to compute the area and circumference");
        double rinp = sc.nextInt();

        Circle c = new Circle(rinp);

        System.out.println("Area of circle "+
                            "\nRadius: "+c.getRadius() +
                            "\nArea: "+c.area());


    }

}
