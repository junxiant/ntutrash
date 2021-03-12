#include <stdio.h>
#include <string.h>
int longWordLength(char *s);
int main()
{
    char str[80], *p;

    printf("Enter a string: \n");
    fgets(str, 80, stdin);
    if (p=strchr(str,'\n'))
        *p = '\0';
    printf("longWordLength(): %d\n", longWordLength(str));
    return 0;
}
int longWordLength(char *s)
{
    /* Write your code here */
    int i, j, k, len;
    len = 0;
    j = 0;
    k = 0;

    len = strlen(s);

    for(i=0; i<len; i++)
    {
        if(isalpha(s[i]))
        {
            j++;

            if(s[i+1] == '\0')
            {
                return j;
            }
        }
        else
        {
            if(j>k)
            {
                k = j;
            }
            if(s[i] == ' ')
            {
                j = 0;
            }
        }
    }

    return k;


}
