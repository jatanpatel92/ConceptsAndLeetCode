/*
 * Second Round: Given directed graph containing unique numbers. We have given a list of nodes and we need to find missing nodes so that there is complete path exist.
Example:
Graph:
1->2,3
2->4
3->4
4->5
Input: 145
Output: 1 2 4 5, 1 3 4 5

I discuss 2 approach. First start from source do dfs and find all possible path to target node.
Here, (1 2 4 5, 1 3 4 5 ) then check which of these paths are contains subsequence (1 4 5).

I did one mistake here, and told time complexity of doing dfs is O(n^2).
Then, he was looking for another approach, so I gave another solution,
for each consecutive pair do dfs and find all possible path and keep adding that into answer.
1 -> 4: 1 2 4, 1 3 4
4 -> 5: 4,5

answer: 1245, 1345
 */
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
public class MissingPathInDAG {

	public static void main(String[] args) {
		Graph graph = new Graph(5);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 6);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 6);
		graph.printGraph();
		graph.findMissingPaths("145");
		graph.findMissingPaths("15");
		graph.findMissingPaths("16");
	}
	static class Graph{
		Map<Integer, List<Integer>> graph;
		public Graph(int n) {
			graph = new HashMap<>(n);
		}
		public void findMissingPaths(String mileStones) {
			List<String> paths = new LinkedList<>();
			int source = Character.getNumericValue(mileStones.charAt(0));
			Set<Integer> visited = new HashSet<>();
			findMissingPathsDFS(source, mileStones, 0, paths, new StringBuffer(), visited);
			System.out.println(paths);
		}
		private void findMissingPathsDFS(int source, String mileStones, int index, List<String> paths, StringBuffer path, Set<Integer> visited) {
			path.append(source);
			visited.add(source);
			int mileStone = Character.getNumericValue(mileStones.charAt(index));
			if(source == mileStone) index++;
			if(index >= mileStones.length()) {
				paths.add(path.toString());
			}
			else {
				for(int neighbor:graph.get(source)) {
					if(!visited.contains(neighbor)) {
						findMissingPathsDFS(neighbor, mileStones, index, paths, path, visited);
					}
				}
			}
			char letter = path.charAt(path.length()-1);
			path.deleteCharAt(path.length()-1);
			visited.remove(Character.getNumericValue(letter));
		}
		void addEdge(int u, int v) {
			if(!graph.containsKey(u)) {
				graph.put(u, new LinkedList<>());
			}
			if(!graph.containsKey(v)) {
				graph.put(v, new LinkedList<>());
			}
			graph.get(u).add(v);
		}
		void printGraph() {
			for(Integer node:graph.keySet()) {
				System.out.println(node + "->"+graph.get(node));
			}
		}
	}

}
