company(sumsum).
company(appy).

smartPhoneTech(galactica-s3).
developed(galactica-s3, sumsum).
boss(stevey).
stole(stevey, galactica-s3).

competitor(sumsum, appy).
rival(Comp):- competitor(Comp, appy);competitor(appy, Comp).
business(Tech):-smartPhoneTech(Tech).


unethical(X):-boss(X),stole(X, Bus),business(Bus),developed(Bus, Comp1),rival(Comp1).
