import java.util.Arrays;
import java.util.Random;

public class myTester {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 1000;
        AVLTree[] trees = new AVLTree[5];
        Long[] avgTimes = new Long[5];
        for (int i = 1; i <= 5; i++) {
            long sum = 0;
            AVLTree testingTree = new AVLTree();
            int low = 0;
            int high = n*i;
            int mid1 = (low+high)/2;
            int mid2 = (low+high)/2;
            while (mid1 > 0 && mid2<=n*i){
                long startTime = System.nanoTime();
                testingTree.insert(mid1, Boolean.TRUE);
                testingTree.insert(mid2, Boolean.TRUE);
                long endTime = System.nanoTime();
                mid1 = (mid1+low)/2;
                mid2 = (mid2+high)/2;
                sum += (endTime - startTime);
            }
            trees[i - 1] = testingTree;
            avgTimes[i - 1] = sum / (n * i);
            System.out.println("For AVLTree testingTree " + i + " the avg across all " + n * i + " natural numbers for insert is: " + avgTimes[i - 1] + " nanoseconds");
        }
        int k = 1000;
        Tree[] Btrees = new Tree[5];
        Long[] BavgTimes = new Long[5];
        for (int i = 1; i <= 5; i++) {
            long sum = 0;
            Tree testingTree = new Tree();
            int low = 0;
            int high = n*i;
            int mid1 = (low+high)/2;
            int mid2 = (low+high)/2+1;
            while (mid1 > 0 && mid2<=n*i){
                long startTime = System.nanoTime();
                testingTree.insert(mid1, Boolean.TRUE);
                testingTree.insert(mid2, Boolean.TRUE);
                long endTime = System.nanoTime();
                mid1 = (mid1+low)/2;
                mid2 = (mid2+high)/2;
                sum += (endTime - startTime);
            }
            Btrees[i - 1] = testingTree;
            BavgTimes[i - 1] = sum / (k * i);
            System.out.println("For Tree testingTree " + i + " the avg across all " + k * i + " natural numbers for insert is: " + BavgTimes[i - 1] + " nanoseconds");
        }
    }
}
