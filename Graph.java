/**
 * Implementing graph ADT using the adjacency matrix structure 
 * @author Alexis Baranauskas
 */
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
public class Graph<V> {
private Map<V, Integer> vertexIndices;
private List<V> vertexes;
// 2D array for adjacency matrix
private String[][] adjacencyMatrix;
// Number of vertexes in the graph
private int numVertexes;
// Create graph
public Graph() {
vertexIndices = new HashMap<>();
vertexes = new ArrayList<>();
adjacencyMatrix = new String[0][0]; 
numVertexes = 0;
}
// add a vertex
public void addVertex(V vertex) {
if (!vertexIndices.containsKey(vertex)) {
vertexIndices.put(vertex, numVertexes);
vertexes.add(vertex);
// add on new matrix 
String[][] newAdjacencyMatrix = new String[numVertexes + 1][numVertexes + 1];
for (int i = 0; i < numVertexes; i++) {
System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, numVertexes);
}
adjacencyMatrix = newAdjacencyMatrix;
numVertexes++;
}
}
// add an edge between two vertexes
public void addEdge(V vertex1, V vertex2, String edgeLabel) {
if (!vertexIndices.containsKey(vertex1) || !vertexIndices.containsKey(vertex2)) {
throw new IllegalArgumentException("error.");
}
// indices of the vertexes
int i = vertexIndices.get(vertex1);
int j = vertexIndices.get(vertex2);
// edge goes both ways for undirected graph
adjacencyMatrix[i][j] = edgeLabel;
adjacencyMatrix[j][i] = edgeLabel;
}
// remove an edge between two vertexes
public void removeEdge(V vertex1, V vertex2) {
if (!vertexIndices.containsKey(vertex1) || !vertexIndices.containsKey(vertex2)) {
return;
}
int i = vertexIndices.get(vertex1);
int j = vertexIndices.get(vertex2);
adjacencyMatrix[i][j] = null;
adjacencyMatrix[j][i] = null;
}
// remove a vertex 
public void removeVertex(V vertex) {
// index of the vertex to removed
int vertexIndex = vertexIndices.remove(vertex);
vertexes.remove(vertexIndex);
numVertexes--;
// update adjacency matrix 
String[][] newAdjacencyMatrix = new String[numVertexes][numVertexes];
for (int i = 0; i < numVertexes; i++) {
for (int j = 0; j < numVertexes; j++) {
if (i < vertexIndex && j < vertexIndex) {
newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
} else if (i < vertexIndex) {


newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
} else if (j < vertexIndex) {
newAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
} else {
newAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
}
}
}
adjacencyMatrix = newAdjacencyMatrix;
// update index
for (int i = 0; i < vertexes.size(); i++) {
vertexIndices.put(vertexes.get(i), i);
}
}
// Display graph
public void printGraph() {
System.out.print("\t"); 
for (V vertex : vertexes) {
System.out.print(vertex + "\t");
}
System.out.println();
for (int i = 0; i < numVertexes; i++) {
// Display vertex labels
System.out.print(vertexes.get(i) + "\t");
// Display where edges go
for (int j = 0; j < numVertexes; j++) {
if (adjacencyMatrix[i][j] != null) {
System.out.print(adjacencyMatrix[i][j] + "\t");
} else {
System.out.print("\t"); // null
}
}
System.out.println(); 
}
}
// Test Method (inputs)
public static void main(String[] args) {
Graph<String> graph = new Graph<>();
// Set vertexes
graph.addVertex("U");
graph.addVertex("V");
graph.addVertex("W");
graph.addVertex("Z");
// Set edges
graph.addEdge("U", "V", "e1");
graph.addEdge("V", "W", "e2");
graph.addEdge("U", "W", "e3");
graph.addEdge("W", "Z", "e4");
// Display graph
System.out.println("Graph:");
graph.printGraph();
}
}