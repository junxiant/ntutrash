#include <stdio.h>
int sumSqDigits1(int num);
void sumSqDigits2(int num, int *result);
int main()
{
    int num, result;
    printf("Enter a number: \n");
    scanf("%d", &num);
    printf("sumSqDigits1(): %d\n", sumSqDigits1(num));
    sumSqDigits2(num, &result);
    printf("sumSqDigits2(): %d\n", result);
    return 0;
}
int sumSqDigits1(int num)
{
    /* Write your code here */
    int temp = 0;
    int total = 0;
    while(num > 0)
    {
        temp = num % 10;
        num = num / 10;
        total = total + (temp * temp);
    }
    return total;

}
void sumSqDigits2(int num, int *result)
{
    /* Write your code here */
    int temp = 0;
    int total = 0;
    while(num > 0)
    {
        temp = num % 10;
        num = num / 10;
        total = total + (temp * temp);
    }
    *result = total;
}
