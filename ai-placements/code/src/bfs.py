from core import *
from reader import *
from collections import deque

def algo(n, p):
	Q = deque()
	# Q = []
	# initial_state = Board(p, [], n)
	initial_state = (p, [], 0)  # num of liz, state, depth
	Q.append(initial_state)
	goal = None
	while True:
		if len(Q) == 0:
			print("State space search halted due to no entires in queue")
			goal = False
			break
		
		# cur_liz, cur_state, cur_depth = Q.pop(0)
		cur_liz, cur_state, cur_depth = Q.popleft()

		if cur_depth+1 < n:
			Q.append((cur_liz, cur_state, cur_depth+1))
		

		# print("n size %d" % (n))
		for col in range(0, n):
			# print((node.depth, i))
			newstate = addLizard(cur_state, (cur_depth, col))
			# print(col)
			if issafe(newstate):
				if cur_liz == 1:
					goal = newstate
					break
				else:
					if cur_depth+1 < n:
						Q.append((cur_liz-1, newstate, cur_depth+1))
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
