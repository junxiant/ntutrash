#include <stdio.h>
#include <stdlib.h>

void fucntion0();
void function1(int h, int k);
void function2(int *h, int *k);

int main()
{
    int h, k;
    h = 5;
    k = 15;

    printf("1h = %d, k = %d, \n", h, k);
    function0();

    printf("2h = %d, k = %d, \n", h, k);
    function1(h, k);

    printf("3h = %d, k = %d, \n", h, k);
    function2(&h, &k);

    printf("4h = %d, k = %d, \n", h, k);
    return 0;


}

void function0()
{
    int h, k;
    h = k = -100;
    printf("5h = %d, k = %d, \n", h, k);


}

void function1(int h, int k)
{
    printf("6h= %d, k = %d, \n", h, k);
    h = k = 100;
    printf("7h = %d, k = %d, \n", h, k);

}


void function2(int *h, int *k)
{
    printf("7h = %d, k = %d, \n", *h, *k);
    *h = *k = 300;
    printf("8h = %d, k = %d, \n", *h, *k);
    printf("9h = %d, k = %d, \n", h, k);

}
