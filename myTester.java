import java.util.Arrays;

public class myTester {
    public static void main(String[] args) {
        int n = 1000;
        AVLTree[] trees = new AVLTree[5];
        Long[] avgTimes = new Long[5];
        for (int i = 1; i <= 5; i++) {
            long sum = 0;
            AVLTree testingTree = new AVLTree();
            for (int j = 1; j <= n * i; j++) {
                long startTime = System.nanoTime();
                testingTree.insert(j, Boolean.TRUE);
                long endTime = System.nanoTime();
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
            for (int j = 1; j <= k * i; j++) {
                long startTime = System.nanoTime();
                testingTree.insert(j, Boolean.TRUE);
                long endTime = System.nanoTime();
                sum += (endTime - startTime);
            }
            Btrees[i - 1] = testingTree;
            BavgTimes[i - 1] = sum / (k * i);
            System.out.println("For Tree testingTree " + i + " the avg across all " + k * i + " natural numbers for insert is: " + BavgTimes[i - 1] + " nanoseconds");
        }
    }
}
