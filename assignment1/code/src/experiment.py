

class Name(object):
	def __init__(self, name, age):
		self.name = name
		self.age = age
		
import time


n = 4000000

t1 = time.time()
a = []
for i in range(0, n):
	a.append(("Rohith", 23))

print(a[n-1])
print(time.time() - t1)

t2 = time.time()
b=[]
for i in range(0, n):
	b.append(Name("Rohith", 23))
print(time.time() - t2)
