#include <iostream>
#include "animal.h"

using namespace std;

class Dog : public Mammal
{
	private:
    string _owner;

    public :
        Dog() : Mammal()
        {
			_owner = "Dog owner";
            cout << "constructing Dog object " << Animal::getName() << endl;
        }

        Dog(string n, COLOR c, string owner) : Mammal(n, c)
        {
            cout << "constructing Dog object " << Animal::getName() << ", it has the color of ";

            switch(Animal::getColor())
            {
                case Green:
                    cout << "Green" << endl;
                    break;

                case Blue:
                    cout << "Blue" << endl;
                    break;

                case White:
                    cout << "White" << endl;
                    break;

                case Black:
                    cout << "Black" << endl;
                    break;

                case Brown:
                    cout <<"Brown" << endl;
                    break;
            }
        }

        ~Dog()
        {
            cout << "destructing Dog object " << Animal::getName() << endl;
        }

		void speak();
        void eat();
        void move();

};

class Cat : public Mammal
{
	private:
    string _owner;

    public :
        Cat() : Mammal()
        {
			_owner = "Cat owner";
            cout << "constructing Cat object " << Animal::getName() << endl;
        }

        Cat(string n, COLOR c, string owner) : Mammal(n, c)
        {
            cout << "constructing Cat object " << Animal::getName() << ", it has the color of ";

            switch(Animal::getColor())
            {
                case Green:
                    cout << "Green" << endl;
                    break;

                case Blue:
                    cout << "Blue" << endl;
                    break;

                case White:
                    cout << "White" << endl;
                    break;

                case Black:
                    cout << "Black" << endl;
                    break;

                case Brown:
                    cout <<"Brown" << endl;
                    break;
            }
        }

        ~Cat()
        {
            cout << "destructing Cat object " << Animal::getName() << endl;
        }
		
		void speak();
        void eat();
        void move();

};


class Lion : public Mammal
{
	private:
    string _owner;

    public :
        Lion() : Mammal()
        {
			_owner = "Lion owner";
            cout << "constructing Lion object " << Animal::getName() << endl;
        }

        Lion(string n, COLOR c, string owner) : Mammal(n, c)
        {
            cout << "constructing Lion object " << Animal::getName() << ", it has the color of ";

            switch(Animal::getColor())
            {
                case Green:
                    cout << "Green" << endl;
                    break;

                case Blue:
                    cout << "Blue" << endl;
                    break;

                case White:
                    cout << "White" << endl;
                    break;

                case Black:
                    cout << "Black" << endl;
                    break;

                case Brown:
                    cout <<"Brown" << endl;
                    break;
            }
        }

        ~Lion()
        {
            cout << "destructing Lion object " << Animal::getName() << endl;
        }

		void speak();
        void eat();
        void move();
 
};
