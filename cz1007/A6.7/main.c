#include <stdio.h>
#include <string.h>
#define MAX 100
typedef struct
{
    char name[40];
    char telno[40];
    int id;
    double salary;
} Employee;
void printEmp(Employee *emp, int size);
int readin(Employee *emp);
int search(Employee *emp, int size, char *target);
int addEmployee(Employee *emp, int size, char *target);
int main()
{
    Employee emp[MAX];
    char name[40], *p;
    int size, choice, result;

    printf("Select one of the following options: \n");
    printf("1: readin() \n");
    printf("2: search() \n");
    printf("3: addEmployee() \n");
    printf("4: print() \n");
    printf("5: exit() \n");
    do
    {
        printf("Enter your choice: \n");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
            size = readin(emp);
            break;
        case 2:
            printf("Enter search name: \n");
            scanf("\n");
            fgets(name, 40, stdin);
            if (p=strchr(name,'\n'))
                *p = '\0';
            result = search(emp,size,name);
            if (result != 1)
                printf ("Name not found! \n");
            break;
        case 3:
            printf("Enter new name: \n");
            scanf("\n");
            fgets(name, 40, stdin);
            if (p=strchr(name,'\n'))
                *p = '\0';
            result = search(emp,size,name);
            if (result != 1)
                size = addEmployee(emp, size, name);
            else
                printf("The new name has already existed in the database\n");
            break;
        case 4:
            printEmp(emp, size);
            break;
        default:
            break;
        }

    }
    while (choice < 5);
    return 0;
}
void printEmp(Employee *emp, int size)
{
    int i;

    printf("The current employee list: \n");
    if (size==0)
        printf("Empty array\n");
    else
    {
        for (i=0; i<size; i++)
            printf("%s %s %d %.2f\n",emp[i].name,emp[i].telno,emp[i].id,
                   emp[i].salary);
    }
}
int readin(Employee *emp)
{
    /* Write your code here */
    int a = 0;

    printf("Enter name: \n");
    scanf("\n");
    scanf("%s", emp->name);

    while(strcmp(emp->name, "#"))
    {
        printf("Enter tel: \n");
        scanf("%s", &(emp->telno));

        printf("Enter id: \n");
        scanf("%d", &(emp->id));

        printf("Enter salary: \n");
        scanf("%lf", &(emp->salary));
        emp++;

        printf("Enter name: \n");
        scanf("\n");
        scanf("%s", emp->name);
        a++;
    }

    return a;


}
int search(Employee *emp, int size, char *target)
{
    /* Write your code here */
    int i;

    for(i=0;i<size;i++)
    {
        if(strcmp(emp[i].name, target) == 0)
        {
            printf("Employee found at index location: %d\n", i);
            printf("%s %s %d %.2f\n", emp[i].name, emp[i].telno, emp[i].id, emp[i].salary);
            return 1;
        }
    }

    return 0;
}
int addEmployee(Employee *emp, int size, char *target)
{
    /* Write your code here */
        strcpy(emp[size].name, target);

        printf("Enter tel: \n");
        scanf("%s", &(emp[size].telno));

        printf("Enter id: \n");
        scanf("%d", &(emp[size].id));

        printf("Enter salary: \n");
        scanf("%lf", &(emp[size].salary));

        printf("Added at position: %d\n", size);
        return size+1;

}
