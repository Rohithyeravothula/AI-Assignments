from core import *
from reader import *
from collections import deque


def algo(n,p): 
	initial_state = (p, [], 0)
	S = []
	S.append(initial_state)
	goal = None
	while True:
		if len(S) == 0:
			print("no solution as no nodes remain in stack")
			goal = False
			break
		cur_liz, cur_state, cur_depth = S.pop()
		if cur_depth+1<n:
			S.append((cur_liz, cur_state, cur_depth+1))

		for col in range(0, n):
			new_state = addLizard(cur_state, (cur_depth, col))
			if issafe(new_state):
				if cur_liz == 1:
					goal = new_state
					break
				else:
					if cur_depth+1<n:
						S.append((cur_liz-1, new_state, cur_depth+1))
		if goal is not None:
			break
	return goal

def main():
	n,p,data = read_input()
	result = algo(n, p)
	print(result)

import time
t1 = time.time()
main()
print(time.time() - t1)




