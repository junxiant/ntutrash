#include <stdio.h>
#include <string.h>
void insertChar(char *str1, char *str2, char ch);
int main()
{
    char a[80],b[80];
    char ch, *p;

    printf("Enter a string: \n");
    fgets(a, 80, stdin);
    if (p=strchr(a,'\n'))
        *p = '\0';
    printf("Enter a character to be inserted: \n");
    ch = getchar();
    insertChar(a,b,ch);
    printf("insertChar(): ");
    puts(b);
    return 0;
}
void insertChar(char *str1, char *str2, char ch)
{
    /* Write your code here */
    int i = 0;
    int j = 0;
    int len = 0;
    int count = 0;

    len = strlen(str1);

    while(j<len)
    {
        str2[i] = str1[j];
        i++;
        j++;

        if(j%3 == 0 && j!=0)
        {
            str2[i] = ch;
            i++;
        }
    }

    str2[i] = '\0';

}
