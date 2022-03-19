import java.util.Scanner;

/**
 * Test Graph, BFS, DFS
 */

/**
 * @author J-10
 *
 */
public class GraphTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("==========Graph==========");
		Scanner sc = new Scanner(System.in);
		System.out.println("Run sample graph? 0 - No, 1- Yes");
		boolean customGraph = sc.nextInt() == 0 ? true : false;
		if(customGraph) {
			System.out.println("Enter number of vertices and edges: ");
			int v = sc.nextInt();
			int e = sc.nextInt();
			Graph graph = new Graph(v);
			System.out.println("Graph initiated with "+v+" vertices.");
			System.out.println("Enter edges "+e+" with source and destination.");
			for(int i=0; i<e; i++) {
				int source = sc.nextInt();
				int destination = sc.nextInt();
				graph.addEdge(source, destination);
			}
			graph.printGraph();
		}
		sc.close();
		System.out.println("==========Sample Graph==========");
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.printGraph();
		int distance = graph.bfs(0, 4);
		System.out.println("Distance between 0 and 4 = "+distance);
		System.out.println("Path exists between 0 and 4 ? "+graph.dfs(0, 4));
		System.out.println("Path exists between 0 and 4 ? "+graph.dfsStack(0, 4));
		System.out.println("Detect Cycle: "+graph.detectCycle());
	}

}
