#include <stdio.h>
#define SIZE 20
typedef struct
{
    char title[81];
    char lastname[81];
    char firstname[81];
    char publisher[81];
    int day, month, year;
} Booktype;
void readBook(Booktype *book);
void printBook(Booktype book);
int main()
{
    Booktype book[SIZE];
    char repeat = 'y',size=0;

    do
    {
        readBook(&book[size]);
        printf("The book information:\n");
        printBook(book[size]);
        printf("Continue ('y' or 'n'): ");
        scanf("%c", &repeat);
        size++;
    }
    while (repeat == 'y');
}
void readBook(Booktype *book)
{
    char *p;

    printf("Enter the title of the book: \n");
    scanf("\n");
    fgets(book->title, 80, stdin);
    if (p=strchr(book->title,'\n')) *p= '\0';

    printf("Enter the author's first name: \n");
    fgets(book->firstname, 80, stdin);
    if (p=strchr(book->firstname,'\n')) *p= '\0';

    printf("Enter the author's last name: \n");
    fgets(book->lastname, 80, stdin);
    if (p=strchr(book->lastname,'\n')) *p= '\0';

    printf("Enter date as (dd-mm-yy): \n");
    scanf("%d %d %d", &book->day, &book->month, &book->year);

    printf("Enter the publisher name: \n");
    scanf("\n");
    fgets(book->publisher, 80, stdin);
    if (p=strchr(book->publisher,'\n')) *p= '\0';

}
void printBook(Booktype book)
{
    printf("Title: %s\n", book.title);
    printf("Author: %s %s\n", book.firstname, book.lastname);
    printf("Date: %d%d%d\n", book.day, book.month, book.year);
    printf("Publisher: %s\n", book.publisher);

}
