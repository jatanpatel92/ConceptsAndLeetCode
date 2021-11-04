package topological;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DirectedAcyclicGraph {
	List<Integer>[] adj;
	public DirectedAcyclicGraph(int n) {
		adj = new LinkedList[n];
		for(int i=0; i<n; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	public void addEdge(int i, int j) {
		adj[i].add(j);
	}
	public boolean detectCycle() {
		int n = adj.length;
		boolean[] visited = new boolean[n];
		boolean[] recursiveStack = new boolean[n];
		for(int i=0; i<n; i++) {
			if(detectCycleDFS(i, visited, recursiveStack))
				return true;
		}
		return false;
	}
	private boolean detectCycleDFS(int source, boolean[] visited, boolean[] recursiveStack) {
		System.out.println("Processing back edge detection for vertex : "+source);
		if(!visited[source]) {
			visited[source] = true;
			recursiveStack[source] = true;
			for(Integer neighbour : adj[source]) {
				if(!visited[neighbour]) {
					if(detectCycleDFS(neighbour, visited, recursiveStack)) {
						return true;
					}
				}
				else if(recursiveStack[neighbour]){ // node is in recursiveStack hence cycle detected
					return true;
				}
			}
		}
		recursiveStack[source] = false; // no cycle detected hence remove from recursiveStack
		return false;
	}
	public void printGraph() {
		System.out.println("Begin Printing Graph...");
		int v = adj.length;
		for(int i=0; i<v; i++) {
			System.out.println(i + "->" +adj[i]);
		}
		System.out.println("End Printing Graph.");
	}
	public List<Integer> printTopologicalOrder() {
		System.out.println("Begin Printing Topological order: ");
		List<Integer> topologicalOrder = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		int n = adj.length;
		boolean[] visited = new boolean[adj.length];
		for(int i=0; i<n; i++) {
			if(!visited[i])
				topologicalSort(i, stack, visited);
		}
		while(!stack.isEmpty()) {
			topologicalOrder.add(stack.pop());
		}
		return topologicalOrder;
	}
	private void topologicalSort(int u, Stack<Integer> stack, boolean[] visited) {
		visited[u] = true;
		for(int neighbour:adj[u]) {
			if(!visited[neighbour])
				topologicalSort(neighbour, stack, visited);
		}
		stack.add(u);
		System.out.println("Debug stack : "+stack);
	}
}