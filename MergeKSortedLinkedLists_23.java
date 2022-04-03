/*
23. Merge k Sorted Lists
Hard

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:

Input: lists = []
Output: []

Example 3:

Input: lists = [[]]
Output: []



Constraints:

    k == lists.length
    0 <= k <= 104
    0 <= lists[i].length <= 500
    -104 <= lists[i][j] <= 104
    lists[i] is sorted in ascending order.
    The sum of lists[i].length will not exceed 104.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 // With Priority Queue O(nlogk)
 class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        int k = lists.length;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new ListNodeComparator());
        for(ListNode list:lists){
            if(list!=null)
                priorityQueue.offer(list);
        }
        ListNode prev = new ListNode(-1);
        ListNode current = prev;
        while(!priorityQueue.isEmpty()){
            ListNode poll = priorityQueue.poll();
            current.next = poll;
            if(poll.next != null)
                priorityQueue.offer(poll.next);
            current = current.next;
        }
        return prev.next;
    }
    class ListNodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode l1, ListNode l2){
            return l1.val<=l2.val? -1:1;
        }
    }
}

// O(nk)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        ListNode mergedList = null;
        for(ListNode list:lists){
            mergedList = merge2Lists(list, mergedList);
        }
        return mergedList;
    }
    private ListNode merge2Lists(ListNode list1, ListNode list2){
        ListNode previous = new ListNode(-1);
        ListNode current = previous;
        while(list1!=null && list2 != null){
            if(list1.val<=list2.val){
                current.next = list1;
                list1 = list1.next;
            }
            else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = list1 == null ? list2 : list1;
        return previous.next;
    }
}
