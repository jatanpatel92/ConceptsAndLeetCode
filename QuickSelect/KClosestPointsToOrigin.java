class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int left = 0;
        int right = points.length-1;
        int pivotIndex = 0;
        quickSort(points, left, right, k);
        return Arrays.copyOf(points, k);
    }
    public void quickSort(int[][] points, int l, int h, int k){
        if(l<0 || h>points.length || l>h) return;
        int pivot = partition(points, l, h);
        if(pivot == k)
            return;
        else{
            if(k<=pivot)
                quickSort(points, l, pivot-1, k);
            else
                quickSort(points, pivot+1, h, k);
        }
    }
    public int partition(int[][] points, int l, int h){
        int[] pivotElement = points[l];
        double distance = getDistance(pivotElement);
        int i = l;
        int j = h;
        while(i<j){
            while(i<points.length && getDistance(points[i])<=distance) i++;
            while(j>=0 && getDistance(points[j])>distance) j--;
            if(i<j){
                swap(points, i, j);
            }
        }
        swap(points, l, j);
        return j;
    }
    public double getDistance(int[] point){
        return Math.sqrt(Math.pow(point[0], 2)+Math.pow(point[1], 2));
    }
    public void swap(int[][] points, int i, int j){
        if(i!=j){
            int[] temp = new int[2];
            temp = points[i];
            points[i] = points[j];
            points[j] = temp;
        }
    }
}
