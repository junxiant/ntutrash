#include <stdio.h>
int perfectProd1(int num);
void perfectProd2(int num, int *prod);
int main()
{
    int number, result=0;

    printf("Enter a number: \n");
    scanf("%d", &number);
    printf("Calling perfectProd1() \n");
    printf("perfectProd1(): %d\n", perfectProd1(number));

    printf("Calling perfectProd2() \n");
    perfectProd2(number, &result);
    printf("perfectProd2(): %d\n", result);
    return 0;
}
int perfectProd1(int num)
{
int result = 1;
 int sum = 0;
 int y;
 int x;
 for(x=1; x<=num; x++)
    {
        sum = 0;
        for(y=1; y<x; y++)
        {
            if(x % y == 0)
            {
                sum += y;
            }
        }
        if(sum == x)
        {
            printf("Perfect number: %d\n", x);
            result = result * x;
        }
    }
  return result;
}
void perfectProd2(int num, int *prod)
{
    /* Write your code here */
}
