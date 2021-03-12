#include <stdio.h>
float rPower1(float num, int p);
void rPower2(float num, int p, float *result);
int main()
{
 int power;
 float number, result;

 printf("Enter the number and power: \n");
 scanf("%f %d", &number, &power);
 printf("rPower1(): %.2f\n", rPower1(number, power));
 rPower2(number, power, &result);
 printf("rPower2(): %.2f\n", result);
 return 0;
}
float rPower1(float num, int p)
{
 /* Write your code here */
    if(p == 0 || num == 1)
    {
        return 1;
    }
    else if(num == 0)
    {
        return 0;
    }
    else if(p == 1)
    {
        return num;
    }
    else if(p > 1)
    {
        return (num*rPower1(num, p-1));
    }
    else if(p < 0)
    {
       return (1/rPower1(num, -p));
    }

}
void rPower2(float num, int p, float *result)
{
 /* Write your code here */
     if(p == 0 || num == 1)
    {
        *result = 1;
    }
    else if(num == 0)
    {
        *result = 0;
    }
    else if(p == 1)
    {
        *result = num;
    }
    else if(p > 1)
    {
        rPower2(num, p-1, result);
        *result = (*result) * num;
    }
    else if(p < 0)
    {
       rPower2(num, -p, result);
       *result = 1 / (*result);
    }
}
