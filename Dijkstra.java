//package prg2;
import java.util.*;

class Main{
  
  
  static class Graph 
  { 
    int n;
    List<List<Node> > adjList;
    
    // Constructor to initialize arrays to track and store distance, parents, 
    // explored nodes, and create a priority queue
    public Graph(int n) {
      this.n = n;
      adjList = new ArrayList<List<Node>>();
      for(int i = 0; i < n ; i++){ 
        List<Node> node = new ArrayList<Node>();
        adjList.add(node); 
        } 
    }
  }
  
  //Initializes a node class that allows us to compare node weights
  //to implement the priority queue of nodes
  static class Node
  {
    public int e; 
    public int weight; 

    public Node(int e, int weight) 
       { 
           this.e = e; 
           this.weight = weight;      
       }
   }
  static class NodeComparator implements Comparator<Node>{ 
    public int compare(Node n1, Node n2) { 
       if (n1.weight < n2.weight) 
           return -1; 
       else if (n1.weight > n2.weight)  
           return 1; 
       return 0;
     
  }
  }
    
    // Function to implement Dijkstra's Algorithm
    static void runDijkstra(Graph g, int source, boolean[] unexplored, int[] distance,int[] parent) 
    {
      for (int i=0; i<g.n;i++) {
        distance[i] = Integer.MAX_VALUE;
        parent[i] = -1;
//        inPrint[i] = false;
        unexplored[i] = false;
      }
      PriorityQueue<Node> explored = new PriorityQueue<>(new NodeComparator());
      distance[source] = 0;
      explored.add(new Node(source, distance[source]));
      
      while (!explored.isEmpty()) 
      { 
        Node node = explored.remove();
        if(!unexplored[node.e]) {
           unexplored[node.e] = true;
           List<Node> neighbors = g.adjList.get(node.e);
           for (Node neighbor: neighbors)
           {
             int l = distance[node.e] + neighbor.weight;  
             if (l < distance[neighbor.e]) 
               {
                 distance[neighbor.e] = l;
                 parent[neighbor.e] = node.e;
               }
               explored.add(new Node(neighbor.e, distance[neighbor.e]));
            }
          }
         node = null;
          }
        }
   
     
    public static void main(String[] args) {
  
        Scanner Input = new Scanner(System.in);
        // Store the number of vertices, edges and the source node
        int n = Input.nextInt();
        int m = Input.nextInt();
        int source = Input.nextInt();
  
        
        // Create a Graph object to store the rest of the inputs
        Graph g = new Graph(n);
        for (int i=0; i<m;i++) 
        {
          int from = Input.nextInt();
          int to = Input.nextInt();
          int weight = Input.nextInt();
          g.adjList.get(from).add(new Node(to, weight));
        }
        Input.close();
        
        int[] distance = new int[n];
        int[] parent = new int[n];
//        boolean[] inPrint = new boolean[n];
        boolean[] unexplored = new boolean[n];
        
        
        // Run the Dijkstra Algorithm on the obtained graph
        runDijkstra(g, source, unexplored, distance, parent);
        
        for(int i=0; i<n; i++){
          if (parent[i] !=-1) {
          System.out.printf("%d %d %d\n", i, distance[i], parent[i]);
          }
        }
        
    }
}


