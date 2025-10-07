/**
 * Implementing graph ADT using the adjacency list structure 
 * @author Alexis Baranauskas
 */
import java.util.*; 
class Graph<Element> { 
 private Map<Element, List<Element>> adjacencyList; 
 public Graph() { 
adjacencyList = new HashMap<>(); 
} 
// add a vertex 
 public void addVertex(Element vertex) { 
adjacencyList.put(vertex, new ArrayList<>()); 
} 
// add an edge between two vertexes 
 public void addEdge(Element source, Element destination) { 
if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) { 
adjacencyList.get(source).add(destination); // for a directed graph
adjacencyList.get(destination).add(source); // for a undirected graph 
} 
} 
// remove a vertex 
 public void removeVertex(Element vertex) { 
adjacencyList.remove(vertex); 
for (List<Element> neighbors : adjacencyList.values()) { 
neighbors.remove(vertex); 
} 
} 
// remove an edge 
 public void removeEdge(Element source, Element destination) { 
if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) { 
adjacencyList.get(source).remove(destination); // for a direct graph
adjacencyList.get(destination).remove(source); // for a undirected graph 
} 
} 
// Method to print the graph 
 public void printGraph() { 
for (Map.Entry<Element, List<Element>> entry : adjacencyList.entrySet()) { 
System.out.print(entry.getKey() + " -> "+ " -> "); 
for (Element neighbor : entry.getValue()) { 
System.out.print(neighbor + " "); 
} 
System.out.println(); 
} 
} 
} 
// Testing Method (Inputs) 
public class MainAdjacency { 
 public static void main(String[] args) { 
Graph<String> graph = new Graph<>(); 
// Set the vertexes 
graph.addVertex("u"); 
graph.addVertex("v"); 
graph.addVertex("w"); 
graph.addVertex("z"); 
// Set the edges 
graph.addEdge("v", "u"); 
graph.addEdge("v", "w"); 
graph.addEdge("u", "w"); 
graph.addEdge("w", "z"); 
// Display adjacency list graph 
System.out.println("Graph:"); 
graph.printGraph(); 
// Remove an edge and update the graph 
graph.removeEdge("u", "v"); 
System.out.println("After removing edge (u, v):"); 
graph.printGraph(); 
// Remove a vertex and update the graph 
graph.removeVertex("w"); 
System.out.println("After removing vertex w:"); 



graph.printGraph(); 
} 
}