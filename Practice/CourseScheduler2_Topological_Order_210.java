class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0) return new int[numCourses];
        int row = prerequisites.length;
        int col = row!=0?prerequisites[0].length:0;
        Graph graph = new Graph(numCourses);
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
            }
        }
        if(!graph.detectCycle()){
            return graph.topologicalOrder();
        }
        return new int[0];
    }
}
class Graph{
    List<Integer>[] adjList;
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
        for(int neighbor:adjList[node]){
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
    public int[] topologicalOrder(){
        int n = adjList.length;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i])
                topologicalOrderDFS(i, visited, stack);
        }
        System.out.println(stack);
        int[] result = new int[n];
        int count = 0;
        while(!stack.isEmpty()){
            result[count++] = stack.pop();
        }
        return result;
    }
    private void topologicalOrderDFS(int node, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;
        for(int neighbor:adjList[node]){
            if(!visited[neighbor])
                topologicalOrderDFS(neighbor, visited, stack);
        }
        stack.push(node);
    }
}
