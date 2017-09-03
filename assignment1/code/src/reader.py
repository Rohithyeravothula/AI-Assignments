def str_to_int(a):
	return [int(i) for i in list(a)]

def read_input():
	with open("input.txt", 'r') as f:
		algo_name = f.readline()
		n = int(f.readline().strip())
		p = int(f.readline().strip())
		data = list(map(str_to_int, f.read().split()))
		return (n, p, data)
	