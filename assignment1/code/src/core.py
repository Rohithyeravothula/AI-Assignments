def isgoal(liz):
	if liz == 0:
		return True
	return False

def addLizard(state, pos):
	return state + [pos]

# add functionality to check if tree obstructs
def check_liz_eat(pos1, pos2, trees = []):
	x1 = pos1[0]
	y1 = pos1[1]
	x2 = pos2[0]
	y2 = pos2[1]

	#check if both are same position
	if pos1 == pos2:
		return False

	# #check if both are horizontal or vertical with a gap of 
	# if x1 == x2-1 or y1 == y2-1 or x2 == x1-1 or y2 == y1-1:
	# 	return False

	#check if both are in horizontal or vertical
	if x1 == x2 or y1 == y2:
		return False

	#check if both are in diagonal 
	slope = (y2-y1)/(x2-x1)
	if abs(slope) == 1:
		return False

	return True


# time complexity is o(n**2)
def issafe(state):
	l = len(state)
	for i in range(0, l):
		for j in range(0, i):
			if not check_liz_eat(state[i], state[j]):
				return False
	return True



class Board(object):
	def __init__(self, liz_count, liz_pos, size):
		self.liz_count = liz_count
		self.liz_pos = liz_pos
		self.size = size # remove as it is not used anywhere 
		self.depth = 0
