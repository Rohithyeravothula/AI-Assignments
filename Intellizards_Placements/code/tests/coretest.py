import sys 
sys.path.insert(0, "../src/")
import core

	

def test_check_liz_eat():
	tests_passed = 0
	inp = [
	((0,0), (0,0), False),
	((3,4), (3,4), False), 

	((3,4), (2,4), False), #horizontal
	((3,4), (6,4), False), 

	((3,4), (3,6), False), #vertical
	((3,4), (3,1), False),

	((3,4), (4,3), False), #right diagonal
	((3,4), (1,6), False),
	
	((3,4), (1,2), False), #left diagonal
	((3,4), (5,6), False),

	((0,0), (1,2), True), # true cases
	((0,0), (1,10), True), 
	((0,0), (10,1), True), 
	((0,0), (5,6), True)
	]
	for i in inp:
		output = core.check_liz_eat(i[0], i[1])
		if output == i[2]:
			tests_passed += 1
		else:
			print("Failed for input ", i)
	print("passed %d cases out of %d" % (tests_passed, len(inp)))

	
test_check_liz_eat()