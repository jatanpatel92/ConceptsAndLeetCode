import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DijkstraAlgorithmForSingleSourceShortestPath {

	public static void main(String[] args) {
		Graph graph = new Graph(6);
		graph.addEdge(0, 1, 50);
		graph.addEdge(0, 3, 10);
		graph.addEdge(0, 2, 45);
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 3, 15);
		graph.addEdge(2, 4, 30);
		graph.addEdge(3, 0, 10);
		graph.addEdge(3, 4, 15);
		graph.addEdge(4, 1, 20);
		graph.addEdge(4, 2, 35);
		graph.addEdge(5, 4, 3);
		System.out.println(graph.getShortestPath(0, 2));
		System.out.println(graph.getShortestPath(0, 4));
		System.out.println(graph.getShortestPath(0, 5));
		System.out.println(graph.getShortestPath(0, 3));
		System.out.println(graph.getShortestPath(0, 1));
		System.out.println(graph.getShortestPath(5, 0));
	}
	
	public static class Graph{
		Map<Integer, Map<Integer, Integer>> graph;
		public Graph(int nodes) {
			graph = new HashMap<>();
			for(int i=0; i<nodes; i++) {
				graph.put(i, new HashMap<>());
			}
		}
		public void addEdge(int u, int v, int weight) {
			graph.get(u).put(v, weight);
		}
		public int getShortestPath(int source, int destination) {
			Set<Integer> visited = new HashSet<>();
			Queue<Integer> queue = new LinkedList<>();
			int[] distance = new int[graph.size()];
			distance[source] = 0;
			for(int i=0; i<graph.size(); i++) {
				if(i!=source) {
					distance[i] = Integer.MAX_VALUE;
				}
			}
			queue.offer(source);
			while(!queue.isEmpty()) {
				int current = getLowestDistNode(queue, distance);
				queue.remove((Integer) current);
				for(Integer neighbor: graph.get(current).keySet()) {
					if(!visited.contains(neighbor)) {
						calculateMinDistance(current, neighbor, graph.get(current).get(neighbor), distance);
						queue.offer(neighbor);
					}
				}
				visited.add(current);
			}
			return distance[destination];
		}
		private void calculateMinDistance(int current, Integer neighbor, Integer weight, int[] distance) {
			if(distance[current] + weight < distance[neighbor]) {
				distance[neighbor] = distance[current] + weight;
			}
		}
		public int getLowestDistNode(Queue<Integer> queue, int[] distance) {
			int min = Integer.MAX_VALUE;
			int node = 0;
			for(int element : queue) {
				if(min>distance[element]) {
					min = distance[element];
					node = element;
				}
			}
			return node;
		}
	}
}
