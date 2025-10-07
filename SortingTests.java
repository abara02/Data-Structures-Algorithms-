/**
 * Benchmark Testing Different Sorting methods 
 * @author Alexis Baranauskas
 */
import java.util.Arrays;
import java.util.Random;
public class SortingTests {
public static void main(String[] args) {
int[] randomArray = generateRandomArray(10000);
int[] almostSortedArray = generateAlmostSortedArray(10000);
System.out.println("Random Array Benchmarks:");
benchmark("Insertion Sort", randomArray, () -> insertionSort(randomArray));
benchmark("Selection Sort", randomArray, () -> selectionSort(randomArray));
benchmark("Merge Sort", randomArray, () -> mergeSort(randomArray));
benchmark("Quick Sort", randomArray, () -> quickSort(randomArray));
System.out.println("\nAlmost Sorted Array Benchmarks:");
benchmark("Insertion Sort", almostSortedArray, () -> insertionSort(almostSortedArray));
benchmark("Selection Sort", almostSortedArray, () -> selectionSort(almostSortedArray));
benchmark("Merge Sort", almostSortedArray, () -> mergeSort(almostSortedArray));
benchmark("Quick Sort", almostSortedArray, () -> quickSort(almostSortedArray));
}
private static int[] generateRandomArray(int size) {
int[] array = new int[size];
Random random = new Random();
for (int i = 0; i < size; i++) {
array[i] = random.nextInt(10000);
}
return array;
}
private static int[] generateAlmostSortedArray(int size) {
int[] array = new int[size];
Random random = new Random();
for (int i = 0; i < size; i++) {
array[i] = i + random.nextInt(3) - 1; 
}
return array;
}
private static void benchmark(String sortName, int[] array, Runnable sortMethod) {
int[] arrayCopy = Arrays.copyOf(array, array.length); 
long startTime = System.currentTimeMillis();
sortMethod.run();
long endTime = System.currentTimeMillis();
System.out.println(sortName + " - Time taken: " + (endTime - startTime) + " ms");
}
// Insertion Sort
public static void insertionSort(int[] array) {
for (int i = 1; i < array.length; i++) {
int key = array[i]; 
int j = i - 1; 
while (j >= 0 && array[j] > key) {
array[j + 1] = array[j];
j--;
}
array[j + 1] = key; 
}
}
// Selection Sort
public static void selectionSort(int[] array) {
for (int i = 0; i < array.length - 1; i++) {
int minIndex = i; 
for (int j = i + 1; j < array.length; j++) {
if (array[j] < array[minIndex]) {
minIndex = j; 
}
}
int temp = array[minIndex];
array[minIndex] = array[i];
array[i] = temp; 
}
}
// Merge Sort


public static void mergeSort(int[] array) {
if (array.length < 2) return; // Base case
int mid = array.length / 2; 
int[] left = Arrays.copyOfRange(array, 0, mid); 
int[] right = Arrays.copyOfRange(array, mid, array.length); 
mergeSort(left); // Sort the left half
mergeSort(right); // Sort the right half
merge(array, left, right); // Merge the sorted halves back into the original array
}
private static void merge(int[] array, int[] left, int[] right) {
int i = 0, j = 0, k = 0; 
while (i < left.length && j < right.length) {
array[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
}
// Copy any remaining elements from the left half
while (i < left.length) array[k++] = left[i++];
// Copy any remaining elements from the right half
while (j < right.length) array[k++] = right[j++];
}
// Quick Sort
public static void quickSort(int[] array) {
quickSort(array, 0, array.length - 1);
}
private static void quickSort(int[] array, int low, int high) {
if (low < high) { // Base case:
int pi = partition(array, low, high); // Partition the array 
quickSort(array, low, pi - 1); // Sort the left partition
quickSort(array, pi + 1, high); // Sort the right partition
}
}
private static int partition(int[] array, int low, int high) {
int pivot = array[high]; // Last element as pivot
int i = low - 1; // Index of smaller element
for (int j = low; j < high; j++) {
if (array[j] < pivot) { 
i++;
int temp = array[i];
array[i] = array[j];
array[j] = temp;
}
}
int temp = array[i + 1];
array[i + 1] = array[high];
array[high] = temp;
return i + 1; 
}
}