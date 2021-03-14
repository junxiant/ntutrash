#include <stdio.h>
#include <string.h>
int countWords(char *s);
int main()
{
    char str[50], *p;
    printf("Enter the string: \n");
    fgets(str, 80, stdin);
    if (p=strchr(str,'\n'))
        *p = '\0';
    printf("countWords(): %d", countWords(str));
    return 0;
}
int countWords(char *s)
{
    /* Write your code here */
    int i, j, k;
    int len = 0;
    j=1;
    k=0;

    len = strlen(s);

    for(i=0;i<len;i++)
    {
        if(s[i+1] == ' ')
        {
            j+=1;
        }
    }
    return j;
}

