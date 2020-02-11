package EightPuzzle;

import EightPuzzle.view.GameController;
import javafx.fxml.FXMLLoader;

//Can take up to a minute
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


    public boolean isGoal() {
        if (Arrays.deepEquals(goalState, state))
        return true;

        else return false;
    }

    //Computes number of incorrect tiles
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
    //Returns current state of board
    public Integer[][] getState() {
        return state;
    }

    //Finds moves to get to the goal state
    public void solveGame() {

        Integer[][] clone;
        Integer[][] tempArray;
        List<Node> open = new ArrayList<Node>(); //Open list for nodes that have been discovered, but not expanded
        List<Node> closed = new ArrayList<Node>();//Closed list for nodes that have been expanded and explored
        int distance = 0; //Depth of tree
        Tree root = new Tree(4);//Creates a tree where any node can have max 4 children
        Node n = new Node(state, distance);//Create first node of initial state
        root.addRoot(n);//Add initial node as root of tree
        open.add(n);//Add to open list

        //Loop until finds goal State
        while (!isGoal()) {
            outer:
            for (int rows = 0; rows < state.length; rows++) {
                for (int columns = 0; columns < state[rows].length; columns++) {
                    if (state[rows][columns] == 0) {
                        open = createChild(n, rows, columns, open, closed);
                        n = open.get(0);
                        break outer;
                    }
                }
            }
            System.out.println(Arrays.deepToString(state));
        }
        System.out.println("Goal Found!");
        System.out.println(n.findRoot(0));
    }


    private Integer[][] cloneArray(Integer[][] tempArray) {
        Integer[][] array = new Integer[3][3];
        for (int i = 0; i < tempArray.length; i++) {
            array[i] = tempArray[i].clone();

        }
        return array;
    }

    private List<Node> createChild(Node n, int rows, int columns, List<Node> a, List<Node> b) {

        List<Node> open = a;
        List<Node> closed = b;
        Node tempNode;
        int temp;
        Integer[][] array;


        //Blank space is in a corner
        if (rows == 0 && columns == 0 || rows == 0 && columns == 2 || rows == 2 && columns == 0 || rows == 2 && columns == 2) {

            //Move number below up
            if (rows == 0) {
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows + 1][columns];
                array[rows + 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //Move number right left
            if (columns == 0) {
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns + 1];
                array[rows][columns + 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //Move number above down
            if (rows == 2) {
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows - 1][columns];
                array[rows - 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //Move number left right
            if (columns == 2) {
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns - 1];
                array[rows][columns - 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }


            open = addToOpen(n, open);
            closed.add(n);
            open.remove(n);
            array = (Integer[][]) open.get(0).info;
            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                state[i] = array[i].clone();
            }

        }
        //Blank space in a non corner and not the center
        else if (rows == 0 && columns == 1 || rows == 1 && columns == 0 || rows == 1 && columns == 2 || rows == 2 && columns == 1) {


            //If blank is middle right or middle left move the numbers below/above up and down
            if (columns == 0 || columns == 2) {
                //Move number below up
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows + 1][columns];
                array[rows + 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
                //Move number above down
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows - 1][columns];
                array[rows - 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //if blank is middle bottom, move move number above down
            if (columns == 1 && rows == 2) {
                //Move number above down
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows - 1][columns];
                array[rows - 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //middle Right, move left number right.
            if (columns == 2 && rows == 1) {
                //Move number right left
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns - 1];
                array[rows][columns - 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //If middle left, move number from center left
            if (columns == 0 && rows == 1) {
                //Move number right left
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns + 1];
                array[rows][columns + 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //If top middle, move below up
            if (columns == 1 && rows == 0) {
                //Move below up
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows + 1][columns];
                array[rows + 1][columns] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            //If in Row 0 or 2, move numbers on the left and right
            if (rows == 0 || rows == 2) {
                //Move number left right
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns - 1];
                array[rows][columns - 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
                //Move number right left
                array = cloneArray((Integer[][]) open.get(0).info);
                temp = array[rows][columns + 1];
                array[rows][columns + 1] = 0;
                array[rows][columns] = temp;
                tempNode = new Node(array, n.distance + 1);
                if (tempNode.distance > 1) {
                    if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                        n.addChild(tempNode);
                } else {
                    n.addChild(tempNode);
                }
            }
            open = addToOpen(n, open);
            closed.add(n);
            open.remove(n);
            array = (Integer[][]) open.get(0).info;
            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                state[i] = array[i].clone();
            }

          //Blank Space is in center
        } else if (rows == 1 && columns == 1) {

            //Move number left right
            array = cloneArray((Integer[][]) open.get(0).info);
            temp = array[rows][columns - 1];
            array[rows][columns - 1] = 0;
            array[rows][columns] = temp;
            tempNode = new Node(array, n.distance + 1);
            if (tempNode.distance > 1) {
                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                    n.addChild(tempNode);
            } else {
                n.addChild(tempNode);
            }

            //Move number right left
            array = cloneArray((Integer[][]) open.get(0).info);
            temp = array[rows][columns + 1];
            array[rows][columns + 1] = 0;
            array[rows][columns] = temp;
            tempNode = new Node(array, n.distance + 1);
            if (tempNode.distance > 1) {
                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                    n.addChild(tempNode);
            } else {
                n.addChild(tempNode);
            }

            //Move number above down
            array = cloneArray((Integer[][]) open.get(0).info);
            temp = array[rows - 1][columns];
            array[rows - 1][columns] = 0;
            array[rows][columns] = temp;
            tempNode = new Node(array, n.distance + 1);
            if (tempNode.distance > 1) {
                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                    n.addChild(tempNode);
            } else {
                n.addChild(tempNode);
            }

            //Move number below up
            array = cloneArray((Integer[][]) open.get(0).info);
            temp = array[rows + 1][columns];
            array[rows + 1][columns] = 0;
            array[rows][columns] = temp;
            tempNode = new Node(array, n.distance + 1);
            if (tempNode.distance > 1) {
                if (!(Arrays.deepEquals((Integer[][]) tempNode.info, (Integer[][]) n.parent.info)))
                    n.addChild(tempNode);
            } else {
                n.addChild(tempNode);
            }

            open = addToOpen(n, open);
            closed.add(n);
            open.remove(n);
            array = (Integer[][]) open.get(0).info;
            for (int i = 0; i < ((Integer[][]) open.get(0).info).length; i++) {
                state[i] = array[i].clone();
            }
        }


        return open;

    }

    //Add nodes newly expanded nodes to the open list according to their heuristic
    private List<Node> addToOpen(Node n, List<Node> open) {
        int count = 0;
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
        return open;
    }
}





