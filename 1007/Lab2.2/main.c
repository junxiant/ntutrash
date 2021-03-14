#include <stdio.h>
int main()
{
    int total, count, lines, input;
    double average;
    int i;
    printf("Enter number of lines: \n");
    scanf("%d", &lines);
    /* Write your program code here */
    for(i=1;i<=lines;i++)
    {
        count = 0;
        total = 0;

        printf("Enter line %d\n", i);
        scanf("%d", &input);
        while(input != -1)
        {

            total += input;
            count++;
            scanf("%d", &input);
        }
        average = (double)total / (double)count;
        printf("Average = %.2f\n", average);
    }
    return 0;
}
