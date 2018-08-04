package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {
    
    public static int cutoff = 250;
    public static int threads = 0;

    public static void sort(int[] array, int from, int to) {
	int size = to - from;
	if (size <= cutoff) {
            Arrays.sort(array, from, to); 
	}
	else {
	    int midPoint = (to - from) / 2 + from;
            // TODO implement me
	    CompletableFuture<int[]> parsort1 = parsort(array, from, midPoint);
	    // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, midPoint, to);
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
	    // TODO implement me
            int[] combinedArray = new int[xs1.length + xs2.length];
            int i = 0, j = 0, k = 0;
	    int lengthOfXS1 = xs1.length;
	    int lengthOfXS2 = xs2.length;
	    while (i < lengthOfXS1 && j < lengthOfXS2) {
		if (xs1[i] <= xs2[j]) {
                    combinedArray[k] = xs1[i];
		    i++;
		} else if (xs1[i] > xs2[j]) {
		    combinedArray[k] = xs2[j];
		    j++;
		}
		    k++;
		}
		while (i < lengthOfXS1) {                    
		    combinedArray[k] = xs1[i];
		    k++; i++;		    
		}
		while (j < lengthOfXS2) {
		    combinedArray[k] = xs2[j];
		    k++; j++;
		}
		for (int p = 0; p < combinedArray.length; p++) {
		    array[p + from] = combinedArray[p];
		}
                return combinedArray;
                });

		parsort.whenComplete((result, throwable) -> {
		if (throwable == null) {
		    parsort.complete(array);
		} else {
		    parsort.completeExceptionally(throwable);
		} });
		parsort.join();
		}
	}

	private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
		threads = threads + 1;
		return CompletableFuture.supplyAsync(() -> {
		// TODO implement me	
                int[] result = new int[to - from];
		sort(array, from, to);
		for (int i = from; i < to; i++) {
		    result[i - from] = array[i];
		}
		return result;
		});
	}
    
}