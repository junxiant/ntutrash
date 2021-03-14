#include <stdio.h>
int gcd1(int num1, int num2);
void gcd2(int num1, int num2, int *result);
int main()
{
    int x,y,result=-1;
    printf("Enter 2 numbers: \n");
    scanf("%d %d", &x, &y);
    printf("gcd1(): %d\n", gcd1(x, y));
    gcd2(x,y,&result);
    printf("gcd2(): %d\n", result);
    return 0;
}
int gcd1(int num1, int num2)
{
    /* Write your code here */
 int result;
 if (num1 > num2){
    result=num1;
 }
 else{
    result=num2;
 }

 while (result!=0){
    if(num1%result==0 && num2%result==0){
        return result;
    }
    else{
        result--;
    }
 }
}

void gcd2(int num1, int num2, int *result)
{
    /* Write your code here */
     int temp_result;
 if (num1 > num2){
    temp_result=num1;
 }
 else{
    temp_result=num2;
 }

 while (temp_result!=0){
    if(num1%temp_result==0 && num2%temp_result==0){
        (*result)=temp_result;
        break;
    }
    else{
        temp_result--;
    }
 }
}
