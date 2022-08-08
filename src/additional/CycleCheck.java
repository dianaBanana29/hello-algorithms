package additional;

import telran.util.LinkedList;

//import java.util.LinkedList;

public class CycleCheck{
	
	public static int isCircle(Node head) {
  LinkedList<Node> list = new LinkedList<Node>();
  Node current = head;
  int res = -1;
  while(current != null) {
	  Node temp = current;
	  current = current.next;
	  if(indexOf(temp, head) > indexOf(current, head)) {
		  res =  indexOf(temp, head); 
		  break;
	  }
  }
  return res;
	}
	
	
	private static int indexOf(Node temp, Node head) {
		int res = -1;
		int ind = 0;
		for(Node current = head; current != null; current = current.next, ind++) {
			if (current.equals(temp)) {
				res = ind;
				break;
			}
		}
		return res;  
	}
	public static void main(String[] args) {
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n3;
		System.out.println(isCircle(n0));

	}

}
