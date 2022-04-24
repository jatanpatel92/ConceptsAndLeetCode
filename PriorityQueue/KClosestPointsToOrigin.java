class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> queue = new PriorityQueue<>(new CustomPointCompare());
        for(int[] point : points){
            queue.offer(new Point(point[0], point[1]));
        }
        int[][] result = new int[k][2];
        int count = 0;
        while(count<k && !queue.isEmpty()){
            Point poll = queue.poll();
            result[count] = new int[]{poll.x, poll.y};
            count++;
        }
        return result;
    }
    class Point/* implements Comparable<Point>*/{
        int x;
        int y;
        public Point(int x1, int y1){
            x = x1;
            y = y1;
        }/*
        @Override
        public int compareTo(Point p){
            double disFromOrigin1 = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
            double disFromOrigin2 = Math.sqrt(Math.pow(p.x, 2)+Math.pow(p.y, 2));
            return disFromOrigin1<disFromOrigin2?-1:1;
        }*/
    }
    class CustomPointCompare implements Comparator<Point>{
        @Override
        public int compare(Point p1, Point p2){
            double disFromOrigin1 = Math.sqrt(Math.pow(p1.x, 2)+Math.pow(p1.y, 2));
            double disFromOrigin2 = Math.sqrt(Math.pow(p2.x, 2)+Math.pow(p2.y, 2));
            return disFromOrigin1<disFromOrigin2?-1:1;
        }
    }

}
