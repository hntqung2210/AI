import java.util.PriorityQueue;

import org.w3c.dom.Node;

public class UniformCostSearchAlgo implements ISearchAlgo{
    PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());


}
