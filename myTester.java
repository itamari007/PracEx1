import java.util.Arrays;

public class myTester {
    public static void main(String[] args){
        int n = 500;
        AVLTree[] trees = new AVLTree[5];
        Long[] avgTimes = new Long[5];
        for(int i= 1;i<=5;i++){
            AVLTree testingTree = new AVLTree();
            for(int j =0;j<=n*i;j++){
                testingTree.insert(j,Boolean.TRUE);
            }
            trees[i-1] = testingTree;
        }
        for(int i= 1;i<=5;i++){
            long sum = 0;
            for(int j =0;j<=n*i;j++){
                long startTime = System.nanoTime();
                trees[i-1].prefixXor(j);
                long endTime = System.nanoTime();
                sum+=endTime-startTime;
            }
            avgTimes[i-1] = sum / (n*i);
            System.out.println("For testingTree"+i+" the avg across all "+n*i+" natural numbers for prefixXor is: "+ avgTimes[i-1]+ " nanoseconds");
        }
    }
}
