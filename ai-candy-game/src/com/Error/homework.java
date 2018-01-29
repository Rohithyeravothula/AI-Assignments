package com.Error;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author CHAITANYA POTLURI

 */
class Node {

    int valueOfNode = -1; // Value in the node
    int xPositionOfNode = 0; // x value of position of the node when the node position is (x,y)
    int yPositionOfNode = 0; // y value of position of the node when the node position is (x,y)
}

class Node1 {

    ArrayList subNodeCandyArrayList = new ArrayList();
    ArrayList subNodeOutputArrayList = new ArrayList();

    int xPos;
    int yPos;

    int depth = 0;

    int min = -1;
    int max = -1;

    ArrayList heuristicValues = new ArrayList();

}

public class homework {

    static int n; // Integer n, the width and height of the square board (0 < n <= 26)

    static int p; // Integer p, the number of fruit types (0 < p <= 9)

    static double remainingTime; // Remaining time in seconds

    static ArrayList al = new ArrayList<String>(); // have all input file data into the arraylist

    static ArrayList traverse = new ArrayList() {
    }; // traversed nodes

    static ArrayList columnIndexes = new ArrayList() {
    }; // for sorting the column Indicies 

    static String output[][]; // Output the matrix

    static int occurenceValueChecker = 0;

    static ArrayList outputList = new ArrayList() {
    };
    static ArrayList heuristicValueList = new ArrayList() {
    };

    static ArrayList<Node1> nodeList = new ArrayList<Node1>() {
    };

    static char[] rowHeader = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    static int finalXIndex = -1;

    static int finalYIndex = -1;

    static int[][] candyArray;
    static int[][] clonableCandyArray;
    static int[][] subcandyArray;

    static int hFlag = -1;    // Horizontal Flag Variable
    static int vFlag = -1;    // Vertical Flag Varibale

    static int countValuation = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        Node node = new Node();
        long startTime = System.currentTimeMillis();
        File file = new File(System.getProperty("user.dir") + "/input.txt");
//        System.out.println(System.getProperty("user.dir"));
        BufferedReader b;

        try {
            b = new BufferedReader(new FileReader(file));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                String line = readLine;
                al.add(line);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }

        n = Integer.parseInt((String) al.get(0));
        p = Integer.parseInt((String) al.get(1));
        remainingTime = Double.parseDouble((String) al.get(2));

        candyArray = new int[n][n];
        clonableCandyArray = new int[n][n];
        output = new String[n][n];

        /**
         * Push into the value into the matrix
         */
        pushValuesIntoArray();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pushValuesIntoArray();
                traverse.clear();

                if (candyArray[i][j] != -2) {
//                    if(i == 4 && j == 7)
                    countValuation = 0;
                    checkNodes(candyArray, i, j, i, j, candyArray[i][j], traverse);
                }
            }
        }
        recursiveNodes();
        long stopTime = System.currentTimeMillis();
        double elapsedTime = (stopTime - startTime) * 0.001;
        System.out.println(elapsedTime);

        /*
        Output 
         */
        try{
            write();
            writeCalibration(elapsedTime);
        }
        catch(Exception e)
        {

        }

    }

    public static int[][] arrayCopy(int[][] source, int[][] destination) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, n);
        }
        return destination;
    }

    public static int[][] copyArray() {
        int copiedArray[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copiedArray[i][j] = candyArray[i][j];
            }
        }
        return copiedArray;
    }


    // Depth 1, Loop 

    public static ArrayList firstDepthFunction()
    {

        if(remainingTime > remainingTime - 100)
        {
            return outputList;
        }
        for (int d = 0; d < outputList.size(); d++) {
            int[][] candySubNode = new int[n][n];
            arrayCopy((int[][]) outputList.get(d), candySubNode);

            Node1 node1 = new Node1();
            node1.subNodeCandyArrayList.add(candySubNode);
            node1.depth = 1;
            node1.max = -1;
            node1.min = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    candyArray = new int[n][n];
                    arrayCopy(candySubNode, candyArray);
                    traverse.clear();
                    if (candyArray[i][j] != -2) {
                        countValuation = 0;
                        checkNodes(candyArray, i, j, i, j, candySubNode[i][j], traverse);
                        node1.heuristicValues.add(heursticValue(occurenceValueChecker));
                        occurenceValueChecker = 0;
                        node1.subNodeOutputArrayList.add(copyArray());
                    }

                }
            }

            nodeList.add(node1);
        }
        return null;
    }

    // Find all empty values and recursive functions of the depth or time constraint

    public static void subNodesDepthSearch(){
        for (int depth = 0; depth < nodeList.size(); depth++) {
            int[][] candySubNode = new int[n][n];
            subcandyArray = new int[n][n];

            Node1 subNode = new Node1();

            if (depth == 0) {
                subNode.max = 1;
                subNode.min = -1;
            } else if (subNode.max == 1) {
                subNode.max = -1;
                subNode.min = 1;
//                    subNode.depth = nodeList.get(depth).depth ;
            } else {
                subNode.max = 1;
                subNode.min = -1;
//                    subNode.depth = nodeList.get(depth).depth;
            }

            if (nodeList.get(nodeList.size() - 1).max == -1 && nodeList.get(nodeList.size() - 1).min == 1) {
                subNode.depth = (nodeList.get(nodeList.size() - 1).depth);
            } else {
                subNode.depth = (nodeList.get(nodeList.size() - 1).depth) + 1;
            }

            for (int p = 0; p < nodeList.get(depth).subNodeOutputArrayList.size(); p++) {
                arrayCopy((int[][]) nodeList.get(depth).subNodeOutputArrayList.get(p), candySubNode);

                subNode.subNodeCandyArrayList.add(candySubNode);

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        arrayCopy((int[][]) nodeList.get(depth).subNodeOutputArrayList.get(p), candySubNode);
                        traverse.clear();
                        if (candySubNode[i][j] != -2) {
                            countValuation = 0;
                            checkNodes(candySubNode, i, j, i, j, candySubNode[i][j], traverse);
                            subNode.heuristicValues.add(heursticValue(occurenceValueChecker));
                            occurenceValueChecker = 0;
                            copyArray();
                            subNode.subNodeOutputArrayList.add(subcandyArray);
                        }
                    }
                }

                nodeList.add(subNode);

                if (subNode.subNodeOutputArrayList.isEmpty()) {

                    break;
                }
            }

//            if(nodeList.get(depth).depth == depth + 1)
//            {
//                count++;
//            }
        }
    }


    public static void write() throws IOException {
        BufferedWriter outputWriter = null;
        try {

            outputWriter = new BufferedWriter(new FileWriter("output.txt"));
            outputWriter.write(rowHeader[finalYIndex] + "" + (finalXIndex + 1));
            outputWriter.newLine();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    outputWriter.write(output[i][j]);
                }
                outputWriter.newLine();
            }
        } catch (Exception e) {
            outputWriter.write("FAIL");
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public static void writeCalibration(double value) throws IOException {
        BufferedWriter outputWriter = null;
        try {

            outputWriter = new BufferedWriter(new FileWriter("calibration.txt"));
            outputWriter.write("depth time is: " + value);
            outputWriter.newLine();
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    outputWriter.write(output[i][j]);
//                }
//                outputWriter.newLine();
//            }
        } catch (Exception e) {
            outputWriter.write("");
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public static void pushValuesIntoArray() {
        for (int loopVariable = 3; loopVariable < al.size(); loopVariable++) {
            char[] eachValue1 = new char[n];

            String eachValue;

            eachValue = (String) al.get(loopVariable);

            eachValue1 = eachValue.toCharArray();
            for (int x = 0; x < n; x++) {
                if (eachValue1[x] == '*') {
                    candyArray[loopVariable - 3][x] = -2;
                    clonableCandyArray[loopVariable - 3][x] = -2;
                } else {
                    candyArray[loopVariable - 3][x] = Character.getNumericValue(eachValue1[x]);
                    clonableCandyArray[loopVariable - 3][x] = Character.getNumericValue(eachValue1[x]);
                }

            }
        }
    }

    public static void checkNodes(int candyArray[][], int staticRow, int staticCol, int row, int col, int value, ArrayList tranverse) {

        Node node = new Node();
        node.valueOfNode = 0;
        node.xPositionOfNode = row;
        node.yPositionOfNode = col;

        candyArray[row][col] = -2;
        traverse.add(row + "-" + col);

//        int value = clonableCandyArray[staticRow][staticCol];

        if ((hFlag = horizontalPresenceOfNode(row, col, value, hFlag)) != -1) {
            countValuation = 0;
            if (hFlag == 0) {
                hFlag = -1;
                checkNodes(candyArray, staticRow, staticCol, row, col - 1, value, tranverse);

            } else if (hFlag == 1) {
                hFlag = -1;
                checkNodes(candyArray, staticRow, staticCol, row, col + 1, value, tranverse);

            }

        } else if ((vFlag = verticalPresenceOfNode(row, col, value, vFlag)) != -1) {

            countValuation = 0;
            if (vFlag == 2) {
                vFlag = -1;
                checkNodes(candyArray, staticRow, staticCol, row - 1, col, value, tranverse);
            } else if (vFlag == 3) {
                vFlag = -1;
                checkNodes(candyArray, staticRow, staticCol, row + 1, col, value, tranverse);
            }

        } else //            int occurrences = Collections.frequency(tranverse, "2-1");
        {
            countValuation++;

            if(countValuation >= 2)
            {
                tranverse.remove((tranverse.size() - 1));
            }

//            if( Collections.frequency(traverse, staticRow+"-"+staticCol) > 5)

            if (occurenceCheck())
            {

                if (uniqueOccurences() > occurenceValueChecker) {
                    occurenceValueChecker = uniqueOccurences();
                    columnIndexes();
                    finalXIndex = staticRow;
                    finalYIndex = staticCol;
                }

//            uniqueOccurences();
            } else if (tranverse.size() >= 2) {
                if((tranverse.size() - (countValuation + 1)) > 0)
                {
                    String prevNode = String.valueOf(tranverse.get(tranverse.size() - (countValuation+1)));
                    String[] parts = prevNode.split("-");
                    row = Integer.parseInt(parts[0]);
                    col = Integer.parseInt(parts[1]);

                    checkNodes(candyArray, staticRow, staticCol, row, col, value, tranverse);
                }
                else{
                    if (uniqueOccurences() > occurenceValueChecker) {
                        occurenceValueChecker = uniqueOccurences();
                        columnIndexes();
                        finalXIndex = staticRow;
                        finalYIndex = staticCol;
                    }
                }

            }
        }
        if(tranverse.size() == 1 && occurenceValueChecker == 0)
        {
            occurenceValueChecker = 1;
            columnIndexes();
            finalXIndex = staticRow;
            finalYIndex = staticCol;
        }

    }

    public static int horizontalPresenceOfNode(int xPos, int yPos, int value, int hFlag) {

        if (isLeftValueNodeValue(xPos, yPos, value)) {
            hFlag = 0;
            return hFlag;
        }

        if (isRightValueNodeValue(xPos, yPos, value)) {
            hFlag = 1;
            return hFlag;
        }

        return hFlag;
    }

    public static boolean isLeftValueNodeValue(int xPos, int yPos, int value) {

        if (yPos - 1 < 0) {
            return false;
        } else if (value == candyArray[xPos][yPos - 1]) {
            return true;
        }

        return false;
    }

    public static boolean isRightValueNodeValue(int xPos, int yPos, int value) {
        if (yPos + 1 > n - 1) {
            return false;
        } else if (value == candyArray[xPos][yPos + 1]) {
            return true;
        }

        return false;
    }

    public static int verticalPresenceOfNode(int xPos, int yPos, int value, int vFlag) {

        if (isTopValueNodeValue(xPos, yPos, value)) {
            vFlag = 2;
            return vFlag;
        }
        if (isBottomValueNodeValue(xPos, yPos, value)) {
            vFlag = 3;
            return vFlag;
        }
        return vFlag;
    }

    public static boolean isTopValueNodeValue(int xPos, int yPos, int value) {

        if (xPos - 1 < 0) {
            return false;
        } else if (value == candyArray[xPos - 1][yPos]) {
            return true;
        }

        return false;
    }

    public static boolean isBottomValueNodeValue(int xPos, int yPos, int value) {

        if (xPos + 1 > n - 1) {
            return false;
        } else if (value == candyArray[xPos + 1][yPos]) {
            return true;
        }

        return false;
    }

    static int count = 0;

    public static void columnIndexes() {
        for (int i = 0; i < n; i++) {
            count = 0;
            columnIndexes.clear();
            for (int j = 0; j < n; j++) {
                if (candyArray[j][i] == -2) {
                    count++;
                } else {
                    columnIndexes.add(j);
                }
            }
            for (int k = 0; k < count; k++) {
                output[k][i] = "*";
            }
            for (int p = count; p < n; p++) {
                int index = (int) columnIndexes.get(p - count);
                output[p][i] = String.valueOf(candyArray[index][i]);

            }
        }
    }

    public static boolean occurenceCheck() {

        int flagOccurence = 0;

        for (int i = 0; i < traverse.size(); i++) {
            int occurrences = Collections.frequency(traverse, traverse.get(i));

            if (occurrences > n + 1) {
                flagOccurence = 1;
            }

        }
        if (flagOccurence == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static void recursiveNodes()
    {
        long time;
        time = (long) remainingTime - 10000;
        try{
            Thread.sleep(time);
        }
        catch(Exception e){

        }
        for(int i = 0 ; i < n ; i ++)
        {

            for(int j = 0; j < n ; j ++ )
            {
                for(int k = 0; k < n -i ; k ++)
                {
                    for(int l = 0; l < n - j; l++)
                    {
                        firstDepthFunction();
                    }
                }
            }
        }
    }



    public static int uniqueOccurences() {
        Set<String> uniqueNodes = new HashSet<String>(traverse);
//        System.out.println("Unique Nodes count: " + uniqueNodes.size());
        return uniqueNodes.size();
    }

    public static int heursticValue(int traversedNodes) {
        return traversedNodes * traversedNodes;
    }

    public static int minimax(Node1 node, int depth, int maxORmin)
    {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        for(int i = 0; i < nodeList.size(); i++)
        {
            Node1 node11 = new Node1();

            if(nodeList.get(i).depth == 0)
                return max-min;

            else if((int)nodeList.get(i).heuristicValues.get(i) < min)
            {
                min = (int)nodeList.get(i).heuristicValues.get(i);
            }
            else{
                max = (int)nodeList.get(i).heuristicValues.get(i);
            }

        }
        return -1;
    }
}