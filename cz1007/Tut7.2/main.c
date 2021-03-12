#include <stdio.h>
int rDigitValue1(int num, int k);
void rDigitValue2(int num, int k, int *result);
int main()
{
    int k;
    int number, digit;

    printf("Enter a number: \n");
    scanf("%d", &number);
    printf("Enter k position: \n");
    scanf("%d", &k);
    printf("rDigitValue1(): %d\n", rDigitValue1(number, k));
    rDigitValue2(number, k, &digit);
    printf("rDigitValue2(): %d\n", digit);
    return 0;
}
int rDigitValue1(int num, int k) //from left
{
    int n = 0;
    int pow = 1;
    int dig = 0;
    int temp = 0;

    temp = num;

    while(temp>10)
    {
        temp /= 10;
        dig++;
    }

    for(n = 0; n < dig; n++)
    {
        pow = pow * 10;
    }

    if(k == 0)
    {
        return 0;
    }
    else if(k == 1)
    {
        return num / pow;
    }
    else if(k > 1)
    {

        return rDigitValue1(num%pow, k-1);
        pow = pow / 10;

    }
}
void rDigitValue2(int num, int k, int *result)
{
        if(k == 0)
    {
        *result = 0;
    }
    else if(k == 1)
    {
        *result = num % 10;
    }
    else if(k > 1)
    {
        rDigitValue2(num/10, k-1, result);
    }
}
