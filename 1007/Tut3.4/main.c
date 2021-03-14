#include <stdio.h>
#include <stdlib.h>
void inputXY(double *x1, double *y1, double *x2, double *y2);
void outputResult(double dist);
double calDistance1(double x1, double y1, double x2, double y2);
void calDistance2(double x1, double y1, double x2, double y2, double *dist);

int main()
{
    double x1, y1, x2, y2, distance = -1;

    inputXY(&x1, &y1, &x2, &y2);
    distance =  calDistance1(x1,y1,x2,y2);
    printf("calDistance1(): ");
    outputResult(distance);

    calDistance2(x1, y1, x2, y2, &distance);
    printf("calDistance2(): ");
    outputResult(distance);

    return 0;
}

void inputXY(double *x1, double *y1, double *x2, double *y2)
{
    printf("Input x1 y1 x2 y2: \n");
    scanf("%lf %lf %lf %lf", x1, y1, x2, y2);
}

double calDistance1(double x1, double y1, double x2, double y2)
{
    x2 = pow(x2 - x1,2);
    y2 = pow(y2 - y1, 2);
    return sqrt(x2+y2);
}

void calDistance2(double x1, double y1, double x2, double y2, double *dist)
{
    x2 = pow(x2 - x1,2);
    y2 = pow(y2 - y1, 2);
    *dist = sqrt(x2+y2);

}

void outputResult(double dist)
{
    printf("Result: %f\n", dist);
}
