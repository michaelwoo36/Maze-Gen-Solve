package application;

public class LinkedList {
	
	private static class Node{
		
		public int data;
		public Node prev;
		public Node next;
		
		public Node(int d, Node p, Node n) {
			
			data = d;
			prev = p;
			next = n;
		}
	}
	public LinkedList() {
		
		doClear();
	}
	public void clear() {
		
	}
	
	private void doClear() {
		
		begin = new Node(10,null,null);
		end = new Node(8, begin, null);
		begin.next = end;
		
		theSize = 0;
		modCount++;
	}
	public int size() {
		
		return theSize;
	}
	public boolean isEmpty() {
		
		return size() == 0;
	}
	public boolean add(int x) {
		add(size(),x);
		return true;
	}
	public void add(int idx, int x) {
		
		addBefore( getNode( idx, 0, size()), x);
	}
	public int get(int idx) {
		Node p =  getNode(idx);
		return p.data;
	}
	public int set(int idx, int newVal) {
		
		Node p = getNode(idx);
		int oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	public int remove(int idx) {
		
		return remove(getNode(idx));
		
	}
	private void addBefore(Node p, int x) {
		
		Node help = new Node(x, p.prev, p);
		help.prev.next = help;
		p.prev = help;
		theSize++;
		modCount++;
	}
	private int remove(Node p) {
		
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		
		return p.data;
	}
	private Node getNode(int idx) {
		
		return getNode(idx, 0, size()-1);
	}
	private Node getNode(int idx, int lower, int upper) {
		Node p;
		
		if(idx < lower || idx > upper) {
			throw new IndexOutOfBoundsException();
		}
		
		if(idx < size() / 2) {
			
			p = begin.next;
			for(int i = 0; i < idx; i++) {
				p = p.next;
			}
		}
		else {
			p = end;
			for(int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}
		return p;
	}
	
	/*public java.util.Iterator <Integer>iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements java.util.Iterator<Integer>{
		
		private Node current = begin.next;
		private int expectedMod = modCount;

		
		public boolean hasNext() {
			return current != end;
		}
		
		public Integer next() {
			
			if(modCount != expectedMod) {
				throw new java.util.ConcurrentModificationException();
			}
			if(!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			int nextItem = current.data;
			current = current.next;
			return nextItem;
		}
		
		
	}*/
	
	private int theSize;
	private int modCount = 0;
	private Node begin;
	private Node end;
}
