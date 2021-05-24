import java.util.Arrays;

public class myTester {
    public static void main(String[] args){
        AVLTree testingTree = new AVLTree();
        int n = 21;
        for(int i = 21; i>0; i-=2){
            testingTree.insert(i, true);
        }
        for(int i = 20; i> 8; i-=2){
            testingTree.insert(i, false);

        }
        testingTree.delete(5);
        System.out.println("Amos has small pines" + "*/s");
        System.out.println("TreeSize: "+testingTree.size());
        System.out.println("The Root is: "+testingTree.getRoot().getKey());
        System.out.println("The Root.getRight is: "+testingTree.getRoot().getRight().getKey());
        //System.out.println("The Root.getLeft is: "+testingTree.getRoot().getLeft().getKey());
        //System.out.println(testingTree.);
        //System.out.println("The Root.getLeft is: "+testingTree.getRoot().getLeft().getRight().getKey());

        System.out.println(Arrays.toString(testingTree.keysToArray()));
        System.out.println(Arrays.toString(testingTree.infoToArray()));
    }
}
