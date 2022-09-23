import java.util.Arrays;

class ArrayQueue implements IQueuable{
	
	String queue[];
	int firstItem, lastItem, maxSize;
	//lastItem: array index in which last element is stored
	//firstItem: array index of first element
	
	ArrayQueue(int size){
		
		//Array created is an empty array with max size (array not resizable)
		firstItem = 0;
		lastItem = 0;
		maxSize = size;
		queue = new String[maxSize];
	}
	
	public boolean queueEmpty() {
		
		boolean queueEmpty;
		if(lastItem == firstItem) {
			System.out.println("Queue is empty, no elements found");
			queueEmpty = true;
		}
		else {
			queueEmpty = false;
		}
		return queueEmpty;
	}
	
	public String[] enqueue(String a) {
	
		//check if array is full
		if (lastItem < maxSize) {
			//add element to end, increment lastItem index
			queue[lastItem] = a;
			lastItem ++;
		}
		else {
			System.out.println("Queue is full");
		}
		
		System.out.println("Elements in queue: " + Arrays.toString(queue)); //try to print non null values only
		return queue;
	}

	public String dequeue() {
		
		if(queueEmpty() == false) {
			//dequeue element at firstItem and shift elements' positions to the left by 1
			System.out.println("Element dequeued: " + queue[firstItem]);
			queue[firstItem] = "";
			for(int i = 0; i < lastItem; i++) {
				queue[i] = queue[i + 1];
			}
			lastItem--;
		}		
		//for checking
		System.out.println("Elements in queue: " + Arrays.toString(queue));
		
		return null;
	}

	public String[] getQueue() {
		
		if(queueEmpty() == false) {
			for(int i = 0; i < lastItem; i++) {
				System.out.print(queue[i] + ", ");
			}	
		}
		return queue;
	}

	public int size() {
		int size = lastItem - firstItem;	
		System.out.println("\nQueue size: "+ size);
		return size;
	}	 
}

public class FIFOQUEUE {
	public static void main (String[]args) {
	
		ArrayQueue q = new ArrayQueue(5);
		q.getQueue();
		q.enqueue("Happy");
		q.enqueue("Birthday");
		q.enqueue("Ba");
		q.enqueue("ha");
		q.dequeue();
		q.getQueue();
		q.size();
		
		
	}
	
}
