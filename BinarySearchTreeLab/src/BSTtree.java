import java.util.*; // import java.util.Stack

public class BSTtree {
    BSTnode root;

    public BSTtree() {
        root = null;
    }
    //insert method
    //print all node => traverse => Tree traversal
    //delete node
    //search on the tree
    //balance

    public void insert(int newInfo) {
        BSTnode temp = root;
        if(root == null) {
            root = new BSTnode(newInfo, null, null);
            return;
        }
        while(true) {
            if(newInfo < temp.info && temp.left != null) {
                temp = temp.left;
            }
            else if(newInfo >= temp.info && temp.right != null) {
                temp = temp.right;
            }
            else if(newInfo < temp.info && temp.left == null) {
                temp.left = new BSTnode(newInfo, null, null);
                break;
            }
            else if(newInfo >= temp.info && temp.right == null) {
                temp.right = new BSTnode(newInfo, null, null);
                break;
            }
        }
    }

    public BSTnode search (int key) {
        BSTnode temp = root;
        if (root == null) {
            return null;
        }
        while (true) {
            if (key == temp.info) {
                return temp;
            }
            else if (key < temp.info && temp.left != null) {
                temp = temp.left;
            }
            else if (key > temp.info && temp.right != null) {
                temp = temp.right;
            }
            else { //not found
                return null;
            }
        }
    }

    public void preorderTraversalIterative() {
        BSTnode temp = root;

        if (temp != null){
            Stack st = new Stack();
            st.push(temp);

            while(!st.isEmpty()){
                temp = (BSTnode) st.pop();
                temp.visit();

                if (temp.right!=null) {
                    st.push(temp.right);
                }
                if (temp.left != null) {
                    st.push(temp.left);
                }
            }
        }
    }

    public void preorderTraversalRecursion(BSTnode node) {
        if (node != null) {
            node.visit();
            preorderTraversalRecursion(node.left);
            preorderTraversalRecursion(node.right);
        }
    }

    public void inorderTraversalRecursion(BSTnode node) {
        if (node != null) {
//            node.visit();
            inorderTraversalRecursion(node.left);
            node.visit();
            inorderTraversalRecursion(node.right);
        }
    }

    public void postorderTraversalRecursion(BSTnode node) {
        if (node != null) {
//            node.visit();
            postorderTraversalRecursion(node.left);
            postorderTraversalRecursion(node.right);
            node.visit();
        }
    }

    public void deleteByMerging(int value) {
        BSTnode p = root, prev = null, node, temp;
        //Search for the deleted node
        while (p != null && p.info != value) {
            prev = p;

            if (value < p.info) {
                p = p.left;
            }
            else {
                p = p.right;
            }
        }

        node = p;
        if (p != null && p.info == value) {
            if (p.right == null) { //Deleted node has no right child
                node = node.left;
            }
            else if (p.left == null) { //Deleted node has no left child
                node = node.right;
            }
            else { //Deleted node has both left and right children
                temp = p.left;
                //Search for the right most node of the left subtree of the deleted node
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = p.right;
                node = p.left;
            }
            if (p == root) { //Deleted node is the root
                root = node;
            }
            else if (p == prev.left) { //Deleted node is on the left of the parent
                prev.left = node;
            }
            else { //Deleted node is on the right of the parent
                prev.right = node;
            }
        }
        else if (root != null) {
            System.out.println("No " + value + " to delete from the tree.");
        }
        else {
            System.out.println("Tree is empty. Nothing to delete.");
        }
    }
}
