#include <stdio.h>
int platform1D(int ar[], int size);
int main()
{
    int i,b[50],size;
    printf("Enter array size: \n");
    scanf("%d", &size);
    printf("Enter %d data: \n", size);
    for (i=0; i<size; i++)
        scanf("%d",&b[i]);
    printf("platform1D(): %d\n", platform1D(b,size));
    return 0;
}
int platform1D(int ar[], int size)
{
    /* Write your code here */
    int i;
    int count = 1;
    int max = 1;

    for(i=0;i<size;i++)
    {
        if(ar[i] == ar[i+1])
        {
            count++;
            if(count > max)
            {
                max = count;
            }
        }
        else
        {
            count = 1;
        }
    }
    return max;
}
