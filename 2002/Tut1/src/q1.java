import java.util.Scanner;

public class q1 {
    public static void bubble(int[] arr) {
        int n = arr.length;
        int temp = 0;

        for(int i=0; i<n; i++){
            for(int j=1; j<(n-i); j++){
                if(arr[j-1] > arr[j]){

                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;

                }

            }
        }

    }

    public static void main(String[] args) {

        int n = 0;

        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of Integer elements to be sorted: ");
        n = s.nextInt();

        int arr[] = new int[n]; // Array will have n size

        Scanner e = new Scanner(System.in);
        for(int i=0; i<n; i++)
        {
            System.out.print("Enter integer value for element no. : " +i);
            arr[i] = e.nextInt();

        }

        System.out.println("Array Before Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        bubble(arr); // Call the function

        System.out.println("Array After Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

    }
}  