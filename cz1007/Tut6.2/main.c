#include <stdio.h>
#include <string.h>
struct student
{
    char name[20]; /* student name */
    double testScore; /* test score */
    double examScore; /* exam score */
    double total; /* total = (testScore+examScore)/2 */
};
double average();
int main()
{
    printf("average(): %.2f\n", average());
    return 0;
}
double average()
{
    struct student stud[50];
    int i = 0;
    int sum = 0;

    char *p;

    printf("Enter student name: \n");
    fgets(stud[i].name, 80, stdin);
    if(p=strchr(stud[i].name,'\n')) *p = '\0';

    while(strcmp(stud[i].name, "END") != 0)
    {
        printf("Enter test score: \n");
        scanf("%lf", &stud[i].testScore);
        printf("Enter exam score: \n");
        scanf("%lf", &stud[i].examScore);

        stud[i].total = (stud[i].testScore + stud[i].examScore) / 2;

        printf("Student %s total = %.2f\n", stud[i].name, stud[i].total);

        sum += stud[i].total;
        i++;

        printf("Enter student name: \n");
        scanf("\n");
        fgets(stud[i].name, 80, stdin);
        if(p=strchr(stud[i].name,'\n')) *p = '\0';

    }

    if(i != 0)
    {
        return sum/i;
    }
    else
        return 0;


}
