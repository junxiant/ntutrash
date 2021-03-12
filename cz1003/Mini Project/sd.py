def foo(q):
    q.append(5)
    return q

a = [1,2,3,4]
print(a[::-1])
foo(a)

print(len(a))