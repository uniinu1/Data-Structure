package project10final;
import java.util.Scanner;
interface Queue<T> {

	public void enqueue(T item);
	public T dequeue();
	public T peek();	
	public boolean isEmpty();

}

class PriorityQueue<T extends Comparable<T>> implements Queue<T> {

	private T[] items;
	private int size;
	private int maxSize;
	
	public PriorityQueue(int max) {
		items = (T[]) new Comparable[max];
		size = 0;
		maxSize = max;
	}
	public PriorityQueue() { 
		this(3);
	}

	public void enqueue(T item) {
		if(size == items.length)
			resize();
		items[size++] = item;
		moveUp(0, size - 1);
	}
	
	private void moveUp(int first, int last) {
		int parent = (last - 1) / 2;
		int child = last;
		while(child >= first) {
			if(items[parent].compareTo(items[child]) < 0) {
				swap(parent, child);
				child = parent;
				parent = (child - 1) / 2;
			}
			else
				break;
		}
	}

	public T dequeue() {
		if (size == 0) 
			throw new java.util.NoSuchElementException(
					"dequeue(): queue empty");
		T item = items[0];
		items[0] = items[--size];
		moveDown(0, size - 1);
		return item;
	}
	
	private void moveDown(int first, int last) {
		int maxchild , leftchild, rightchild;
		leftchild = 2 * first + 1;
		while(leftchild <= last) {
			if(leftchild == last) 
				maxchild = leftchild;
			else {
				rightchild = 2 * first + 2;
				if(items[leftchild].compareTo(items[rightchild]) <= 0)
					maxchild = rightchild;
				else
					maxchild = leftchild;
			}
			if(items[first].compareTo(items[maxchild]) < 0) {
				swap(first, maxchild);
				first = maxchild;
				leftchild = 2 * first + 1;
			}
			else
				break;
			
		}
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new java.util.NoSuchElementException(
						"peek(): queue empty");
		return items[0];

	}
	
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	private void resize() {
		T[] newItems = (T[]) new Comparable[2*items.length];
		for (int i = 0; i < items.length; i++)
			newItems[i] = items[i];
		items = newItems;
		maxSize = 2*maxSize;
	}
	
	private void swap(int x, int y) {
		T tmp = items[x];
		items[x] = items[y];
		items[y] = tmp;
	}
	
	public String toString() {	// 배열에 있는 데이터를 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size-1; i++)
			sb.append(items[i].toString() + " ");
		sb.append(items[size-1].toString() + "\n");
		return sb.toString();

	
	}
	public void display() 	{	// 배열에 있는 데이터를 트리 형태로 출력
		int count = 0;
		display(items, count, 0);
	}
	
	public void display(T[] tree, int count, int now) {
		
		int right = 2 * now + 2;
		if(right < size)
			display(tree, count+1, right);
		
		for(int i = 0; i < count; i++) {
			System.out.print("\t");
		}
		System.out.println(tree[now]);
		
		int left = 2 * now + 1;
		if(left < size) 
			display(tree, count+1, left);
	}
	
	public T kthLargestItem(int kth)  {
		if(size == 0)
			throw new java.util.NoSuchElementException(
					"kthLargestItem(): queue empty");
		else if(size < kth)
			throw new java.util.NoSuchElementException(
					"kthLargestItem(): kth is bigger then queue");
		
		T item = peek();
		
		for (int i = 0; i < kth; i++) {
			item = dequeue();
			items[size] = item;
		}
		
		for (int i = 0; i < kth; i++) {
			enqueue(items[size]);
		}
		
		return item;
			
	}
	

	
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		String command;
		int data;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a command: e(nqueue), d(equeue), h(eight),\n"
				+ "k(th largerst item), p(rint), pt(print in tree form), or q(uit)");
		while (true) {
			System.out.print("> ");
			command = in.next();
			if (command.equals("e")) {
				data = in.nextInt();
				pq.enqueue(data); 
			}
			else if (command.equals("d")) {
				int item = pq.dequeue();
				System.out.println(item + " removed.");
			}
			else if (command.equals("p")) {
				System.out.print(pq);
			}
			else if (command.equals("k")) {
				int kth = in.nextInt();
				int item = pq.kthLargestItem(kth);
				System.out.println(item);
			}
			else if (command.equals("pt"))
				pq.display();			
			else if (command.equals("q")) {
				System.out.println("-- End --");
				break;
			}
		}
		in.close();
	}


}


