import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */

/**
 * @author jatan
 *
 */
public class RemoveBinaryTreeLeaves {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = initializeTree();
		System.out.println(root);
		removeLeaves(root, null);
		System.out.println(root);
		root = initializeTree();
		removeLeavesWithNewlyCreatedFirst(root, null);
		System.out.println(root);
		root = initializeTree();
		Map<Node, Node> parentMap = new HashMap<>();
		Deque<Node> list = new LinkedList<>();
		removeLeavesWithNewlyCreatedLast(root, null, parentMap, list);
		while(!list.isEmpty()) {
			Node node = list.poll();
			Node parent = parentMap.get(node);
			if(parent.left == node) {
				parent.left = null;
			}
			if(parent.right == node) {
				parent.right = null;
			}
		}
		System.out.println(root);
	}
	static Node initializeTree() {
		Node root = new Node(1);
		Node l1n1 = new Node(2);
		Node l1n2 = new Node(3);
		Node l2n1 = new Node(4);
		Node l2n2 = new Node(5);
		Node l2n3 = new Node(6);
		Node l2n4 = new Node(7);
		root.left = l1n1;
		root.right = l1n2;
		l1n1.left = l2n1;
		l1n1.right = l2n2;
		l1n2.left = l2n3;
		l1n2.right = l2n4;
		return root;
	}
	static void removeLeaves(Node root, Node parent) {
		if(root == null) return;
		if(root.left == null && root.right == null) {
			if(parent!=null && parent.left == root) parent.left = null;
			if(parent!=null && parent.right == root) parent.right = null;
			root = null;
			return;
		}
		removeLeaves(root.left, root);
		removeLeaves(root.right, root);
	}
	static void removeLeavesWithNewlyCreatedFirst(Node root, Node parent) {
		if(root == null) return;
		if(isLeafNode(root)) {
			if(parent!=null && parent.left == root) parent.left = null;
			if(parent!=null && parent.right == root) parent.right = null;
			root = null;
			return;
		}
		removeLeavesWithNewlyCreatedFirst(root.left, root);
		if(isLeafNode(root.left)) {
			root.left = null;
		}
		removeLeavesWithNewlyCreatedFirst(root.right, root);
		if(isLeafNode(root.right)) {
			root.right = null;
		}
	}
	static void removeLeavesWithNewlyCreatedLast(Node root, Node parent, Map<Node, Node> parentMap, Deque<Node> list) {

		if(root == null) return;
		if(isLeafNode(root)) {
			if(parent!=null && parent.left == root) parent.left = null;
			if(parent!=null && parent.right == root) parent.right = null;
			root = null;
			return;
		}
		parentMap.put(root, parent);
		removeLeavesWithNewlyCreatedLast(root.left, root, parentMap, list);
		if(isLeafNode(root.left)) {
			list.offer(root.left);
		}
		removeLeavesWithNewlyCreatedLast(root.right, root, parentMap, list);
		if(isLeafNode(root.right)) {
			list.offer(root.right);
		}
		//System.out.println(list);
	}
	static boolean isLeafNode(Node root) {
		if(root == null) return false;
		return root.left == null && root.right == null;
	}
	static class Node{
		int val;
		Node left;
		Node right;
		public Node(int v) {
			val = v;
			left = null;
			right = null;
		}
		@Override
		public String toString() {
			return "("+Integer.toString(val) + " Left: "+left+" Right: "+right +")";
		}
	}
}
