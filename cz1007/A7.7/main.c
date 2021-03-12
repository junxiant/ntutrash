#include <stdio.h>
#include <string.h>
int rStrLen(char *s);
int main()
{
    char str[80], *p;

    printf("Enter the string: \n");
    fgets(str, 80, stdin);
    if (p=strchr(str,'\n'))
        *p = '\0';
    printf("rStrLen(): %d\n", rStrLen(str));
    return 0;
}
int rStrLen(char *s)
{
    /* Write your code here */
    if(*s == '\0')
        return 0;
    else
    {
        return 1 + rStrLen(s+1);
    }
}
