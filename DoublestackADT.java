/**
 * Two Color Stack ADT
 * @author Alexis Baranauskas
 */
 



public class DoublestackADT<T> {
    private T[] stackArray; // Array for both stacks
    private int redTop; // Red stack top index
    private int blueTop; // Blue stack top index
    private int capacity; // Array capacity

    // Constructor
    public DoublestackADT(int cap) {
        capacity = cap;
        stackArray = (T[]) new Object[capacity]; 
        redTop = -1; // Red stack empty
        blueTop = capacity; // Blue stack empty
    }

    public void redPush(T value) {
        if (redTop + 1 == blueTop) {
            throw new IllegalStateException("Red stack full");
        }
        stackArray[++redTop] = value; // Adds value to red stack
    }

    public void bluePush(T value) {
        if (blueTop - 1 == redTop) {
            throw new IllegalStateException("Blue stack full");
        }
        stackArray[--blueTop] = value; // Adds value to blue stack
    }

    public T redPop() {
        if (redTop == -1) {
            throw new IllegalStateException("Red stack empty");
        }
        return stackArray[redTop--]; // Remove and return top of red stack
    }

    public T bluePop() {
        if (blueTop == capacity) {
            throw new IllegalStateException("Blue stack empty");
        }
        return stackArray[blueTop++]; // Remove and return top of blue stack
    }

    // INPUT TEST METHOD
    public static void main(String[] args) {
        DoublestackADT<Integer> stack = new DoublestackADT<>(10);

        // Red stack input
        stack.redPush(4);
        stack.redPush(8);
        System.out.println("Red pop: " + stack.redPop()); // Should print 8
        System.out.println("Red pop: " + stack.redPop()); // Should print 4

        // exception test
        try {
            System.out.println("Red pop: " + stack.redPop());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Blue stack input
        stack.bluePush(9);
        stack.bluePush(12);
        System.out.println("Blue pop: " + stack.bluePop()); // Should print 12
        System.out.println("Blue pop: " + stack.bluePop()); // Should print 9
    }
}

