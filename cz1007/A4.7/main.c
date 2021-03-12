#include <stdio.h>
void findAverage2D(float matrix[4][4]);
int main()
{

    float ar[4][4];
    int i,j;

    printf("Enter data: \n");
    for (i = 0; i < 4; i++)
    {
        for (j = 0; j < 4; j++)
            scanf("%f", &ar[i][j]);
    }
    findAverage2D(ar);
    printf("findAverage2D(): :\n");
    for (i = 0; i < 4; i++)
    {
        for (j = 0; j < 4; j++)
            printf("%.2f ", ar[i][j]);
        printf("\n");
    }
    return 0;
}
void findAverage2D(float matrix[4][4])
{
    /* Write your code here */
    int r, c;

    for(r=0;r<4;r++)
    {
        float sum = 0;
        for(c=0;c<3;c++)
        {
            sum += matrix[r][c];
        }
        matrix[r][3] = sum /3;
    }
}
