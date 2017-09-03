from core import *
from reader import *
import copy

def printQ(q):
	for i in q:
		print(i.liz_count)
		print(i.liz_pos)


def algo(n, p):
	Q = []
	initial_state = Board(p, [], n)
	Q.append(initial_state)
	goal = None
	while True:
		if len(Q) == 0:
			print("State space search halted due to no entires in queue")
			goal = False
			break
		
		# printQ(Q)
		node = Q.pop(0)
		print("iteration : %d , size of Q is %d " % (node.depth, len(Q)))
		# print("iteration %d" % (node.depth+1))

		# can be possible that there is no lizard possible at current depth
		bck_node = copy.deepcopy(node)
		bck_node.depth += 1
		if bck_node.depth < n:
			Q.append(bck_node)
		

		# print("n size %d" % (n))
		for col in range(0, n):
			# print((node.depth, i))
			newstate = addLizard(node.liz_pos, (node.depth, col))
			# print(col)
			if issafe(newstate):
				if node.liz_count == 1:
					goal = newstate
					break
				else:
					new_node = copy.deepcopy(node)
					new_node.liz_count = node.liz_count - 1
					new_node.liz_pos = newstate
					new_node.depth = node.depth + 1
					new_node.size = node.size
					if new_node.depth < n:
						Q.append(new_node)
		if goal is not None:
			break
	return goal

def main():
	n,p,data = read_input()
	result = algo(n, p)
	print(result)


# print(issafe([(0,0), (1,2)]))
# print(check_liz_eat((0,0), (1,2)))

main()
