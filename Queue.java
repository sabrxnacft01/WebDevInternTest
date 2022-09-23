import java.util.Arrays;

//Enqueue dequeue with Array

class ArrQueue implements IQueuable{
	
	String queue[];
	
	ArrQueue(){
		//Array created is an empty array with max size (array not resizable)
		queue = new String[0];
	}
	
	public String[] enqueue(String a) {
	
		String new_queue[] = new String[queue.length + 1];
		
		for(int i = 0; i < queue.length; i++) {
			new_queue[i] = queue[i];
		}
		
		new_queue[queue.length] = a;
		queue = new_queue;
		
		System.out.println("Elements in queue: " + Arrays.toString(new_queue)); //try to print non null values only
		return new_queue;
	}

	public String dequeue() {
		
		//dequeue element at firstItem and shift elements' positions to the left by 1
		System.out.println("Element dequeued: " + queue[0]);
		
		String new_queue[] = new String[queue.length - 1];
		
		int j = 0;
		
		for(int i = 1; i < queue.length; i++) {
			new_queue[j] = queue[i];
			j++;
		}
		
		queue = new_queue;
		
		//for checking
		System.out.println("Elements in queue: " + Arrays.toString(queue));
		
		return null;
	}

	public String[] getQueue() {
				
		for(int i = 0; i < queue.length; i++) {
			System.out.print(queue[i] + ", ");
		}	
		
		return queue;
	}

	public int size() {
		
		int size = queue.length;
		System.out.println("\nQueue size: "+ size);
		return size;
	}	 
}

public class Queue {
	public static void main (String[]args) {
	
		ArrQueue q = new ArrQueue();
		q.getQueue();
		q.enqueue("ha");
		q.enqueue("ba");
		q.enqueue("ra");
		q.getQueue();
		q.dequeue();
		q.size();
		
		
		
	}
	
}