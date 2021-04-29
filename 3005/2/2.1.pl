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
is_older(A, B):- older(A, X),is_older(X, B).

in_order(X, Y) :- child(prince, X), child(princess, Y).
in_order(X, Y) :- child(prince, X), child(prince, Y), is_older(X, Y).
in_order(X, Y) :- child(princess, X), child(princess, Y), is_older(X, Y).

insert(X, [], [X]).
insert(X, [Y|Z], [X, Y|Z]) :- in_order(X, Y).
insert(X, [Y|Z], [Y|Z2]) :- not(in_order(X, Y)), insert(X, Z, Z2).

isort([], Po, Po).
isort([J|Z], Po, Y) :- insert(J, Po, Po2), isort(Z, Po2, Y).

insert_sort(X, Y) :- isort(X, [], Y).

successors(X, Y) :- insert_sort(X, Y).

sList(SList):- findall(Y, child(_,Y), ChildList), successors(ChildList, SList).
