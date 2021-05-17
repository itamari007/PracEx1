import java.util.Arrays;

public class myTester {
    private  static AVLTree testingTree = new AVLTree();
    public static void main(String[] args){
        createAVlTestTree();
    }
    private static void createAVlTestTree(){
        int n = 101;
        for(int i = 1; i< n; i++){
            testingTree.insert(i,(i%4==0 || i%7 ==0 ));
        }
        System.out.println("SexySuccess");
        System.out.println("TreeSize: "+testingTree.size());
        System.out.println(Arrays.toString(testingTree.keysToArray()));
        System.out.println(Arrays.toString(testingTree.infoToArray()));
    }
}
