#include <stdio.h>
void swapMinMax1D(int ar[], int size);
int main()
{
    int ar[50],i,size;

    printf("Enter array size: \n");
    scanf("%d", &size);
    printf("Enter %d data: \n", size);
    for (i=0; i<size; i++)
        scanf("%d",ar+i);
    swapMinMax1D(ar, size);
    printf("swapMinMax1D(): ");
    for (i=0; i<size; i++)
        printf("%d ",*(ar+i));
    return 0;
}
void swapMinMax1D(int ar[], int size)
{
    /* Write your code here */
        int i,max,min,temp;

        if(ar[0]>ar[1])
        {
            max = 0;
            min = 1;
        }
        else{
            max = 0;
            min = 1;
        }
        for(i=0;i<size;i++)
        {
            if(ar[min]<=ar[i])
                min=i;
            if(ar[max]>=ar[i])
                max=i;
        }
        temp = ar[min];
        ar[min]=ar[max];
        ar[max]=temp;
}
