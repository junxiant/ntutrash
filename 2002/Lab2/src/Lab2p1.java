import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import java.io.FileWriter;
import java.io.IOException;

public class Lab2p1 {
    public static void main(String[] args)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Perform the following methods:");
            System.out.println("1: multiplication test");
            System.out.println("2: quotient using division by subtraction");
            System.out.println("3: remainder using division by subtraction");
            System.out.println("4: count the number of digits");
            System.out.println("5: position of a digit");
            System.out.println("6: extract all odd digits");
            System.out.println("7: quit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: /* add mulTest() call */
                    mulTest();
                    break;
                case 2: /* add divide() call */
                    System.out.println("Enter m");
                    int m = sc.nextInt();
                    System.out.println("Enter n");
                    int n = sc.nextInt();
                    System.out.println(m+"/"+n+"="+divide(m, n));
                    break;
                case 3: /* add modulus() call */
                    System.out.println("Enter m");
                    int a = sc.nextInt();
                    System.out.println("Enter n");
                    int b = sc.nextInt();
                    System.out.println(a+"%"+b+"="+modulus(a, b));
                    break;
                case 4: /* add countDigits() call */
                    System.out.println("Enter number n");
                    int c = sc.nextInt();
                    System.out.println("n: "+c+" - count = "+countDigits(c));
                    break;
                case 5: /* add position() call */
                    System.out.println("Enter n");
                    int d = sc.nextInt();
                    System.out.println("Enter digit");
                    int digit = sc.nextInt();
                    System.out.println("Position: "+position(d, digit));
                    break;
                case 6: /* add extractOddDigits() call */
                    System.out.println("Enter n");
                    int e = sc.nextInt();
                    if(e<0)
                    {
                        System.out.println("oddDigits = Error input!!");
                    }
                    else
                    {
                        System.out.println("oddDigits = "+extractOddDigits(e));
                    }
                    break;
                case 7: System.out.println("Program terminating...");
            }
        } while (choice < 7);

    }
    /* add method code here */
    //1
    public static void mulTest()
    {
        Scanner sc = new Scanner(System.in);

        int i = 0;
        int correct = 0;
        int total = 0;
        int ans = 0;

        while(i<5)
        {
            int rand1 = ThreadLocalRandom.current().nextInt(1, 9 + 1);
            int rand2 = ThreadLocalRandom.current().nextInt(1, 9 + 1);
            System.out.println("How much is "+rand1+" times "+rand2+"? ");

             ans = sc.nextInt();
             total = rand1*rand2;

            if(ans==total)
            {
                correct++;
            }
            i++;
        }
        System.out.println(correct + " answers out of 5 are correct.\n");

//        try {
//            FileWriter myWriter = new FileWriter("mulTest.txt");
//            myWriter.write(correct + "answers out of 5 are correct.\n");
//            myWriter.close();
//            System.out.println("Done");
//        } catch (IOException e) {
//            System.out.println("Error");
//            e.printStackTrace();
//        }
    }

    //2
    public static int divide(int m, int n) {

        int q = 0;

        while(m>=n)
        {
            m = m - n;
            q++;
        }
        return q;
    }

    //3
    public static int modulus(int m, int n)
    {
        int q = 0;

        if(m<n)
        {
            return m;
        }
        else
        {
            while(m>=n)
            {
                m = m - n;
            }
        }
        return m;
    }

    //4
    public static int countDigits(int n)
    {
        int count = 0;

        if(n<0)
        {
            System.out.println("Negative number error!");
        }
        else
        {
            while(n!=0)
            {
                n = n/10;
                count++;
            }
        }
        return count;
    }

    //5
    public static int position(int n, int digit)
    {

        int pos = 0;

        while(n!=0)
        {
            if(n%10 == digit)
            {
                pos++;
                break;
            }
            else
            {
                n = n/10;
                pos++;
            }

        }
        if(n==0)
        {
            pos = -1;
            System.out.println("Error input!!");
        }
        return pos;
    }

    //6
    public static long extractOddDigits(long n) {

        long newNum = 0;
        int odd = 0;
        long temp = n;
        int count = 0;
        int p = 0;

        while(temp!=0)
        {
            temp = temp/10;
            count++;
        }
        count = count-1;
        p = (int) Math.pow(10, count);

        while (n != 0)
        {

            if (((n / p) % 2) == 1)
            {
                newNum = newNum * 10;
                newNum = newNum + (n / p);
                n = n % p;
                p = p / 10;
                odd++;
            }
            else {
                n = n % p;
                p = p / 10;
            }

        }
        if (odd == 0)
        {
            newNum = -1;
        }
        return newNum;
    }
}