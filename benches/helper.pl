%in case don't have append
append(A, B) :-
	must_be(list, A),
	append_(A, B).

append([], A, A).
append([A|B], C, [A|D]) :-
	append(B, C, D).
	
member_(_, A, A).
member_([C|A], B, _) :-
	member_(A, B, C).
member(B, [C|A]) :-
	member_(A, B, C).