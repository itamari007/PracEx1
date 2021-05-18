import java.util.Arrays;

public class myTester {
    public static void main(String[] args){
        AVLTree testingTree = new AVLTree();
        int n = 21;
        for(int i = 1; i< n; i+=4){
            testingTree.insert(i,(i%4==0 || i%7 ==0 ));
        }
        for(int i = 20; i> 0; i-=2){
            testingTree.insert(i,(i%4==0 || i%7 ==0 ));
        }
        System.out.println("SexySuccess");
        System.out.println("TreeSize: "+testingTree.size());
        System.out.println(Arrays.toString(testingTree.keysToArray()));
        System.out.println(Arrays.toString(testingTree.infoToArray()));
    }
}
