#include <stdio.h>
int main()
{
    int n, denominator = 1;
    float x, result = 1.0, numerator = 1.0;
    printf("Enter x: \n");
    scanf("%f", &x);
    /* Write your program code here */
    if(x == 0)
    {
        result = 1.0;
    }

    for(n = 1; n <= 10; n++)
    {
        denominator = denominator * n;
        numerator = numerator * x;
        result = result + numerator/denominator;
    }
    printf("Result = %.2f\n", result);
    return 0;
}
