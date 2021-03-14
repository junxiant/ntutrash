#include <stdio.h>
int main()
{
    int studentNumber;
    int mark;
    /* insert variable declarations here */

    printf("Enter Student ID: \n");
    scanf("%d", &studentNumber);
    while (studentNumber != -1)
    {
        printf("Enter Mark: \n");
        scanf("%d", &mark);
        switch((mark+5) / 10)
        {
        case 10:
        case 9:
        case 8:
            printf("Grade = A \n");
            break;
        case 8:
            printf("Grade = B \n");
            break;
        case 8:
            printf("Grade = C \n");
            break;
        case 8:
            printf("Grade = D \n");
            break;
        default:
            printf("Grade = F \n");

        }
        printf("Enter Student ID: \n");
        scanf("%d", &studentNumber);
        /* Write your program code here */
    }
    return 0;
}
