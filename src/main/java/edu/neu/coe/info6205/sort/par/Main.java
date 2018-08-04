package edu.neu.coe.info6205.sort.par;

import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        int myArraySize = 35000;
        long sum = 0;
        long finishTime, totalTime;
        double avgTime = 0 ;        
        int j, temp;
        for(int z = 0; z < 10; z++) {
            int[] myArray = new int[myArraySize];
            Random random = new Random(0L);
            for (int i = 0; i < myArray.length; i++) 
                myArray[i] = random.nextInt(200000);
            Random r = new Random();
            //loop in the reverse
            for(int i = myArray.length-1; i > 0; i--) {
                j = r.nextInt(i);
        	temp = myArray[i];
        	myArray[i] = myArray[j];
        	myArray[j] = temp;
            }
            long startTime = System.currentTimeMillis();            
            ParSort.threads = 0;
            ParSort.sort(myArray, 0, myArray.length);
            finishTime = System.currentTimeMillis();            
            totalTime = finishTime - startTime;
            System.out.println("Completion time = " + totalTime);
            sum += totalTime;
            }
        avgTime = sum / 10;
        System.out.println("Average completion time = " + avgTime);
    }
    
}  