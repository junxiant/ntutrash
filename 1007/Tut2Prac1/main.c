#include <stdio.h>
int main()
{
 char ch;
 printf("Enter a character: \n");
 scanf("%c", &ch);
 /* Write your code here */
 if(ch >= 'A' && ch <= 'Z')
 {
     printf("Upper case letter\n");
 }
 else if(ch >= 'a' && ch <= 'z')
 {
     printf("Lower case letter\n");
 }
 else if(ch >= '0' && ch <= '9')
 {
     printf("Digit\n");
 }
 return 0;
}
