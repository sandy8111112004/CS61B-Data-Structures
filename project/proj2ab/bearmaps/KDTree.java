package bearmaps;

import java.util.List;

public class KDTree implements PointSet{
    private Node root;

    public KDTree(List<Point> points){
        if(points == null) throw new IllegalArgumentException("please don't enter null list");
        for(Point p: points){
            add(p);
        }
    }

    @Override
    public Point nearest(double x, double y){
        Point targetPoint = new Point(x,y);
        if(contains(targetPoint)) return targetPoint;

        return new Point(0,0);
    }

    public void clear(){
        root =null;
    }

    public boolean contains(Point p){
        if(p==null) throw new IllegalArgumentException("Please don't enter null point");
        if(root ==null) throw new IllegalArgumentException("KDTree is empty");
        Node pNode = new Node(p,1,0);
        return find(root, pNode);
    }

    private boolean find(Node start, Node target){
        if(start == null){
            return false;
        }
        if(start.equals(target)){
            return true;
        }
        if(start.status==0){
            if(target.key.getX() >= start.key.getX()){
                return find(start.right,target);
            }else{
                return find(start.left,target);
            }
        }else{
            if(target.key.getY() >= start.key.getY()){
                return find(start.right,target);
            }else{
                return find(start.left,target);
            }
        }
    }

    public void printKDTree(){
        if(root==null) return;
        preOrder(root,0);
    }
    private void preOrder(Node x,int depth){
        StringBuilder prefix = new StringBuilder();
        for(int i=0;i<depth;i++){
            prefix.append("-- ");
        }
        if(x==null) {
            System.out.println(prefix.toString());
            return;
        }
        else {
            System.out.println(prefix.toString() + x.key + ", status: " + x.status);
        }
        depth += 1;
        preOrder(x.left, depth);
        preOrder(x.right, depth);
    }

    public int size(){
        return size(root);
    }

    private int size(Node p){
        if(p==null)return 0;
        else return p.size;
    }

    public void add(Point p) {
        if (root == null) {
            root = new Node(p, 1, 0);
        } else {
            if(contains(p)) return;
            root = addHelper(root,p,0);
        }
    }

    private Node addHelper(Node prev, Point p,int pStatus){
        if(prev==null){
            return new Node(p,1,pStatus);
        }
        if(prev.status==0){
            if(p.getX() >= prev.key.getX()){
                prev.right = addHelper(prev.right,p,1);
            }else{
                prev.left = addHelper(prev.left,p,1);
            }
        }else if(prev.status==1){
            if(p.getY()>=prev.key.getY()){
                prev.right = addHelper(prev.right,p,0);
            }else{
                prev.left = addHelper(prev.left,p,0);
            }
        }
        prev.size = 1 + size(prev.left) + size(prev.right);
        return prev;
    }



    //set up the Node property
    private class Node {
        private Point key;
        private Node left, right;
        private int size;
        private int status;

        public Node(Point key, int size, int status){
            this.key = key;
            this.size = size;
            this.status = status;
        }

        @Override
        public boolean equals(Object o){
            if(o == null ||o.getClass()!= this.getClass()){
                return false;
            }else {
                Node otherNode = (Node) o;
                return key.equals(otherNode.key);
            }
        }

        @Override
        public String toString(){
            return key.toString();
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}
