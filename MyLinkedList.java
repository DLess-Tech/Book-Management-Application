import MyLinkedList.LinkedListIterator;
import MyLinkedList.Node;

public class MyLinkedList<E> implements MyList<E> {
	
	private Node<E> head, tail;
	private int size = 0; //Number of elements in the list
	
	//Create an empty list.
	public MyLinkedList() {
	}
	
	
	//Create a list from an array of objects.
	public MyLinkedList(E[] objects) {
	
		//Loop through each item in the array and add them to the list.
		for(int i = 0; i< objects.length; i ++)
			add(objects[i]);
	}
	
	//Return the head element in the list
	public E getFirst() {
		
		//if there are no items in the list, return null
		if(size == 0) {
			return null;
		}
		else { //else, return the pointer to the head(start) of the list.
			return head.element;
		}
	}
	//Return the last element in the list
	public E getLast() {
		if(size == 0) { //if there are no items in the list, return null
			return null;
		}
		else { //else, return the pointer to the Tail(End) of the list.
			return tail.element;
		}
	}
	
	
	//Add an element to the beginning of the list
	public void addFirst(E e) {
		Node <E> newNode = new Node<>(e); //Create a new node with the element data
		newNode.next = head; // point the new node at the prior head.
		head = newNode; // Reassign head to point to the new node.
		size++; // increase the list size
		
		if(tail == null) { //The new node is the only node in list
			tail = head;
		}
	}

	
	//Add an element to the end of the list
	public void addLast(E e) {
		Node <E> newNode = new Node<>(e); // Create a new node for element e
		
		if (tail == null) {
			head = tail = newNode; //The new node is the only node in the list.
		}
		else {
			tail.next = newNode; // Point the prior tail to the next node
			tail = newNode; // tail now points to the new last node.
		}
		size++; // Increase size.
	}
	
	
	@Override /**Add a new element at the specified index
	*in this list. The index of the head elements is 0 */
	public void add(int index, E e) {
		
		if(index == 0) { //Use existing function to add to the head
			addFirst(e);
		}
		else if(index >= size) { //Use existing function to add to the tail
			addLast(e);
		}
		else {
			//Else, loop till we reach the right node
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next; //Create a temporary Node, point it to the next item in the list.
			current.next = new Node<>(e); // Point the current node at a new Node filled with passed in data
			(current.next).next = temp; // Point the new Nodes next to the address stored in the temp data.
			size++;
		}
	}
	
	/**Remove the head node and 
	 * return the object that is contained in the removed node. */
	public E removeFirst() {
		if(size == 0) {
			return null; 
		}
		else {
			E temp = head.element;
			head = head.next;
			size--;
			if(head == null) {
				tail = null;
			}
			return temp;
		}
	}
	 
	
	/**Remove the last node and 
	 * return the object that is contained in the removed node.*/
	public E removeLast() {
		if(size == 0) {
			return null;
		}
		else if (size == 1) {
			E temp = head.element;
			head = tail = null;
			size = 0;
			return temp;
		}
		else {
			Node<E> current = head;
			
			for (int i = 0; i < size -2; i++) {
				current = current.next;
			}
			
			E temp = tail.element;
			tail = current; 
			tail.next = null;
			size--;
			return temp;
		}
	}
	
	
	
	@Override /** Remove the element at the specified position in this list
	Return the element that was removed from the list */
	public E remove(int index) {
		if(index <0 || index >= size) {
			return null;
		}
		else if(index == 0) {
			return removeFirst();
		}
		else if (index == size -1) {
			return removeLast();
		}
		else {
			Node<E> previous = head;
			
			for(int i = 1; i < index; i++) {
				previous = previous.next;
			}
			
			Node <E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}
	
	
	@Override /** Override toString() to return elements in the list*/
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if(current != null) {
				result.append(","); // Separate two elements with a comma
		}
		else {
			result.append("]"); // Insert the closing ] in the string
		}
	}
	
	return result.toString();
	}

	@Override /** Clear the list * -IMPORTANT - Did this destroy the items stored in memory? **/
	public void clear() {
		size = 0; 
		head = tail = null;
	}
	
	@Override /** Return true if this list contains the element e */
	public boolean contains(Object e) {
		//Left as an exercise 
		return true;
	}
	
	@Override /** Return the element at the specified index */
	public E get(int index) {
	    if (index < 0 || index >= size) {
	        return null;
	    }

	    Node<E> current = head;

	    for (int i = 0; i < index; i++) {
	        current = current.next;
	    }

	    return current.element;
	}
	
	
	@Override /** Return the index of the head matching element in 
	*this list. Return -1 if no match. */
	public int indexOf(Object e) {
		//Left as an exercise
		return 0;
	}
	
	@Override /** Return the index of the last matching element in 
	*this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		//Left as an exercise
		return 0;
	}
	
	
	@Override /** Replace the element at the specified position
	* in this list with the specified element */
	public E set(int index, E e) {
		//Left as an exercise
		return null;
	}
	
	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	//Iterator Class
	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; //Current index
		
		@Override
		public boolean hasNext() {
			return(current != null);
		}
		
		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}
		
		
		@Override
		public void remove() {
			//Left as an exercise
		}
	}
	
	
	private static class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E element) {
			this.element = element;
		}
	}
	
	@Override /** Return the number of elements in this list */
	public int size() {
		return size;
	}
}

