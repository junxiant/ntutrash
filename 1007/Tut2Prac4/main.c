#include <stdio.h>
int main()
{
    int list, coe = 0, cat;
    double discounted, luxury = 0, gst, total;

    printf("Please enter the list price: \n");
    scanf("%d", &list);
    printf("Please enter the category: \n");
    scanf("%d", &cat);
    /* Write your program code here */
    discounted = 0.9 * list;
    if(discounted > 100000)
        luxury = 1.1 * (discounted - 100000);
    gst = 0.03 * discounted;

    if(cat == 1)
    {
        coe = 70000;
        total = coe + discounted + luxury + gst;
    }

    else if(cat == 2)
    {
        coe = 80000;
        total = coe + discounted + luxury + gst;
    }

    else if(cat == 3)
    {
        coe = 23000;
        total = coe + discounted + luxury + gst;
    }

    else if(cat == 4)
    {
        coe = 600;
        total = coe + discounted + luxury + gst;
    }


    printf("Total price is $%.2lf\n", total);
    return 0;
}
