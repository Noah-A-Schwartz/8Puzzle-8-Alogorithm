package EightPuzzle;


//Used this to make a n-ary tree structure https://stackoverflow.com/questions/30460565/k-ary-tree-implementation-in-java-how-to

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

    public Node parent; // The parent of the current node
    public ArrayList<Node> children; // The children of the current node
    public Object info;
    public int distance;

    public static int maxNrOfChildren; // Equal to the k-arity;

    public Node(Object info) {
        this.info = info;
        children = new ArrayList<Node>();

    }

    public Node(Object info, int distance) {
        this.info = info;
        children = new ArrayList<Node>();
        this.distance = distance;
    }

    public void addChild(Node childNode)
    // You must take care so that future insertions don't override a child on i-th position
    {

        childNode.parent = this;
        children.add(childNode);


    }
    public int findRoot(int depth){

        if(parent != null){
            depth++;
            depth = this.parent.findRoot(depth);
        }

        return depth;
    }

}
