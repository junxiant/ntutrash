#include <stdio.h>
#include <stdlib.h>
int digitValue1(int num, int k);
int leftside(int num, int k);
void digitValue2(int num, int k, int *result);

int main()
{
    int num, digit, result;

    printf("Enter the number: \n");
    scanf("%d", &num);

    printf("Enter k position: \n");
    scanf("%d", &digit);

    printf("digitValue1(): %d\n", digitValue1(num, digit));

    digitValue2(num, digit, &result);
    printf("digitValue2(): %d\n", result);

    printf("From left side: %d\n", leftside(num, digit));

    return 0;

}

int digitValue1(int num, int k)
{
    int i, r;

    for(i = 0; i < k; i++)
    {
        r = num%10;
        num = num/10;
    }

    return r;
}

int leftside(int num, int k)
{
    int i, r;
    int count = 1;
    int power = 1;
    int temp = num;

    while(temp>10)
    {
        count++;
        power = power*10;
        temp = temp/10;
    }

    if(k > count)
    {
        return 0;
    }

    for(i = 0; i < k; i++)
    {
        r = num/power;
        num = num%power;
        power = power/10;

    }

    return r;
}




void digitValue2(int num, int k, int *result)
{
    int i, r;

    for(i = 0; i < k; i++)
    {
        r = num%10;
        num = num/10;
    }

    *result = r;
}
