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
    int i;
    int platform = 1;
    int max = 1;

    for(i = 0; i < size; i++)
    {
        if(ar[i] == ar[size])
        {
            break;
        }
        if(ar[i] == ar[i+1])
        {
            platform++;
            if(platform > max)
            {
                max = platform;
            }
        }
        else
        {
            platform = 1; // for when not consecutive, reset
        }
    }

    return max;
}
