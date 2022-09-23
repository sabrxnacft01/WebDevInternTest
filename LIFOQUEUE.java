import java.util.Arrays;

class ArrayStack implements IQueuable{

	String stack[];
	int bottom, top, maxSize;
	//top: array index in which last element is stored
	//bottom: array index of first element
	
	ArrayStack(int size){
		bottom = 0;
		top = -1;
		maxSize = size;
		stack = new String[maxSize];				
	}
	
	public boolean stackEmpty() {
		
		boolean stackEmpty;
		if(top < 0) {
			System.out.println("Stack is empty, no elements found");
			stackEmpty = true;
		}
		else {
			stackEmpty = false;
		}
		return stackEmpty;
	}
	
	public String[] enqueue(String a) {
		
		//check if array is full
		if (top < maxSize) {
			//add element to end, increment lastItem index
			top++;
			stack[top] = a;
		}
		else {
			System.out.println("Stack is full");
		}
		
		System.out.println("Elements in stack: " + Arrays.toString(stack)); //try to print non null values only
		return stack;
	}

	public String dequeue() {
		
		if(stackEmpty() == false) {
			System.out.println("Element removed from stack: " + stack[top]);
			stack[top] = "";
			top--;
		}
		return null;
	}

	public String[] getQueue() {
		if(stackEmpty() == false) {
			for(int i = top; i >= bottom; i--) {
				System.out.println(stack[i]);
			}
		}
		return stack;
	}

	@Override
	public int size() {
		int size = top + 1;
		System.out.println("\nStack size: "+ size);
		return size;
	}
	
}

public class LIFOQUEUE {
	public static void main (String[]args) {
		
		ArrayStack q = new ArrayStack(5);
		q.getQueue();
		q.enqueue("Happy");
		q.enqueue("Birthday");
		q.enqueue("Ba");
		q.enqueue("ha");
		q.dequeue();
		q.getQueue();
		q.dequeue();
		q.getQueue();
		q.size();
	}
}
