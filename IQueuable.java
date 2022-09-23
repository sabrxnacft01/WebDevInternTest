
public interface IQueuable {
	
	String[] enqueue(String a);
	String dequeue();
	String[] getQueue();
	int size();	
}

