package topological;

public class DirectedAcyclicGraphTester {

	public static void main(String[] args) {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph(6);
		dag.addEdge(0, 2);
		dag.addEdge(0, 3);
		dag.addEdge(3, 1);
		dag.addEdge(4, 2);
		dag.addEdge(4, 1);
		dag.addEdge(5, 0);
		dag.addEdge(5, 2); // to make graph acyclic
		//dag.addEdge(2, 5); // to make graph cyclic
		dag.printGraph();
		boolean graphHasCycle = dag.detectCycle();
		String hasCycle = graphHasCycle?"Yes":"No";
		System.out.println("Does this graph has a cycle? - "+hasCycle);
		if(!graphHasCycle) {
			System.out.println("Topological Order: " +dag.printTopologicalOrder());
		}
	}
}
