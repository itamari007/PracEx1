import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.Dictionary;

/**
 * public class AVLNode
 * <p>
 * This class represents an AVLTree with integer keys and boolean values.
 * <p>
 * IMPORTANT: do not change the signatures of any function (i.e. access modifiers, return type, function name and
 * arguments. Changing these would break the automatic tester, and would result in worse grade.
 * <p>
 * However, you are allowed (and required) to implement the given functions, and can add functions of your own
 * according to your needs.
 */

public class Tree {
    private AVLNode root;
    private int treeSize;
    /**
     * This constructor creates an empty AVLTree.
     */
    public Tree(){
    }

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return this.getRoot() == null;
    }

    /**
     * public boolean search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public Boolean search(int k) {
        return recursiveSearch(k,root);
    }

    //Recursively search for life meaning(hint: it is divisible by both 14 and 3)
    private Boolean recursiveSearch(int k,AVLNode node){
        if(node==null){
            return  null;
        }
        if(node.key == k){
            return node.getValue();
        }
        if(k>node.key){
            return recursiveSearch(k,node.right);
        }
        return recursiveSearch(k,node.left);
    }


    /**
     * public int insert(int k, boolean i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
     * returns the number of nodes which require rebalancing operations (i.e. promotions or rotations).
     * This always includes the newly-created node.
     * returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, boolean i) {
        if (search(k)!=null){
            return -1;
        }
        treeSize++;
        AVLNode newNode = new AVLNode(i,k);
        if(root==null){
            root = newNode;
            return 0;
        }
        return BSTInsert(newNode);
    }

    private void updateHeightForNodesInPath(AVLNode node){
        while(node!=null){
            calcHeight(node);
            node=node.getParent();
        }
    }


    /**
     * regular Binary Search Tree Insert function,
     * traverses the tree and appends node where it is proper to do so, and
     * returns the old height of the direct parent, before adding the new node.
     * @param node
     */
    private int BSTInsert(AVLNode node){
        AVLNode cursorNode = root;//Pointer that we shall use to traverse the tree
        while(cursorNode.isRealNode()){
            cursorNode = cursorNode.getKey()>node.getKey()? cursorNode.getLeft() : cursorNode.getRight();
        }
        //Now cursorNode points to the correct (fictional) node to be set as a real child
        AVLNode parent = cursorNode.getParent();
        int OldParentHeight = parent.getHeight();
        node.setParent(parent);
        if(parent.getKey() > node.getKey()){
            parent.setLeft(node);
        }
        else{
            parent.setRight(node);
        }
        while(parent!=null) {
            calcHeight(parent);
            parent = parent.getParent();
        }
        //After adding node at end of leaf
        return OldParentHeight;
    }

    private int calcHeight(AVLNode node){
        if(node.getKey()==-1){
            return -1;
        }
        int newHeight = 1 + Math.max(calcHeight(node.left),calcHeight(node.right));
        node.setHeight(newHeight);
        return node.getHeight();
    }

    /** as name implies, rotates to the left
     * P
     *  \
     *   6
     *    \
     *     7
     *      \
     *       8
     * the argument, called naughtyNode, in this scheme is 6.
     * Algorithm: 1.keep ref to P. set P right son from 6 to 7.
     *            2.for 6, change parent from P to 7.
     *            3.for 7, change his parent from 6 to P, and add 6 as his left son
     */
    private void rotateLeft(AVLNode grandpa){
        AVLNode father = grandpa.getRight();
        AVLNode leftT = father.getLeft();
        //Previous father of grandpa
        AVLNode prevFather =  grandpa.getParent();
        //Make the rotate
        father.setLeft(grandpa);
        grandpa.setRight(leftT);
        grandpa.setParent(father);
        leftT.setParent(grandpa);
        if (prevFather == null){
            this.root = father;
            father.setParent(null);
        }
        if (prevFather != null){
            father.setParent(prevFather);
            if(prevFather.getKey()<father.getKey()){
                prevFather.setRight(father);
            } else {
                prevFather.setLeft(father);
            }
        }
    }

    /** as name implies, rotates to the right
     */
    private void rotateRight(AVLNode grandpa){
        AVLNode father = grandpa.getLeft();
        AVLNode rightT = father.getRight();
        //Previous father of grandpa
        AVLNode prevFather = grandpa.getParent();
        //Make the rotate
        father.setRight(grandpa);
        grandpa.setLeft(rightT);
        grandpa.setParent(father);
        rightT.setParent(grandpa);
        if (prevFather == null){
            this.root = father;
            father.setParent(null);
        }
        if (prevFather != null){
            father.setParent(prevFather);
            if(prevFather.getKey()<father.getKey()){
                prevFather.setRight(father);
            } else {
                prevFather.setLeft(father);
            }

        }
    }


    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of nodes which required rebalancing operations (i.e. demotions or rotations).
     * returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        if (search(k)==null){
            return -1;
        }
        treeSize--;
        AVLNode cursorNode = root;//Pointer that we shall use to traverse the tree
        while(cursorNode.key!=k){
            cursorNode = cursorNode.getKey()>k ? cursorNode.getLeft() : cursorNode.getRight();
        }
        int oldHeight = BSTdelete(cursorNode, root);
        //Now cursorNode points to the relevantn node with key == k
        return oldHeight;
    }

    //Regular Delete in BST
    public int BSTdelete(AVLNode node, AVLNode cursorNode){
        boolean gotIt = false;
        while(cursorNode.isRealNode() && !gotIt) {
            if (cursorNode.getKey() == node.getKey()){
                gotIt = true;
            }
            else{
                cursorNode = cursorNode.getKey() > node.getKey() ? cursorNode.getLeft() : cursorNode.getRight();
            }
        }
        //Now cursorNode points to the node to be deleted
        AVLNode parent = cursorNode.getParent();
        int OldParentHeight = parent.getHeight();
        //Case no children to needed to be deleted node
        if (!cursorNode.getLeft().isRealNode() && !cursorNode.getRight().isRealNode()){
            if (parent.getRight() == cursorNode) {
                parent.setRight(new AVLNode());
            }
            if (parent.getLeft() == cursorNode){
                parent.setLeft(new AVLNode());
            }
            cursorNode.setParent(new AVLNode());

            return OldParentHeight;
        }
        //Case has one child - the Right child-  to needed to be deleted node
        else if (cursorNode.getRight().isRealNode() && !cursorNode.getLeft().isRealNode()){
            if (parent.getLeft() == cursorNode){
                parent.setLeft(cursorNode.getRight());
            }
            else{
                parent.setRight(cursorNode.getRight());
            }
            cursorNode.getRight().setParent(parent);
        }
        //Case has one child - the Left child-  to needed to be deleted node
        else if (cursorNode.getLeft().isRealNode() && !cursorNode.getRight().isRealNode()){
            if (parent.getLeft() == cursorNode){
                parent.setLeft(cursorNode.getRight());
            }
            else{
                parent.setRight(cursorNode.getRight());
            }
            cursorNode.getLeft().setParent(parent);
        }
        //Case the node has 2 children, we will call the successor node
        else {
            AVLNode succsessor = successor(cursorNode);
            AVLNode succParent = succsessor.getParent();
            cursorNode.value = succsessor.value;
            cursorNode.key = succsessor.key;
            BSTdelete(succsessor, succParent);
        }
        return 0;
    }

    /**
     * public Boolean min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public Boolean min() {
        AVLNode curr = root;
        while (curr.isRealNode()){
            curr = curr.getLeft();
        }
        return curr.getValue();
    }

    /**
     * public Boolean max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public Boolean max() {
        AVLNode curr = root;
        while (curr.isRealNode()){
            curr = curr.getRight();
        }
        return curr.getValue();
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arr = new int[treeSize];
        int[] index = {0};
        RecursiveKeyIndexing(arr,index,root);
        return arr;
    }

    /**
     *
     */
    private void RecursiveKeyIndexing(int[] arr, int[] index, AVLNode cursorNode){
        if(index[0] == arr.length){return;}
        if(cursorNode.getLeft()!=null && cursorNode.getLeft().isRealNode()==true){
            RecursiveKeyIndexing(arr,index,cursorNode.getLeft());
        }
        arr[index[0]] = cursorNode.getKey();
        index[0] = index[0] + 1;
        if(cursorNode.getRight() != null && cursorNode.getRight().isRealNode() == true){
            RecursiveKeyIndexing(arr,index,cursorNode.getRight());
        }
    }


    /**
     * public boolean[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public boolean[] infoToArray() {
        boolean[] arr = new boolean[treeSize];
        int[] index = new int[1];
        index[0]=0;
        traverseInOrderAndPopulateInfo(arr,index,root);
        return arr;
    }

    private void traverseInOrderAndPopulateInfo(boolean[] arr,int[] index, AVLNode node){
        if(index[0] == arr.length){return;}
        if(node.getLeft()!=null && node.getLeft().isRealNode()==true){
            traverseInOrderAndPopulateInfo(arr,index,node.getLeft());
        }
        arr[index[0]] = node.getValue();
        index[0] = index[0] + 1;
        if(node.getRight() != null && node.getRight().isRealNode() == true){
            traverseInOrderAndPopulateInfo(arr,index,node.getRight());
        }
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return treeSize;
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public AVLNode getRoot() {
        return root;
    }

    /**
     * public boolean prefixXor(int k)
     *
     * Given an argument k which is a key in the tree, calculate the xor of the values of nodes whose keys are
     * smaller or equal to k.
     *
     * precondition: this.search(k) != null
     *
     */
    public boolean prefixXor(int k){
        AVLNode cursorNode = root;
        Boolean res = null;
        int i = 0;
        while(true){
            if(cursorNode.getKey() <= k){
                if(res == null){
                    res = cursorNode.getValue();
                }
                else{
                    res = Boolean.logicalXor(res,cursorNode.getValue());
                }
                if(cursorNode.getKey() == k){
                    break;
                }
                cursorNode = cursorNode.getRight();
            }
            else{
                cursorNode = cursorNode.getLeft();
            }
        }
        return  res;
    }

    private AVLNode searchAndRetrieve(int key){
        return searchAndRetrieveRec(key,root);
    }

    private AVLNode searchAndRetrieveRec(int k,AVLNode node){
        if(node==null){
            return null;
        }
        if(node.key == k){
            return node;
        }
        if(k>node.key){
            return searchAndRetrieveRec(k,node.right);
        }
        return searchAndRetrieveRec(k,node.left);
    }

    /**
     * public AVLNode successor
     *
     * given a node 'node' in the tree, return the successor of 'node' in the tree (or null if successor doesn't exist)
     *
     * @param node - the node whose successor should be returned
     * @return the successor of 'node' if exists, null otherwise
     */
    public AVLNode successor(AVLNode node) {
        if (node == root) {
            return null;
        }
        if (node.getRight().isRealNode()) {
            return minForSucc(node.getRight());
        }
        AVLNode parent = node.getParent();
        while (parent.isRealNode() && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    //using while loop to find min in the smallest node in subtree
    private AVLNode minForSucc(AVLNode subRoot) {
        AVLNode curr = subRoot;
        while (curr.isRealNode()){
            curr = curr.getLeft();
        }
        return curr;
    }
    private int binarySearch(int[] arr, int k){
        int n = arr.length;
        int i = n/2;
        while(arr[i]!=k){
            if (arr[i] == k){
                return i;
            }
            if(arr[i]>k){
                i = i/2;
            }
            else{
                i+=i/2;
            }
        }
        return i;
    }



    /**
     * public boolean succPrefixXor(int k)
     *
     * This function is identical to prefixXor(int k) in terms of input/output. However, the implementation of
     * succPrefixXor should be the following: starting from the minimum-key node, iteratively call successor until
     * you reach the node of key k. Return the xor of all visited nodes.
     *
     * precondition: this.search(k) != null
     */
    public boolean succPrefixXor(int k){
        int[] arr = keysToArray();
        AVLNode traverser = searchAndRetrieve(arr[0]);
        int n = arr.length;
        int i = 1 ;
        boolean res = traverser.value;
        while(i<n && arr[i]<=k){
            traverser = searchAndRetrieve(arr[i]);
            i++;
            res = Boolean.logicalXor(res,traverser.value);
        }
        return res;
    }


    /**
     * public class AVLNode
     * <p>
     * This class represents a node in the AVL tree.
     * <p>
     * IMPORTANT: do not change the signatures of any function (i.e. access modifiers, return type, function name and
     * arguments. Changing these would break the automatic tester, and would result in worse grade.
     * <p>
     * However, you are allowed (and required) to implement the given functions, and can add functions of your own
     * according to your needs.
     */
    public class AVLNode {

        private int key;
        private Boolean value;
        private AVLNode left = null;
        private AVLNode right = null;
        private AVLNode parent = null;
        private int height;

        public AVLNode(Boolean value, int key) {
            this.value = value;
            this.key = key;
            if(key!=-1 && value!=null){
                AVLNode fictionalLeftChild = new AVLNode(null,-1);
                fictionalLeftChild.setParent(this);
                AVLNode fictionalRightChild = new AVLNode(null,-1);
                fictionalRightChild.setParent(this);
                setLeft(fictionalLeftChild);
                setRight(fictionalRightChild);
            }
            else{
                this.height = -1;
            }
        }

        public AVLNode() {
            key = -1;
            value = null;
        }


        //returns node's key (for virtual node return -1)
        public int getKey() {
            return key;
        }

        //returns node's value [info] (for virtual node return null)
        public Boolean getValue() {
            return value;
        }

        //sets left child
        public void setLeft(AVLNode node) {// TODO check if beed to increase height
            left = node;
            //node.setParent(this);
        }

        //returns left child (if there is no left child return null)
        public AVLNode getLeft() {
            return left;
        }

        //sets right child
        public void setRight(AVLNode node) { // TODO check if beed to increase height
            this.right = node;
            //node.setParent(this);
        }

        //returns right child (if there is no right child return null)
        public AVLNode getRight() {
            return right;
        }

        //sets parent
        public void setParent(AVLNode node) {
            parent = node;
        }

        //returns the parent (if there is no parent return null)
        public AVLNode getParent() {
            return parent;
        }

        // Returns True if this is a non-virtual AVL node
        public boolean isRealNode() {
            return key != -1 && value!=null;
        }

        // sets the height of the node
        public void setHeight(int newHeight) {
            this.height = newHeight;
        }


        // Returns the height of the node (-1 for virtual nodes)
        public int getHeight() {
            return this.height;
        }

        /**
         *
         * @ret this node's balance factor
         */
        public int getBalanceFactor(){
            return left.height - right.height;
        }

    }

}

