import java.util.Scanner;

public class P1 {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a character: ");

        char c = scan.next().charAt(0);

        char c1 = Character.toLowerCase(c);

        switch(c1)
        {
            case 'a' :
                System.out.println("Action movie fan; \n");
                break;
            case 'c' :
                System.out.println("Comedy movie fan; \n");
                break;
            case 'd' :
                System.out.println("Drama movie fan; \n");
                break;
            default:
                System.out.println("Invalid choice; \n");
        }

    }


}
