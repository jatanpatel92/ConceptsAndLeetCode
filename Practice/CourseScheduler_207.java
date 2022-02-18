class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // no vertex or edge
        if(numCourses == 0 || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        int row = prerequisites.length;
        int col = prerequisites[0].length;
        Graph graph = new Graph(numCourses);
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
            }
        }
        return !graph.detectCycle();
    }
}

class Graph{
    List[] adjList;
    // n = num of nodes
    public Graph(int n){
        adjList = new LinkedList[n];
        for(int i=0; i<n; i++){
            adjList[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int a, int b){
        adjList[a].add(b);
    }

    public boolean detectCycle(){
        int n = adjList.length;
        boolean[] visited = new boolean[n];
        boolean[] recursiveStack = new boolean[n];
        for(int i=0; i<n; i++){
            if(detectCycleDFS(i, visited, recursiveStack)){
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDFS(int node, boolean[] visited, boolean[] recursiveStack){
        visited[node] = true;
        recursiveStack[node] = true;
        List<Integer> neighbors = adjList[node];
        for(int neighbor : neighbors){
            if(!visited[neighbor]){
                if(detectCycleDFS(neighbor, visited, recursiveStack)){
                    return true;
                }
            }
            else if(recursiveStack[neighbor]){
                return true;
            }
        }
        recursiveStack[node] = false;
        return false;
    }
}
