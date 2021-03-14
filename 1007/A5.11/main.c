#include <stdio.h>
#include <string.h>
#define INIT_VALUE -1
int countSubstring(char str[], char substr[]);
int main()
{
    char str[80], substr[80], *p;
    int result=INIT_VALUE;

    printf("Enter the string: \n");
    fgets(str, 80, stdin);
    if (p=strchr(str,'\n'))
        *p = '\0';
    printf("Enter the substring: \n");
    fgets(substr, 80, stdin);
    if (p=strchr(substr,'\n'))
        *p = '\0';
    result = countSubstring(str, substr);
    printf("countSubstring(): %d\n", result);
    return 0;
}

int countSubstring(char str[], char substr[])
{
    /* Write your program code here */
    int i, j, found, count;
    int stringLen, searchLen;
    stringLen = strlen(str);      // length of MAIN string
    searchLen = strlen(substr); // length of substring to be searched
    count = 0;
    for(i=0; i<=stringLen-searchLen; i++)
    { /* Match substring with string */
        found = 1;
        for(j=0; j<searchLen; j++)
        {
            if(str[i+j] != substr[j])
            {
                found = 0;
                break;
            }
        }

        if(found == 1)
        {
            count++;
        }
    }

    return count;
}
