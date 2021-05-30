import java.util.Arrays;
import java.util.Random;

import java.util.Arrays;

public class myTester {
    public static void main(String[] args){
        int n = 500;
        AVLTree[] trees = new AVLTree[5];
        Long[] avgTimes = new Long[5];
        Long[] avgTimes100 = new Long[5];
        for(int i= 1;i<=5;i++){
            AVLTree testingTree = new AVLTree();
            for(int j =1;j<=n*i;j++){
                testingTree.insert(j,Boolean.TRUE);
            }
            trees[i-1] = testingTree;
        }
        for(int i= 1;i<=5;i++){
            long sum = 0;
            for(int j =1;j<=n*i;j++){
                long startTime = System.nanoTime();
                trees[i-1].prefixXor(j);
                long endTime = System.nanoTime();
                sum+=endTime-startTime;
            }
            avgTimes[i-1] = sum / (n*i);
            System.out.println("For testingTree"+i+" the avg across all "+n*i+" natural numbers for prefixXor is: "+ avgTimes[i-1]+ " nanoseconds");
        }
        for(int i=1;i<=5;i++){
            long sum100 = 0;
            for(int j=1;j<=100;j++){
                long t1 = System.nanoTime();
                trees[i-1].prefixXor(j);
                long t2 = System.nanoTime();
                sum100 += t2-t1;
            }
            avgTimes100[i-1] = sum100 / 100;
            System.out.println("For testingTree"+i+" the avg across all "+n*i+" natural numbers for prefixXor is: "+ avgTimes100[i-1]+ " nanoseconds");
        }
    }
}
