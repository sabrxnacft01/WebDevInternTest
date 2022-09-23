import java.util.Arrays;

//Stack with Array

class ArrStack implements IQueuable{

	String stack[];
	
	ArrStack(){
		stack = new String[0];				
	}
		
	public String[] enqueue(String a) {
		
		String new_stack[] = new String[stack.length + 1];
		
		for(int i = 0; i < stack.length; i++) {
			new_stack[i] = stack[i];
		}
		
		new_stack[stack.length] = a;
		stack = new_stack;
		
		System.out.println("Elements in stack: " + Arrays.toString(new_stack)); //try to print non null values only
		return new_stack;
	}

	public String dequeue() {
		
		int size = stack.length -1;
		
		System.out.println("Element removed from stack: " + stack[size]);
		
		String new_stack[] = new String[size];
		
		for(int i = 0; i < size; i++) {
			new_stack[i] = stack[i];
		}
		
		stack = new_stack;
		
		return null;
	}

	public String[] getQueue() {
		for(int i = 0; i < stack.length; i++) {
			System.out.print(stack[i] + ", ");
		}	
		System.out.print("\n");
		return stack;
	}

	@Override
	public int size() {
		int size = stack.length;
		System.out.println("\nStack size: "+ size);
		return size;
	}
	
}

public class Stack {
	public static void main (String[]args) {
		
		ArrStack q = new ArrStack();
		q.getQueue();
		q.enqueue("ha");
		q.enqueue("ba");
		q.enqueue("ra");
		q.getQueue();
		q.dequeue();
		q.size();
	}
}
