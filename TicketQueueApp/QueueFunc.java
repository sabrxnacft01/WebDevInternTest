package TicketQueueApp;

public class QueueFunc {
	
	GUI gui;
	String queue[];
	int count = 0;
	String queue_num = "";
	
	public QueueFunc(GUI main_gui) {
		
		queue = new String[0];
		gui = main_gui;
	}
	
	//when customer click take new number
	public String[] enqueue() {
		
		count++;
		queue_num = String.valueOf(count);
		
		String new_queue[] = new String[queue.length + 1];
		
		for(int i = 0; i < queue.length; i++) {
			new_queue[i] = queue[i];
		}
		
		new_queue[queue.length] = queue_num;
		queue = new_queue;
		
		return new_queue;
	}
	
	//when staff clicks Call next 
	public String dequeue() {
		
		//dequeue element at firstItem and shift elements' positions to the left by 1		
		String new_queue[] = new String[queue.length - 1];
		
		int j = 0;
		
		for(int i = 1; i < queue.length; i++) {
			new_queue[j] = queue[i];
			j++;
		}
		
		queue = new_queue;
		
		return null;
	}

	public int size() {
		
		int size = queue.length;
		return size;
	}	 
		

}
