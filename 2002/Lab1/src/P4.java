import java.util.Scanner;

public class P4 {

    public static void main(String[] args)
    {


        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("Enter height: ");
            String input = scan.next();

            String a = "AA";
            String b = "BB";

            int letter = 65;

            try {
                int height = Integer.parseInt(input);
                if(height > 0)
                {
                    for(int i=1; i<=height; i++)
                    {
                        for(int j=1; j<=i;j++)
                        {
                            if(j%2 == 1)
                            {
                                if(i%2 == 1)
                                {
                                    System.out.print(a);
                                }
                                else
                                {
                                    System.out.print(b);
                                }
                            }
                            else
                            {
                                if(i%2 == 1)
                                {
                                    System.out.print(b);
                                }
                                else
                                {
                                    System.out.print(a);
                                }
                            }
                        }
                        System.out.println();
                    }
                }
                else
                {
                    System.out.println("Error input!!");
                }
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Only integers allowed. ");
            }
        }


    }

}
