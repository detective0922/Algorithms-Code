package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

public class Test1_3_3 {
	//a: 4 3 2 1 0 9 8 7 6 5 is possible
	//push 0 1 2 3 4
	//pop 4 3 2 1 0  
	//push 5 6 7 8 9
	//pop 9 8 7 6 5
	
	//b:4 6 8 7 5 3 2 9 0 1 is impossible
	//push 0 1 2 3 4
	//pop 4,    left 0 1 2 3
	//push 5 6
	//pop 6,    left 0 1 2 3 5
	//push 7 8
	//pop 8 7 5 3 2  left 0 1
	//push 9
	//pop 9 1 0, not 9 0 1  
	//so b is impossible
	
	//c: 2 5 6 7 4 8 9 3 1 0 is possible
	//push 0 1 2
	//pop 2,     left 0 1
	//push 3 4 5
	//pop 5,     left 0 1 3 4
	//push 6
	//pop 6
	//push 7
	//pop 7 4,   left 0 1 3
	//push 8
	//pop 8
	//push 9
	//pop 9 3 1 0
	
	//d:4 3 2 1 0 5 6 7 8 9 is possible
	//push 0 1 2 3 4
    //pop 4 3 2 1 0
	//push 5
	//pop 5
	//push 6
	//pop 6
	//push 7
	//pop 7
	//push 8
	//pop 8
	//push 9
	//pop 9
	
	//e: 1 2 3 4 5 6 9 8 7 0 is possible
	//push 0 1
	//pop 1
	//push 2
	//pop 2
	//push 3
	//pop 3
	//push 4
	//pop 4
	//push 5
	//pop 5
	//push 6
	//pop 6
	//push 7 8 9
	//pop 9 8 7 0
	
	//f: 0 4 6 5 3 8 1 7 2 9 is impossible
	//push 0
	//pop 0
	//push 1 2 3 4
	//pop 4,      left 1 2 3
	//push 5 6
	//pop 6 5 3    left 1 2
	//push 7 8
	//pop 8       left 1 2 7, 1 7 2 is impossible
	
	//g: 1 4 7 9 8 6 5 3 0 2 is possible
	//push 0 1
	//pop 1,     left 0
	//push 2 3 4
	//pop 4,     left 0 2 3
	//push 5 6 7
	//pop 7,     left 0 2 3 5 6
	//push 8 9
	//pop 9 8 6 5 3 2 0
	
	//h:2 1 4 3 6 5 8 7 9 0 is possible
	//push 0 1 2
	//pop 2 1,      left 0
	//push 3 4
	//pop 4 3,      left 0
	//push 5 6
	//pop 6 5,      left 0
	//push 7 8
	//pop 8 7,      left 0
	//push 9
	//pop 9 0
	
}
