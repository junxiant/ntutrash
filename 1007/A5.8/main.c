#include <stdio.h>
#include <string.h>
void maxCharToFront(char *str);
int main()
{
    char str[80], *p;

    printf("Enter a string: \n");
    fgets(str, 80, stdin);
    if (p=strchr(str,'\n'))
        *p = '\0';
    printf("maxCharToFront(): ");
    maxCharToFront(str);
    puts(str);
    return 0;
}
void maxCharToFront(char *str)
{
    /* Write your code here */
    int i;
    int len = 0;

    char biggest;

    len = strlen(str);


    for(i=len; i>=0; i--)
    {
        if(str[i] > str[i-1])
        {
            biggest = str[i];
        }
        str[i] = biggest;
    }


}
