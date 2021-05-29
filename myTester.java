import java.util.Arrays;
import java.util.Random;

public class myTester {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 1024;
        long[] avlAVG = new long[5];
        long[] binAVG = new long[5];
        for (int i = 1; i <= 5; i++) {
            AVLTree balancedTree = new AVLTree();
            Tree bBinTree = new Tree();
            int N = n * i;
            long sumAVL = 0;
            long sumBIN = 0;
            while (N > 0) {
                N = N / 2;
                int x = 0;
                while (N != 0 && x + N <= n * i) {
                    x += N;
                    long startTime = System.nanoTime();
                    balancedTree.insert(x, Boolean.TRUE);
                    long endTime = System.nanoTime();
                    sumAVL += endTime - startTime;
                    startTime = System.nanoTime();
                    bBinTree.insert(x, Boolean.TRUE);
                    endTime = System.nanoTime();
                    sumBIN += endTime - startTime;
                }
            }
            avlAVG[i - 1] = sumAVL / (n * i);
            binAVG[i - 1] = sumBIN / (n * i);
            System.out.println("for AVL tree #" + i  + ", the avg insert time was: " + avlAVG[i - 1]);
            System.out.println("meanwhile, forthe corresponding regular binary tree with size " + ", the avg insert time was: " + binAVG[i - 1]);
        }
    }
}
