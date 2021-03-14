#include <stdio.h>
void specialNumbers1D(int ar[], int num, int *size);
int main()
{
    int a[20],i,size=0,num;

    printf("Enter a number (between 100 and 999): \n");
    scanf("%d", &num);
    specialNumbers1D(a, num, &size);
    printf("specialNumbers1D(): ");
    for (i=0; i<size; i++)
        printf("%d ",a[i]);
    return 0;
}
void specialNumbers1D(int ar[], int num, int *size)
{
    int i,d1,d2,d3,sum;
    int count = 0;

    for (i=100; i<num; i++)
    {
        d1 = i % 10;
        d2 = i /10 % 10;
        d3 = i / 100 % 10;
        sum = (d1*d1*d1) + (d2*d2*d2) + (d3*d3*d3);
        if (sum == i)
        {
            ar[count] = i;
            count++;
        }
    }
    *size = count;
}
