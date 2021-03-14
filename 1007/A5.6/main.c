#include <stdio.h>
#include <string.h>
#define N 20
char *longestStrInAr(char str[N][40], int size, int *length);
int main()
{
    int i, size, length;
    char str[N][40], first[40], last[40], *p, *result;
    char dummychar;

    printf("Enter array size: \n");
    scanf("%d", &size);
    scanf("%c", &dummychar);
    for (i=0; i<size; i++)
    {
        printf("Enter string %d: \n", i+1);
        fgets(str[i], 40, stdin);
        if (p=strchr(str[i],'\n'))
            *p = '\0';
    }
    result = longestStrInAr(str, size, &length);
    printf("longest: %s \nlength: %d\n", result, length);
    return 0;
}
char *longestStrInAr(char str[N][40], int size, int *length)
{
    /* Write your code here */
    int i, j;
    int longest = 0;
    int l = 0;

    char *lstr;

    for(i=0;i<size;i++)
    {
        l = strlen(str[i]);
        if(l>longest)
        {
            longest = l;
            lstr = str[i];
        }
    }
    *length = longest;
    return lstr;

}
