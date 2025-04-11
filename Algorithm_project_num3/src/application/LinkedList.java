//Abdallah Najjar _1220864
package application;



public class LinkedList <T extends Comparable<T>>{
	private Node<T> head; // The first node in the list
    private int size; // Size of the linked list

    // Constructor to initialize an empty list
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data); // Create a new node with the data
        if (head == null) {
            head = newNode; // If the list is empty, make the new node the head
        } else {
            Node<T> current = head;
            // Traverse to the last node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode); // Attach the new node to the last node
        }
        size++;
    }


	public boolean delete(T data) {// this method to delete a element
		if(head!=null) {
			Node <T> prev=null,curr=head;
			for(;curr!=null&&curr.getData().compareTo(data)<0;prev=curr,curr=curr.getNext()) ;
			if(curr.getData().compareTo(data)==0) {
				if(head==curr) {//if we need to delete the first
					head=curr.getNext();
				}
				else if(curr.getNext()==null) {//if we need to delete the last
					prev.setNext(null);  
				}
				else {//if we need to delete between two elements
					prev.setNext(curr.getNext());
				}
			}
		}

		return false;

	}

	public Node<T> find(T data){// this method to search for a element  
		if(head!=null) {
			Node <T>newNode=new Node<T>(data);
			Node <T> prev=null,curr=head;
			while(curr!=null) {
				if(curr.getData().compareTo(data)==0) {
					return curr;
				}
				else if(newNode==null) {
					return null;
				}
				else if(newNode.getData().compareTo(data)>0){
					return null;
				}
				prev=curr;
				curr=curr.getNext();
			}
		}

		return null;
	}

	public void print () {// return all elements in the Linked
		Node<T> curr=head;
		System.out.print("Head----->");
		while(curr!=null) {
			System.out.print(curr+"--->");
			curr=curr.getNext();
		}
		System.out.println("Null");
	}
	public void printRec() {
		printRec(head);
	}
	private void printRec(Node<T> d) {
		if(d!=null) {
			System.out.print(d);
			if(d.getNext()!=null&&d.getNext().getNext()!= null) {
				printRec(d.getNext().getNext().getNext());	
			}

		}

	}

    // Method to get the size of the list
    public int size() {
        return size;
    }




    // Method to get the element at a specific index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }
}
