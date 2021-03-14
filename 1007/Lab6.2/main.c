#include <stdio.h>
#include <string.h>
#define MAX 100
typedef struct
{
    char name[20];
    char telno[20];
} PhoneBk;
void printPB(PhoneBk *pb, int size);
int readin(PhoneBk *pb);
void search(PhoneBk *pb, int size, char *target);
int main()
{
    PhoneBk s[MAX];
    char t[20], *p;
    int size=0, choice, dummychar;

    printf("Select one of the following options: \n");
    printf("1: readin()\n");
    printf("2: search()\n");
    printf("3: printPB()\n");
    printf("4: exit()\n");
    do
    {
        printf("Enter your choice: \n");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
            scanf("%c", &dummychar);
            size = readin(s);
            break;
        case 2:
            scanf("%c", &dummychar);
            printf("Enter search name: \n");
            fgets(t, 20, stdin);
            if (p=strchr(t,'\n'))
                *p = '\0';
            search(s,size,t);
            break;
        case 3:
            printPB(s, size);
            break;
        }
    }
    while (choice < 4);
    return 0;
}
void printPB(PhoneBk *pb, int size)
{
    int i;

    printf("The phonebook list: \n");
    if(size == 0)
    {
        printf("Empty phonebook \n");
    }
    else
    {
        for(i = 0; i < size; i++)
        {
            printf("Name: %s\n", (pb+i)->name);
            printf("Telno: %s\n", (pb+i)->telno);
        }
    }

}
int readin(PhoneBk *pb)
{
    char *p;
    int ppl = 0;

    while(1)
    {

        printf("Enter name: \n");
        fgets(pb->name, 80, stdin);
        if(p=strchr(pb->name, '\n'))
            *p = '\0';

        if(strcmp(pb->name, "#") == 0)
            break;

        printf("Enter tel: \n");
        fgets(pb->telno, 80, stdin);
        if(p=strchr(pb->telno, '\n'))
            *p = '\0';
        pb++;
        ppl++;

    }

    return ppl;

}
void search(PhoneBk *pb, int size, char *target)
{
    int i;
    for (i=0; i<size; i++)
    {
        if (strcmp(pb->name,target)==0)
        {
            printf("Name = %s, Tel = %s\n",target,pb->telno);
            break;
        }
    } pb++;
    if (i==size)
        printf("Name not found!\n");
}
