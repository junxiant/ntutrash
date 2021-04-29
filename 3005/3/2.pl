:- discontiguous initialQuery/1.
:- dynamic did/1,didnt/1,queried/1.

/* Initial query */
ask(0):- initialQuery([eat]).

/* If yes, execute function yes else no */
validateQuery(Y) :- did(Y), yes(Y); no(0).

/* Ask another question if kid did not do the activity */
no(0) :- others(L), initialQuery(L).

/* Find random activity */
others(L) :- findnsols(100, X, random(X), L).


/**********************************************************************/

/* Ask about activity and add it to did or didnt list */
initialQuery(L) :-
	print(L), print("Did you "), member(X,L), print(X), print('? y/n/q: '),
	read(Answer), (Answer==q -> abort;Answer==y -> assert(did(X));assert(didnt(X))), validateQuery(X).
	
/* Ask related question */
yes(Y) :- relatedQuery(Y, L), relatedQuery2(L).

/* Finding related activity */
relatedQuery(Y, L) :- findnsols(100, X, related(Y,X), L).


/**********************************************************************/


/* Asking another related question */
query2(Y) :- relatedAct(Y, L), relatedQuery2(L).

/* Find related activities */
relatedAct(Y, L) :- findnsols(100, X, related2(Y,X), L).


/**********************************************************************/

relatedQuery2(L) :-
	findnsols(100,X,queried(X),Queried), list_to_set(L,S), list_to_set(Queried,A), subtract(S,A,Leftover), checkList(Leftover).
	

/**********************************************************************/


/* If no more related questions, ask another random */
checkList([]) :- no(0).

/* Else, ask another */
checkList(R) :- member(X,R), print(X), print('? y/n/q: '),
read(Answer),
(Answer==q -> abort;assert(queried(X))), query2(X).


/**********************************************************************/

/* Removes activity that has been asked */
random(Y) :- activity(A),
findnsols(100,X,did(X),DidList),
findnsols(100,X,didnt(X),DidntList),
append(DidList,DidntList,History),

list_to_set(A,S),
list_to_set(History,H),
subtract(S,H,Leftover),
random_member(Y,Leftover).


/* If no more questions, end */
initialQuery([]) :- print('Im glad you had fun').

/**********************************************************************/


/* Related activities */
related(eat,X):- eat(L),random_member(X,L).
related(play,X):- play(L),random_member(X,L).
related(game,X):- game(L),random_member(X,L).
related(learn,X):- learn(L),random_member(X,L).

/* For relattion */
related2(Y, X) :-
	eat(L),member(X,L),member(Y,L);
	play(L),member(X,L),member(Y,L);
	game(L),member(X,L),member(Y,L);
	learn(L),member(X,L),member(Y,L).
	

	
/* List of activities */
activity([eat, play, game, learn]).

eat(['Did you use a spoon', 'Did you use a fork', 'Did it taste good', 'Did it taste delicious']).
play(['Did you play ping pong', 'Did you play skipping', 'Did you play soccer']).
game(['Did you play with toys', 'Did you play with cards']).
learn(['Did you learn math', 'Did you learn how to read', 'Did you learn spelling', 'Did you learn how to draw']).


/**********************************************************************/


did(nothing).
didnt(nothing).
queried(nothing).
a.
