import k21.Node;

public class Depth_limitedsearch {
    public static final Node Depth = new Node("Depth");
    public Node execute(Node root, String goal, int limitedDepth){
        return new Depth_limitedsearch(root, goal, limitedDepth);
    }
}
private Node recurse(Node root, String goal, int limitedDepth){
    Node resurt=null;
    if(root.getLabel().equals(goal)){
        return root;
    }else{
        if(limitedDepth==0){
            return null;
        }else{
            for(Node node : root.getChildrenNodes()){
                node.setParent(root);
                if(resurt==null) recurse(node, goal, limitedDepth-1);
            }
        }
        return null;
}
}
