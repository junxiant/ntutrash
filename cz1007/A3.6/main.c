#include <stdio.h>

int allEvenDigits1(int num);
void allEvenDigits2(int num, int *result);

int main()
{
    int number = 0;
    int p=999;
    int result=999;

    printf("Enter a number: \n");
    scanf("%d", &number);

    p = allEvenDigits1(number);
    if (p == 1)
        printf("allEvenDigits1(): yes\n");
    else if (p == 0)
        printf("allEvenDigits1(): no\n");
    else
        printf("allEvenDigits1(): error\n");

    allEvenDigits2(number, &result);
    if (result == 1)
        printf("allEvenDigits2(): yes\n");
    else if (result == 0)
        printf("allEvenDigits2(): no\n");
    else
        printf("allEvenDigits2(): error\n");

    return 0;
}

int allEvenDigits1(int num)
{
    int odd = 0;
    int even = 0;
    int dig = 0;

    while(num > 0)
    {
        dig = num % 10;
        if(dig % 2 == 0)
        {
            even += 1;
        }
        else
        {
            odd += 1;
        }
        num = num / 10;
    }
    if(even != 0 && odd == 0)
        {
            return 1;
        }
    else
        {
            return 0;
        }
}

void allEvenDigits2(int num, int *result)
{
    int odd = 0;
    int even = 0;
    int dig = 0;

    while(num > 0)
    {
        dig = num % 10;
        if(dig % 2 == 0)
        {
            even += 1;
        }
        else
        {
            odd += 1;
        }
        num = num / 10;
    }
    if(even != 0 && odd == 0)
        {
            *result = 1;
        }
    else
        {
            *result = 0;
        }
}
