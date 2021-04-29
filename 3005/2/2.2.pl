child(prince, charles).
child(princess, ann).
child(prince, andrew).
child(prince, edward).

older(charles, ann).
older(ann, andrew).
older(andrew, edward).

male(A):- child(prince, A).
female(A):- child(princess, A).

is_older(X, Y):- older(X, Y).
is_older(A, B):- older(A, X), is_older(X, B).

insert(X, [], [X]).
insert(X, [Y|Z], [X, Y|Z]) :- is_older(X, Y).
insert(X, [Y|Z], [Y|Z2]) :- not(is_older(X, Y)), insert(X, Z, Z2).

isort(X, Y) :- isort2(X, [], Y).
isort2([], Po, Po).
isort2([J|Z], Po, Y) :- insert(J, Po, Po2), isort2(Z, Po2, Y).

sList(SList):-findall(Y, child(_,Y), ChildList), isort(ChildList, SList).
