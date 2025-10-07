import java.util.*;
// Interface for nodes
interface Position<E> {
E getElement(); // current node
Position<E> getLeft(); // left child
Position<E> getRight(); // right child
Position<E> getParent(); // parent node
}
// Binary expression tree
class ExpressionTree<E> {
private static class Node<E> implements Position<E> {
E element; // current node
Position<E> left, right, parent;
Node(E element) {
this.element = element;
}
public E getElement() { return element; }
public Position<E> getLeft() { return left; }
public Position<E> getRight() { return right; }
public Position<E> getParent() { return parent; }
}
private Node<E> root; // root of binary tree
public ExpressionTree(String expr) {
root = constructTree(expr);
}
private Node<E> constructTree(String expr) {
Stack<Node<E>> stack = new Stack<>(); // node stack
Stack<Character> ops = new Stack<>(); // operator stack
for (int i = 0; i < expr.length(); i++) {
char c = expr.charAt(i);
if (c == ' ') continue;
if (c == '(') {
ops.push(c);
} else if (c == ')') {
while (ops.peek() != '(') processOperator(stack, ops.pop());
ops.pop(); // Remove '('
} else if (Character.isLetterOrDigit(c)) {
StringBuilder sb = new StringBuilder().append(c);
while (i + 1 < expr.length() && Character.isLetterOrDigit(expr.charAt(i + 1))) {
sb.append(expr.charAt(++i));
}
stack.push(new Node<>((E) sb.toString()));
} else if ("+-*/".indexOf(c) >= 0) {
while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
processOperator(stack, ops.pop());
}
ops.push(c);
}
}
while (!ops.isEmpty()) processOperator(stack, ops.pop());
return stack.pop();
}
private void processOperator(Stack<Node<E>> stack, char op) {
Node<E> right = stack.pop(), left = stack.pop();
Node<E> opNode = new Node<>((E) String.valueOf(op));
opNode.left = left; opNode.right = right;
left.parent = opNode; right.parent = opNode;
stack.push(opNode);
}
// Order of operations 
private int precedence(char op) {
return switch (op) {
case '+', '-' -> 1;
case '*', '/' -> 2;
default -> 0;
};
}
public Position<E> root() { return root; }
public int evaluate(Position<E> node, Map<String, Integer> vars) {
String val = (String) node.getElement();
if (Character.isDigit(val.charAt(0))) return Integer.parseInt(val);
if (Character.isLetter(val.charAt(0))) return vars.getOrDefault(val, 0);
int leftVal = evaluate(node.getLeft(), vars);



int rightVal = evaluate(node.getRight(), vars);
return switch (val.charAt(0)) {
case '+' -> leftVal + rightVal;
case '-' -> leftVal - rightVal;
case '*' -> leftVal * rightVal;
case '/' -> leftVal / rightVal;
default -> throw new UnsupportedOperationException("Unknown operator: " + val);
};
}
public void printTree(Position<E> node) {
if (node != null) {
printTree(node.getLeft());
System.out.print(node.getElement() + " ");
printTree(node.getRight());
}
}
}
// Testing method
public class ExpressionTreeTest
{
public static void main(String[] args) {
String expression = "((3 * 3) + (12 - X1))"; // input expression
ExpressionTree<String> tree = new ExpressionTree<>(expression);
System.out.println("Expression tree: ");
tree.printTree(tree.root());
System.out.println();
Map<String, Integer> variables = new HashMap<>();
variables.put("X1", 5); // Assign 5 to X1
System.out.println("Value with X1 as 5: " + tree.evaluate(tree.root(), variables));
variables.put("X1", 0); // Change the value of X1
System.out.println("Value with X1 as 0: " + tree.evaluate(tree.root(), variables));
}
}
