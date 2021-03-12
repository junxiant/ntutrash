#include <stdio.h>

double computePay1(int noOfHours, int payRate);
void computePay2(int noOfHours, int payRate, double *grossPay);

int main()
{
    int noOfHours = 0;
    int payRate = 0;
    double grossPay = 0;

    printf("Enter number of hours: \n");
    scanf("%d", &noOfHours);

    printf("Enter hourly pay rate: \n");
    scanf("%d", &payRate);

    printf("computePay1(): %.2f\n", computePay1(noOfHours, payRate));

    computePay2(noOfHours, payRate, &grossPay);
    printf("computePay2(): %.2f\n", grossPay);

    return 0;

}
double computePay1(int noOfHours, int payRate)
{    /* Write your code here */
    int sum = 0;
    int ot = 0;

    if(noOfHours <= 160)
    {
        sum = noOfHours * payRate;
        return sum;
    }
    else if(noOfHours > 160)
    {
        ot = (1.5 * payRate) * (noOfHours - 160);
        sum = (160 * payRate) + ot;
        return sum;
    }
}

void computePay2(int noOfHours, int payRate, double *grossPay)
{    /* Write your code here */
    int ot = 0;

    if(noOfHours <= 160)
    {
        *grossPay = noOfHours * payRate;

    }
    else if(noOfHours > 160)
    {
        ot = (1.5 * payRate) * (noOfHours - 160);
        *grossPay = (160 * payRate) + ot;

    }
}
