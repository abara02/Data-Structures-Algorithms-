/**
 * Top 15 Scores with Doubly Linked List
 * @author Alexis Baranauskas
 */
 

public class TopScores {

// Nested Doubly Linked Node class
private static class Node {
private int score;
private Node next;
private Node prev;
public Node(int score, Node next, Node prev) {
this.score = score;
this.next = next;
this.prev = prev;
}
public int getScore() {
return score;
}
public Node getNext() {
return next;
}
public void setNext(Node next) {
this.next = next;
}
public Node getPrev() {
return prev;
}
public void setPrev(Node prev) {
this.prev = prev;
}
}
private Node head = null; // head node of the list
private Node tail = null; // tail node of the list
private int size = 0; // current size of the list
private final int maxScores = 15; // maximum number of top scores
public TopScores() {

// construct null list
}
public int size() {
return size;
}
public boolean isEmpty() {
return size == 0;
}
public void addScore(int score) {
Node newNode = new Node(score, null, null);

// Case 1: List is empty, new node becomes head and tail
if (isEmpty()) {
head = tail = newNode;
} 

// Case 2: New score is greater than head score, insert at the front
else if (score > head.getScore()) {
newNode.setNext(head);
head.setPrev(newNode);
head = newNode;
} 

// Case 3: Traverse the list to find the correct position for the score
else {
Node current = head;
while (current.getNext() != null && current.getNext().getScore() >= score) {
current = current.getNext();
}
newNode.setNext(current.getNext());
newNode.setPrev(current);
current.setNext(newNode);
if (newNode.getNext() != null) {
newNode.getNext().setPrev(newNode);
} else {


tail = newNode;
}
}
size++;

// Remove the lowest score if the list size exceeds 15
if (size > maxScores) {
removeLowest();
}
}

// Remove the lowest score (tail node)
private void removeLowest() {
if (size <= 1) {
head = tail = null; 
} else {
Node prevTail = tail.getPrev();
prevTail.setNext(null);
tail = prevTail;
}
size--;
}

// Removes a node at an index 
public void remove(int i) {
if (i < 0 || i >= size) {
throw new IndexOutOfBoundsException("Index out of range");
}
Node current;

// Optimize traversal 
if (i < size / 2) {
current = head; // Start from the head if i is closer to the head
for (int index = 0; index < i; index++) {
current = current.getNext();
}
} else {
current = tail; // Start from the tail if i is closer to the tail
for (int index = size - 1; index > i; index--) {
current = current.getPrev();
}
}
// Remove the node
if (current == head) {
head = current.getNext();
if (head != null) {
head.setPrev(null);
}
} else if (current == tail) {
tail = current.getPrev();
if (tail != null) {
tail.setNext(null);
}
} else {
current.getPrev().setNext(current.getNext());
current.getNext().setPrev(current.getPrev());
}
size--;
}

// Display top 15 scores
public void printScores() {
Node current = head;
System.out.print("Top 15 Scores: ");
int count = 0;
while (current != null && count < maxScores) {
System.out.print(current.getScore());
current = current.getNext();
count++;
if (current != null && count < maxScores) {
System.out.print(" , ");
}
}
}

// Testing Method
public static void main(String[] args) {
TopScores topScores = new TopScores();
// Add the scores
int[] scores = {95, 85, 97, 100, 68, 72, 57, 67, 73, 81, 91, 99, 75, 92, 64, 88, 82, 94, 98, 99};
for (int score : scores) {



topScores.addScore(score);
}
topScores.printScores();
System.out.println(); 

// Remove the top score and print again 
topScores.remove(0); 
topScores.printScores();
}
}
