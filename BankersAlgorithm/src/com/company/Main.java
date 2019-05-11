package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static ArrayList<Integer> popUnvisited(ArrayList<Integer> unvisitedArr, int value) {

        int index = unvisitedArr.lastIndexOf(value);
        if (index != -1) {
            unvisitedArr.remove(index);
        }
        return unvisitedArr;
    }

    private static void printArr(int [] A, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + ", ");
        }
        System.out.println();
    }

    private static int[] resourceVector (int row, int [] A, int [][] C, int [][] R) {
        for (int i = 0; i < A.length; i++) {
                A[i] = A[i] - R[row][i];
                A[i] = C[row][i] + A[i] + R[row][i];

                //Thread.sleep(1000);
        }
            System.out.println("       A is Updated.");
            return A;
    }

    private static String returnState(int N, int M, int [] A, int [][] C, int [][] R) throws InterruptedException{

        ArrayList<Integer> visitedRows = new ArrayList<>(N - 1);
        ArrayList<Integer> unvisitedRows = new ArrayList<>(N - 1);

        int colValue = M; // which columns are = 0, if sum = 0, then processed row, so skip
        int processCount = 0;
        int iterations = N;
        boolean flag = true;
        boolean pFlag = false;

        for (int k = 0; k < N; k++){ // process array
            unvisitedRows.add(k);
        }

        while (flag) {
            for (int i = 0; i < N; i++) {
                if (pFlag == true) {
                    pFlag = false;
                    break;
                }
                if (i != N - 1 && !visitedRows.contains(unvisitedRows)) {
                    if (processCount == N && unvisitedRows.isEmpty()) {
                        return "Safe BECAUSE EMPTY.";
                    } else {

                        i = unvisitedRows.get(i);
                        //i++;
                        System.out.println("Printing i "+i);
                    }
                }
                for (int j = 0; j < M; j++) {
                    pFlag = false;
                    System.out.println(Arrays.toString(visitedRows.toArray())+" VISITED");
                    System.out.println(Arrays.toString(unvisitedRows.toArray())+" UNVISITED");
/*
                    if (iterations == 0) {
                        flag = false;
                        return "Unsafe state.";
                    }*/

                    System.out.println("ROW "+i+" and COLUMN "+j+" and ITER "+iterations);

                    if (R[i][j] > A[j]) {
                        colValue = M;
                        iterations--;


                        System.out.println(i+" row is unsafe, R value "+R[i][j]+", iteration "+iterations);
                        Thread.sleep(1000);
                        break; // go to next ROW
                    }

                    if (R[i][j] == 0 && j == M - 1 && colValue == 1 ) { // All values in columns are 0, so skip this row
                        colValue = M;
                        break;
                    }

                    if (R[i][j] <= A[j] && j == M - 1 && R[i][j] > 0 && i != N - 1) { // Last column
                        colValue = M;
                        resourceVector(i, A, C, R);
                        processCount++;

                        pFlag = true;
                        System.out.println("Last column. Calculate new A and add/ remove i "+i);
                        printArr(A, M);

                        visitedRows.add(i);
                        popUnvisited(unvisitedRows, i);
                        System.out.println(Arrays.toString(visitedRows.toArray())+" VISITED in if");
                        System.out.println(Arrays.toString(unvisitedRows.toArray())+" UNVISITED");
                        Thread.sleep(1000);
                        break;
                    }

                    if (R[i][j] <= A[j] && j == M - 1 && R[i][j] > 0 && i == N - 1) { // Last row
                    //if (R[i][j] <= A[j] && j == M - 1 && R[i][j] > 0 && ) {
                        colValue = M;
                        resourceVector(i, A, C, R);
                        processCount++;
                        pFlag = true;
                        System.out.println("Last ROW. Calculate new A ");
                        printArr(A, M);
                        visitedRows.add(i);
                        popUnvisited(unvisitedRows, i);

                        System.out.println(Arrays.toString(visitedRows.toArray()) + " VISITED in last");
                        System.out.println(Arrays.toString(unvisitedRows.toArray()) + " UNVISITED");
                        Thread.sleep(1000);
                    }

                    if (R[i][j] == 0 && j != M - 1 && colValue != 0) { // column = 0
                        colValue--;

                        System.out.println(j + " column empty. go to next column.\nColumn value "+colValue);
                        Thread.sleep(1000);
                    }

                    if (R[i][j] <= A[j] && j != M - 1 && R[i][j] > 0) { // column is 0 < R <= A
                        System.out.println("R <= A in "+i+" "+j);

                        Thread.sleep(1000);
                    }

                    if (processCount == N && unvisitedRows.isEmpty()) { // When all the processes finished running
                        return "Safe state.";
                    } else if (i == N - 1 && processCount != N && pFlag != true) {
                        flag = false;
                        return "Unsafe state.";
                    }

                    /*if (R[i][j] != 0) {

                        System.out.println("Do stuff, "+i + " row is safe, R value " + R[i][j] + ", iteration " + iterations);
                        //System.out.println(" Processing the stuff in row " + i);
                        Thread.sleep(1000);

                    }*/
                }
            }
        }
        return "Hello";
    }

    public static void main(String[] args) {
/*
	int processesNum = 4;
	int resourceNum = 5;

	int[] availableResources = new int[] {0, 1, 0, 2, 1};

	int[][] currentAllocMatrix = new int[processesNum][resourceNum];
	int[][] requestMatrix = new int[processesNum][resourceNum];

	currentAllocMatrix[0] = new int [] {0, 1, 1, 1, 2};
	currentAllocMatrix[1] = new int [] {0, 1, 0, 1, 0};
	currentAllocMatrix[2] = new int [] {0, 0, 0, 0, 1};
	currentAllocMatrix[3] = new int [] {2, 1, 0, 0, 0};

	requestMatrix[0] = new int[] {1, 1, 0, 2, 1};
	requestMatrix[1] = new int[] {0, 1, 0, 2, 1};
	requestMatrix[2] = new int[] {0, 2, 0, 3, 1};
	requestMatrix[3] = new int[] {0, 2, 1, 1, 0};
*/

	int processesNum = 2;
	int resourceNum = 5;

	int[] availableResources = new int[] {0, 1, 0, 2, 1};

	int[][] currentAllocMatrix = new int[processesNum][resourceNum];
	int[][] requestMatrix = new int[processesNum][resourceNum];

	currentAllocMatrix[1] = new int [] {0, 0, 0, 0, 1};
	currentAllocMatrix[0] = new int [] {0, 1, 0, 1, 0};

	requestMatrix[1] = new int[] {0, 2, 0, 3, 1};
	requestMatrix[0] = new int[] {0, 1, 0, 2, 1};


    try {
        System.out.print(returnState(processesNum, resourceNum, availableResources, currentAllocMatrix, requestMatrix));
    } catch(Exception ex) {
        System.out.print(ex);
    }

    }
}
