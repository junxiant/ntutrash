#include <stdio.h>
#define INIT_VALUE -1000

int palindrome(char *str);

int main()
{
    char str[80];
    int result = INIT_VALUE;

    printf("Enter a string: \n");
    gets(str);
    result = palindrome(str);
    if (result == 1)
        printf("palindrome(): A palindrome\n");
    else if (result == 0)
        printf("palindrome(): Not a palindrome\n");
    else
        printf("An error\n");
    return 0;
}

int palindrome(char *str)
{
    int i, j, len;
    len = 0;
    i = 0;
    char *p1, *p2;

    int temp;

    while(*(str+i) != '\0')
    {
        len++;
        i++;
    }

    p1 = str;
    p2 = str+len-1;

    while (p1 < p2)
    {
        if (*p1 != *p2)
            break;
        else
        {
            p1++;
            p2--;
        }
    }
    if (p1 < p2)
        return 0;
    else
        return 1;

}
