/*
207. Course Schedule
Medium

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.



Constraints:

    1 <= numCourses <= 105
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    All the pairs prerequisites[i] are unique.


*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses==0 || prerequisites == null || prerequisites.length==0) return true;
        Graph graph = new Graph(numCourses);
        for(int i=0; i<prerequisites.length; i++){
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        return !graph.detectCycle();
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
        public boolean detectCycle(){
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
        public boolean detectCycleDFS(int source, boolean[] visited, boolean[] recursiveStack){
            if(!visited[source]){
                visited[source] = true;
                recursiveStack[source] = true;
                for(int neighbour : adj[source]){
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
    }
}
