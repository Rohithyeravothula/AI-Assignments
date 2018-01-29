#include <stack>
#include <iostream>
#include <tuple>
#include <cmath>
using namespace std;

bool check_liz_safe(int liz1[],int liz2[]){
	int x1, y1, x2, y2;
	x1 = liz1[0];
	y1 = liz1[1];
	x2 = liz2[0];
	y2 = liz2[1];
	
	//check if both are same position
	if(x1 == x2 && y1 == y2)
		return false;

	//check if both are in horizontal or vertical
	if (x1 == x2 || y1 == y2)
		return false;

	// check if both are in diagonal 
	int slope = (y2-y1)/(x2-x1);
	if(abs(slope) == 1)
		return false;

	return true;
}



int main(){
	int pos1[] = {0,0};
	int pos2[] = {4,9};
	int result = check_liz_safe(pos1, pos2);
	cout << result;
	// printf("")
	return 0;
}

