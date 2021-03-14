#include <iostream>
#include "childAnimal.h"

using namespace std;

COLOR Animal::getColor()
{
    return _color;
}

string Animal::getName()
{
    return _name;
}

void Animal::speak()
{
    cout << "Animal speaks "<< endl ;
}

void Mammal::eat()
{
    cout << "Mammal eat " << endl;
}

void Mammal::move()
{
    cout << "Mammal moves" << endl;
}

void Dog::speak()
{
    cout << "Woof" << endl;
}

void Dog::move()
{
    cout << "Dog move" << endl;
}

void Dog::eat()
{
    cout << "Dog eat" << endl;
}

void Cat::speak()
{
    cout << "Meow" << endl;
}

void Cat::move()
{
    cout << "Cat move" << endl;
}

void Cat::eat()
{
    cout << "Cat eat" << endl;
}

void Lion::speak()
{
    cout << "Roar" << endl;
}

void Lion::move()
{
    cout << "Lion move" << endl;
}

void Lion::eat()
{
    cout << "Lion eat" << endl;
}


int main() {

int choice = 0;
Mammal **mammal = new Mammal*[3];
mammal[0] = new Dog();
mammal[1] = new Cat();
mammal[2] = new Lion();


	 while(choice!=5)
    {
        cout << "Select the animal to send to Zoo :" << endl;
        cout << "(1) Dog (2) Cat (3) Lion (4) Move all animals (5) Quit" << endl;
        cin >> choice;

        switch(choice)
        {
            case 1:
                mammal[0]->move();
                mammal[0]->speak();
                mammal[0]->eat();
                break;

            case 2:
                mammal[1]->move();
                mammal[1]->speak();
                mammal[1]->eat();
                break;

            case 3:
                mammal[2]->move();
                mammal[2]->speak();
                mammal[2]->eat();
                break;

            case 4:
                for(int i=0; i<3; i++)
                {
                    mammal[i]->move();
                    mammal[i]->speak();
                    mammal[i]->eat();
                }
                break;
        }
    }



 cout << "Program exiting бн. "<< endl ;

return 0;
}