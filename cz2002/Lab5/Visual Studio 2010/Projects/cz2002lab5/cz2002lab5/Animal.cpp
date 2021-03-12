#include <iostream>
#include <string>

using namespace std ;
enum COLOR { Green, Blue, White, Black, Brown } ;

class Animal {
    public :
        Animal() : _name("unknown") {
            cout << "constructing Animal object "<< _name << endl ;
        }
		Animal(string n, COLOR c) : _name(n), _color(c) {
		cout << "constructing Animal object "<< _name << ", it has the color of " ;

            switch(c)
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
			        ~Animal(){
            cout << "destructing Animal object " << Animal::getName() << endl;
        }

		virtual void speak() {
			cout << "Animal speaks "<< endl ; }

		virtual void move() =0;



        COLOR getColor()
        {
            return _color;
        }

        string getName()
        {
            return _name;
        }
	private :
        string _name;
        COLOR _color ;

};
class Mammal : public Animal
{
    public :
        Mammal() : Animal()
        {
            cout << "constructing Mammal object " << Animal::getName() << endl;
        }

        Mammal(string n, COLOR c) : Animal(n, c)
        {
            cout << "constructing Mammal object " << Animal::getName() << ", it has the color of ";

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

        ~Mammal()
        {
            cout << "destructing Mammal object " << Animal::getName() << endl;
        }


        virtual void eat()
        {
            cout << "Mammal eat " << endl;
        }

        virtual void move()
        {
            cout << "Mammal moves" << endl;
        }
};

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

		 void speak()
		{
        cout << "Woof" << endl;
		 }


        void eat()
        {
            cout << "Dog eat " << endl;
        }

        void move()
        {
            cout << "Dog moves" << endl;
        }
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

		 void speak()
		{
        cout << "Meow" << endl;
		 }


        void eat()
        {
            cout << "Cat eat " << endl;
        }

        void move()
        {
            cout << "Cat moves" << endl;
        }
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

		 void speak()
		{
        cout << "Roar" << endl;
		 }


        void eat()
        {
            cout << "Lion eat " << endl;
        }

        void move()
        {
            cout << "Lion moves" << endl;
        }
};


int main() {
//Animal a; //for 3.1.2
//Animal b("Tommy", Brown); //for 3.1.2
//Mammal a("Timmy", White); //for 3.2.1
//Dog a("Jacky", Blue,"Owner"); for 3.2.2
//a.speak() ;
//a.move(); //for 3.2.2
//b.speak() ;

// Animal *animalPtr = new Dog("Lassie", White, "Andy"); //for 3.3.1
//animalPtr -> speak(); //for 3.3.1
//animalPtr -> move(); //for 3.3.1
//delete animalPtr; //for 3.3.1

//Dog dogi("Lassie", White, "Andy"); //for 3.3.3
//Mammal *aniPtr = &dogi ; //for 3.3.3
//Mammal &aniRef = dogi ; //for 3.3.3
//Mammal aniVal = dogi ; //for 3.3.3
//aniPtr->speak() ; //for 3.3.3
	//aniRef.speak() ; //for 3.3.3
		//aniVal.speak() ; //for 3.3.3


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
