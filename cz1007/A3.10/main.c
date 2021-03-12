#include <stdio.h>
#define INIT_VALUE 999
int extEvenDigits1(int num);
void extEvenDigits2(int num, int *result);
int main()
{
    int number, result = INIT_VALUE;

    printf("Enter a number: \n");
    scanf("%d", &number);
    printf("extEvenDigits1(): %d\n", extEvenDigits1(number));
    extEvenDigits2(number, &result);
    printf("extEvenDigits2(): %d\n", result);
    return 0;
}
int extEvenDigits1(int num)
{
    /* Write your code here */
    int count = 1;
    int temp = num;
    int power = 1;

    int dig = 0;
    int nnum = 0;
    int even = 0;
    int i;

    while(temp > 10)
    {
        count++;
        power = power*10;
        temp = temp/10;
    }

    for(i=0;i<count;i++)
    {
        dig = num / power;
        if(dig%2 == 0)
        {
            nnum = (nnum*10) + dig;
            even++;
        }
        num = num % power;
        power = power/10;
    }

    if(even > 0)
        return nnum;
    else
        return -1;

}
void extEvenDigits2(int num, int *result)
{
    /* Write your code here */
}
