#include <stdio.h>
int reverseDigits1(int num);
void reverseDigits2(int num, int *result);
int main()
{
    int num, result=999;
    printf("Enter a number: \n");
    scanf("%d", &num);
    printf("reverseDigits1(): %d\n", reverseDigits1(num));
    reverseDigits2(num, &result);
    printf("reverseDigits2(): %d\n", result);
    return 0;
}
int reverseDigits1(int num)
{
    /* Write your code here */
    int i;
    int power = 1;
    int count = 1;

    int temp = num;

    int nnum = 0;

    int dig = 0;

    while(temp > 10)
    {
        count++;
        power = power*10;
        temp = temp/10;
    }

    for(i=0;i<count;i++)
    {
        dig = num%10;
        nnum = (nnum*10) + dig;
        num /= 10;
    }
    return nnum;
}
void reverseDigits2(int num, int *result)
{
    /* Write your code here */
}
