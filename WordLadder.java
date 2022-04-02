class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Graph graph = new Graph();
        graph.addNode(beginWord);
        boolean listHasDestination = false;
        boolean listHasSource = false;
        for(String word:wordList){
            if(word.equals(endWord))
                listHasDestination = true;
            if(word.equals(beginWord))
                listHasSource = true;
            graph.addNode(word);
        }
        if(!listHasDestination) return 0;
        graph.generateGraph();
        return graph.bfs(beginWord, endWord);
    }
    class Graph{
        Map<String, LinkedList<String>> map;
        public Graph(){
            map = new HashMap<>();
        }
        public void addNode(String v){
            if(!map.containsKey(v))
                map.put(v, new LinkedList<>());
        }
        public void addEdge(String u, String v){
            if(!hasEdge(u, v)){
                map.get(u).add(v);
                map.get(v).add(u);
            }
        }
        public boolean hasEdge(String u, String v){
            return map.containsKey(u) && map.get(u).contains(v) && map.containsKey(v) && map.get(v).contains(u);
        }
        public int bfs(String source, String destination){
            int distance = 0;
            Map<String, String> parent = new HashMap<>();
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            parent.put(source, "");
            queue.offer(source);
            visited.add(source);
            String current = source;
            boolean found = false;
            while(!queue.isEmpty()){
                current = queue.poll();
                if(current.equals(destination)){
                    found = true;
                    break;
                }
                for(String neighbor:map.get(current)){
                    if(!visited.contains(neighbor)){
                        parent.put(neighbor, current);
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            if(found){
                while(parent.get(current)!=""){
                    current = parent.get(current);
                    distance++;
                }
            }
            return found?distance+1:0;
        }
        public void generateGraph(){
            if(map.isEmpty()) return;
            for(String node1: map.keySet()){
                for(String node2 : map.keySet()){
                    if(!node1.equals(node2)){
                        if(oneStepAway(node1, node2)){
                            addEdge(node1, node2);
                        }
                    }
                }
            }
        }
        private boolean oneStepAway(String str1, String str2){
            int step = 0;
            int index = 0;
            int n = str1.length();
            while(index<n){
                if(str1.charAt(index)!=str2.charAt(index))
                    step++;
                index++;
            }
            return step == 1 ? true:false;
        }
        @Override
        public String toString(){
            return map.toString();
        }
    }
}
