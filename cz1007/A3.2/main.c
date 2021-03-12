#include <stdio.h>
void readInput(int *id, int *noOfHours, int *payRate);
double computeSalary1(int noOfHours, int payRate);
void computeSalary2(int noOfHours, int payRate, double *grossPay);
int main()
{
    int id = -1, noOfHours, payRate;
    double grossPay;

    readInput(&id, &noOfHours, &payRate);
    while (id != -1)
    {
        printf("computeSalary1(): ");
        grossPay = computeSalary1(noOfHours, payRate);
        printf("ID %d grossPay %.2f \n", id, grossPay);
        printf("computeSalary2(): ");
        computeSalary2(noOfHours, payRate, &grossPay);
        printf("ID %d grossPay %.2f \n", id, grossPay);
        readInput(&id, &noOfHours, &payRate);
    }
    return 0;
}
void readInput(int *id, int *noOfHours, int *payRate)
{
    /* Write your code here */
    while(*id != 1)
    {
        printf("Enter ID: \n");
        scanf("%d", id);
        if(*id == -1)
            break;
        printf("Enter number of hours: \n");
        scanf("%d", noOfHours);
        printf("Enter hourly pay rate: \n");
        scanf("%d", payRate);
    }


}
double computeSalary1(int noOfHours, int payRate)
{
    /* Write your code here */
    int total = 0;
    if(noOfHours > 160)
    {
        total = ((noOfHours-160) * (payRate*1.5)) + (160*payRate);
        return total;
    }
    else
        return (noOfHours * payRate);
}
void computeSalary2(int noOfHours, int payRate, double *grossPay)
{
    /* Write your code here */
    int total = 0;
    if(noOfHours > 160)
    {
        total = ((noOfHours-160) * (payRate*1.5)) + (160*payRate);
        *grossPay = total;
    }
    else
        *grossPay = (noOfHours * payRate);
}
