package EightPuzzle;

import EightPuzzle.view.GameController;
import javafx.fxml.FXMLLoader;


import java.util.*;

public class EightPuzzle {
    private Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Integer[][] state;
    private static Integer[][] goalState = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
    private boolean goal;
    List<Integer> list;
    private GameController myController;


    public EightPuzzle() {

        state = new Integer[3][3];
        shuffle();

    }

    public Integer[][] getPuzzle() {
        return state;
    }

    public void shuffle() {
        list = Arrays.asList(numbers);
        Collections.shuffle(list);
        listToArray();


    }

    public void listToArray() {
        int count = 0;
        for (int row = 0; row < state.length; row++)
            for (int column = 0; column < state[row].length; column++) {
                state[row][column] = list.get(count);
                count++;
            }
    }


    public boolean isGoal(){
        if(state == goalState)

            return true;
        else return false;
    }

    public int heuristic(Integer[][] array) {
        int wrong = 0;
        for (int rows = 0; rows < array.length; rows++) {
            for (int columns = 0; columns < array[rows].length; columns++) {
                if (array[rows][columns] != goalState[rows][columns])
                    wrong++;
            }
        }
        return wrong;
    }

    public Integer[][] getState() {
        return state;
    }

    public void solveGame() {
        Integer[][] clone = new Integer[3][];
        Integer[][] tempArray;
        List<Node> open = new ArrayList<Node>();
        List<Node> closed = new ArrayList<Node>();
        int distance = 0;
        Tree root = new Tree(4);
        tempArray = state;
        for (int i = 0; i < state.length; i++) {
            clone[i] = tempArray[i].clone();
        }

        Node n = new Node(clone, distance);
        root.addRoot(n);
        Node tempNode;
        int min = 0;
        int temp = 0;
        open.add(n);
        int count = 0;


        while (!isGoal()) {
            distance++;
             outer: for (int rows = 0; rows < state.length; rows++) {
                for (int columns = 0; columns < state[rows].length; columns++) {
                    // System.out.println(Arrays.deepToString(state));
                    if (state[rows][columns] == 0) {
                        //if 0 is in top right corner
                        if (rows == 0 && columns == 0) {

                            closed.add(open.get(0));




                            clone = cloneArray((Integer[][]) open.get(0).info);

                            //move number below up
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number to the right left

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 0 && columns == 1) {

                            closed.add(open.get(0));

                            //Move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number to the right left

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number below up

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 0 && columns == 2) {

                            //move number below up
                            closed.add(open.get(0));


                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 1 && columns == 0) {

                            closed.add(open.get(0));

                            //Move number to above down

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number to the right left
                            tempArray = ((Integer[][]) open.get(0).info);

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number below up

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 1 && columns == 1) {

                            closed.add(open.get(0));

                            //Move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number to the right left

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number below up

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number above down

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }
                                    else continue;

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 1 && columns == 2) {

                            closed.add(open.get(0));

                            //Move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number above down

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number below up

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows + 1][columns];
                            clone[rows + 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 2 && columns == 0) {

                            closed.add(open.get(0));

                            //Move number to the right left

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number above down

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;
                        } else if (rows == 2 && columns == 1) {

                            closed.add(open.get(0));

                            //Move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number above down

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //Move number right left

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns + 1];
                            clone[rows][columns + 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;

                        } else if (rows == 2 && columns == 2) {

                            //Move number to the left right

                            clone = cloneArray((Integer[][]) open.get(0).info);
                            temp = clone[rows][columns - 1];
                            clone[rows][columns - 1] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            //move number above down
                            clone = cloneArray((Integer[][]) open.get(0).info);

                            temp = clone[rows - 1][columns];
                            clone[rows - 1][columns] = 0;
                            clone[rows][columns] = temp;
                            tempNode = new Node(clone, n.distance+1);
                            tempNode.parent = open.get(0);
                            if(tempNode.distance > 1) {
                                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) open.get(0).parent.info)))
                                    n.addChild(tempNode);
                            }
                            else{
                                n.addChild(tempNode);
                            }

                            count = 0;
                            while (count < n.children.size()) {
                                for (int i = 0; i < open.size(); i++) {
                                    if (heuristic((Integer[][]) n.children.get(count).info) + n.children.get(count).distance <= heuristic((Integer[][]) open.get(i).info) + open.get(i).distance) {
                                        open.add(i, n.children.get(count));
                                        count++;
                                        break;
                                    } else if (i == open.size() - 1) {
                                        open.add(n.children.get(count));
                                        count++;
                                        break;
                                    }

                                }
                            }
                            open.remove(n);
                            n = open.get(0);
                            tempArray = (Integer[][]) open.get(0).info;
                            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                                state[i] = tempArray[i].clone();
                            }
                            break outer;

                        }
                    }

                }

            }
            System.out.println(Arrays.deepToString(state));
        }
        System.out.println("Goal Found!");
        System.out.println(n.findRoot(0));

    }


    public ArrayList<Node> expandNode(ArrayList<Node> open, Node node) {
        int temp = 0;


        return open;
    }

    private Integer[][] cloneArray(Integer[][] tempArray) {
        Integer[][] array = new Integer[3][3];
        for (int i = 0; i < tempArray.length; i++) {
            array[i] = tempArray[i].clone();

        }
        return array;
    }

    private void createChild(Node n, int rows, int columns){
        Node tempNode;
        int temp;
        Integer[][] array = cloneArray((Integer[][])n.info);

        if(rows == 0 || rows == 2 &&  columns == 0 || columns == 2){
            temp = array[rows][columns - 1];
            array[rows][columns - 1] = 0;
            array[rows][columns] = temp;
            tempNode = new Node(array, n.distance+1);
            if(tempNode.distance > 1) {
                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                    n.addChild(tempNode);
            }
            else{
                n.addChild(tempNode);
            }
        }





    }

}


