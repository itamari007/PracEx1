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

public class AVLTree {
    private AVLNode root;
    private int treeSize;
    /**
     * This constructor creates an empty AVLTree.
     */
    public AVLTree(){
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
        if(node.key == k){
            return Boolean.TRUE;
        }
        if(node.isLeaf()){
            return Boolean.FALSE;
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
        if (search(k)){
            return -1;
        }
        AVLNode insertedNode = new AVLNode(i,k);
        int counter = 0;
        //TODO: find where to insert the new node, i.e, who is it's parent
        AVLNode fakeParent = new AVLNode(true,42);//TODO: calculate actual daddy.
        fakeParent.setRight(insertedNode);
        insertedNode.setParent(fakeParent);
        int prevHeight = fakeParent.getHeight();
        while (fakeParent!=null){
            //For example: if key is bigger than daddy's
            int parentBF = fakeParent.getBalanceFactor();
            int absBF = Math.abs(parentBF);
            if(prevHeight == fakeParent.getHeight() && absBF < 2){
                return counter;
            }
            if(absBF < 2 && prevHeight != fakeParent.getHeight()){
                prevHeight = fakeParent.getHeight();
                fakeParent = fakeParent.getParent();
            }
        }

        return counter;
    }



    private AVLNode findProperNode(AVLNode node, int key){
        if (node.isLeaf()){
            if (node.key < key){
                //return node.setRight();
            }
        }
        return node;
    }

    /** as name implies, rorates to the left
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
    private void rotateLeft(AVLNode naughtyNode){
        AVLNode P = naughtyNode.getParent();//1
        AVLNode seven = naughtyNode.getRight();//1
        P.setRight(seven);//1
        seven.setParent(P);//3
        naughtyNode.setParent(seven);//2
        seven.setLeft(naughtyNode);//3
    }

    /** as name implies, rorates to the right
     */
    private void rotateRight(AVLNode naughtyNode){
        AVLNode P = naughtyNode.getParent();
        AVLNode seven = naughtyNode.getLeft();
        P.setLeft(seven);
        seven.setParent(P);
        naughtyNode.setParent(seven);
        seven.setRight(naughtyNode);

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
        return 42;    // to be replaced by student code
    }

    /**
     * public Boolean min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public Boolean min() {
        return infoToArray()[0];
    }

    /**
     * public Boolean max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public Boolean max() {
        return infoToArray()[size()-1];
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arr = new int[treeSize];
        traverseInOrder(arr,0,root);
        return arr;
    }

    //Traverses tree according to the regular order between Natural numbers.
    private void traverseInOrder(int[] arr,int index, AVLNode node){
        if(!this.empty()){
            traverseInOrder(arr,index,node.left);
            arr[index++] = this.root.getKey();
            traverseInOrder(arr,index,node.right);
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
        traverseInOrderAndPopulateInfo(arr,0,root);
        return arr;
    }

    private void traverseInOrderAndPopulateInfo(boolean[] arr,int index, AVLNode node){
        if(!this.empty()){
            traverseInOrderAndPopulateInfo(arr,index,node.left);
            arr[index++] = this.root.getValue();
            traverseInOrderAndPopulateInfo(arr,index,node.right);
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
        return false;
    }

    /**
     * public AVLNode successor
     *
     * given a node 'node' in the tree, return the successor of 'node' in the tree (or null if successor doesn't exist)
     *
     * @param node - the node whose successor should be returned
     * @return the successor of 'node' if exists, null otherwise
     */
    public AVLNode successor(AVLNode node){
        return null;
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
        return false;
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
                setLeft(new AVLNode(null,-1));
            }
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
        }

        //returns left child (if there is no left child return null)
        public AVLNode getLeft() {
            return left;
        }

        //sets right child
        public void setRight(AVLNode node) { // TODO check if beed to increase height
            right = node;
        }

        //returns right child (if there is no right child return null)
        public AVLNode getRight() {
            return right;
        }

        //sets parent
        public void setParent(AVLNode node) {// TODO check if beed to increase height
            parent = node;
        }

        //returns the parent (if there is no parent return null)
        public AVLNode getParent() {
            return parent;
        }

        // Returns True if this is a non-virtual AVL node
        public boolean isRealNode() {
            return key != -1;
        }

        // sets the height of the node
        public void setHeight(int height) {
            height = height;
        }

        // Returns the height of the node (-1 for virtual nodes)
        public int getHeight() {
            if(!this.isRealNode()){
                this.setHeight(-1);
                return  -1;
            }
            if(this.isLeaf()){
                this.setHeight(0);
                return 0;
            }
            this.setHeight(1 + Math.max(left.getHeight(), right.getHeight()));
            return height;
        }
        //checks if leaf
        public boolean isLeaf(){
            return left == null && right == null;
        }

        /**
         *
         * @returns this node's balance factor
         */
        public int getBalanceFactor(){
            return left.getHeight()- right.getHeight();
        }
    }

}


