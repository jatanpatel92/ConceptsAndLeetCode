import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	LinkedList<Integer>[] adj;
	public Graph(int v) {
		adj = new LinkedList[v];
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	public void addEdge(int source, int destination) {
		adj[source].add(destination);
		adj[destination].add(source);
	}
	public void printGraph() {
		System.out.println("Begin Printing Graph...");
		int v = adj.length;
		for(int i=0; i<v; i++) {
			System.out.println(i + "->" +adj[i]);
		}
		System.out.println("End Printing Graph.");
	}
	public int bfs(int source, int destination) {
		int distance = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[adj.length];
		int[] parent = new int[adj.length];
		queue.add(source);
		parent[source] = -1;
		visited[source] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			if(current == destination) break;
			for(int neighbour : adj[current]) {
				if(!visited[neighbour]) {
					queue.add(neighbour);
					visited[neighbour] = true;
					parent[neighbour] = current;
				}
			}
		}
		int path = destination;
		Stack<Integer> printPathStk = new Stack<>();
		while(parent[path]!=-1) {
			printPathStk.push(path);
			path = parent[path];
			distance++;
		}
		printPathStk.push(path);
		System.out.print("Path from "+source+" to "+destination+" : ");
		while(!printPathStk.isEmpty()) {
			int pop = printPathStk.pop();
			System.out.print(pop+" -> ");
		}
		System.out.println();
		return distance;
	}

	public boolean dfs(int source, int destination) {
		boolean[] visited = new boolean[adj.length];
		visited[source] = true;
		return dfsRecursive(source, destination, visited);
	}
	private boolean dfsRecursive(int source, int destination, boolean[] visited) {
		if(source == destination) return true;
		for(int neighbour : adj[source]) {
			if(!visited[neighbour]) {
				visited[neighbour] = true;
				boolean pathExists = dfsRecursive(neighbour, destination, visited);
				if(pathExists) return true;
			}
		}
		return false;
	}

	public boolean dfsStack(int source, int destination) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[adj.length];
		stack.push(source);
		visited[source] = true;
		while(!stack.isEmpty()) {
			int pop = stack.pop();
			if(pop == destination) return true;
			for(int neighbour : adj[pop]) {
				if(!visited[neighbour]) {
					visited[neighbour] = true;
					stack.push(neighbour);
				}
			}
		}
		return false;
	}

	public boolean detectCycle() {
		int n = adj.length;
		for(int i=0; i<n; i++) {
			boolean[] visited = new boolean[n];
			if(detectCycleDFS(i, visited, -1)) {
				return true;
			}
		}
		return false;
	}
	private boolean detectCycleDFS(int source, boolean[] visited, int parent) {
		visited[source] = true;
		for(int neighbor:adj[source]) {
			if(!visited[neighbor]) {
				if(detectCycleDFS(neighbor, visited, source))
					return true;
			}
			else if(parent != neighbor) {
				return true;
			}
		}
		return false;
	}
}
