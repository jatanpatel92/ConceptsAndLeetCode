/*
210. Course Schedule II
Medium

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]



Constraints:

    1 <= numCourses <= 2000
    0 <= prerequisites.length <= numCourses * (numCourses - 1)
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    ai != bi
    All the pairs [ai, bi] are distinct.


*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || prerequisites == null) return new int[0];
        Graph graph = new Graph(numCourses);
        int edges = prerequisites.length;
        for(int i=0; i<edges; i++){
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        return graph.getTopologicalOrder();
    }
    class Graph{
        List<Integer>[] adj;
        public Graph(int n){
            adj = new List[n];
            for(int i=0; i<n; i++){
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int source, int destination){
            adj[source].add(destination);
        }
        private boolean detectCycle(){
            if(adj == null || adj.length == 0) return false;
            int n = adj.length;
            boolean[] visited = new boolean[n];
            boolean[] recursiveStack = new boolean[n];
            for(int i=0; i<n; i++){
                if(detectCycleDFS(i, visited, recursiveStack))
                    return true;
            }
            return false;
        }
        private boolean detectCycleDFS(int source, boolean[] visited, boolean[] recursiveStack){
            if(!visited[source]){
                visited[source] = true;
                recursiveStack[source] = true;
                for(int neighbour: adj[source]){
                    if(detectCycleDFS(neighbour, visited, recursiveStack))
                        return true;
                }
                recursiveStack[source] = false;
            }
            else if(recursiveStack[source]){
                return true;
            }
            return false;
        }
        public int[] getTopologicalOrder(){
            if(detectCycle()) return new int[0];
            int n = adj.length;
            int[] result = new int[n];
            boolean[] visited = new boolean[n];
            Stack<Integer> recursiveStack = new Stack<>();
            for(int i=0; i<n; i++){
                if(!visited[i])
                    topologicalSort(i, visited, recursiveStack);
            }
            int count = 0;
            while(!recursiveStack.isEmpty()){
                result[count++] = recursiveStack.pop();
            }
            return result;
        }
        private void topologicalSort(int source, boolean[] visited, Stack<Integer> recursiveStack){
            visited[source] = true;
            for(int neighbour:adj[source]){
                if(!visited[neighbour])
                    topologicalSort(neighbour, visited, recursiveStack);
            }
            recursiveStack.push(source);
        }
    }
}
