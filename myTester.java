public class myTester {
    private  static AVLTree testingTree = new AVLTree();
    public static void main(String[] args){
        createAVlTestTree();
    }
    private static void createAVlTestTree(){
        int n = 10;
        for(int i = 1; i< n; i++){
            testingTree.insert(i,Boolean.TRUE);
        }
        System.out.println("SexySuccess");
    }
}
