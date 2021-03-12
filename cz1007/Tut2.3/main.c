#include <stdio.h>
int main()
{
    int row, col, height;
    int num = 0;
    printf("Enter the height: \n");
    scanf("%d", &height);
    printf("Pattern: \n");
    /* Write your program code here */
    for(row=0;row<height;row++)
    {
        for(col=0;col<row+1;col++)
        {
            printf("%d", num+1);
        }
        num = (num+1) %3;
        printf("\n");
    }
    return 0;
}
