import java.util.Scanner;

public class P2 {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter salary: ");
        int sal = scan.nextInt();

        System.out.println("Enter merit: ");
        int merit = scan.nextInt();

        if (sal >= 700 && sal <= 899)
        {
            if (merit < 20)
            {
                System.out.printf("salary : $%d, merit : %d - Grade B;\n", sal, merit);
            }
            else
            {
                System.out.printf("salary : $%d, merit : %d - Grade A;\n", sal, merit);
            }
        }
        else if (sal >= 600 && sal <= 799)
        {
            if (merit < 10)
            {
                System.out.printf("salary : $%d, merit : %d - Grade C;\n", sal, merit);
            }
            else
            {
                System.out.printf("salary : $%d, merit : %d - Grade B;\n", sal, merit);
            }
        }
        else
        {
            System.out.printf("salary : $%d, merit : %d - Grade C;\n", sal, merit);

        }

    }

}
