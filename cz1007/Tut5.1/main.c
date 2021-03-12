#include <stdio.h>
#include <string.h>

void processString(char *str, int *totVowels, int *totDigits);

int main()
{
    char str[50], *p;
    int totVowels, totDigits;

    printf("Enter the string: \n");
    fgets(str, 80, stdin);

    if (p=strchr(str,'\n'))
        *p = '\0';
    processString(str, &totVowels, &totDigits);
    printf("Total vowels = %d\n", totVowels);
    printf("Total digits = %d\n", totDigits);

    return 0;

}

void processString(char *str, int *totVowels, int *totDigits)
{
    *totVowels = 0;
    *totDigits = 0;

    int i;
    int len = 0;

    len = strlen(str);
    printf("len is %d\n", len);

    for(i = 0; i < len; i++)
    {
        if(str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' || str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
        {
            (*totVowels)++;
        }
        else if(str[i] >= '0' && str[i] <= '9')
            (*totDigits)++;
    }


}
