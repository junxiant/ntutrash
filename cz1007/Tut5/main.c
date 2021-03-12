#include <stdio.h>
#include <stdlib.h>

int main()
{
    char array[] = "pointer";
    char *ptr1 = "10 spaces";

    printf("ptr1 = %s\n", ptr1);
    printf("array = %s\n", array);

    array[5] = 'A';

    return 0;
}
