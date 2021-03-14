#include <stdio.h>
int main()
{
    int salary, merit;

    printf("Enter the salary: \n");
    scanf("%d", &salary);
    printf("Enter the merit: \n");
    scanf("%d", &merit);
    /* Write your program code here */
    if(salary >= 800 || salary >= 700 && merit >= 20)
        printf("The grade: A\n");
    else if(salary >= 650 || salary >= 600 && merit >= 10)
        printf("The grade: B\n");
    else if(salary >= 500)
        printf("The grade: C\n");
    return 0;
}
