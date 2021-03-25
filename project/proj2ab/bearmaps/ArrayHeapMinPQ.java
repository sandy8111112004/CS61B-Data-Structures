package bearmaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    public ArrayHeapMinPQ(){ items = new ArrayList<>();}
    private int parent(int index){
        if((index-1)/2 <0){
            return -1;
        }else {
            return (index - 1) / 2;
        }
    }
    private int leftChild(int index){
        if(2*index+1 <items.size()) {
            return 2 * index + 1;
        }else{
            return -1;
        }
    }
    private int rightChild(int index){
        if(2*index+2 < items.size()) {
            return 2 * index + 2;
        }else{
            return -1;
        }
    }

    private void swap(int index1, int index2){
        PriorityNode temp1 = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp1);
    }

    private void swim(int index){
        if(parent(index)<0){
            return;
        }
        while(items.get(index).compareTo(items.get(parent(index))) < 0){
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void sink(int index){
        if(items.size()==0){
            return;
        }

        PriorityNode rightNode;
        PriorityNode leftNode;
        if(rightChild(index)!= -1 && leftChild(index)!= -1) {
            rightNode = items.get(rightChild(index));
            leftNode = items.get(leftChild(index));
        }else if(leftChild(index)!=-1){
            leftNode = items.get(leftChild(index));
            rightNode = null;
        }else if(rightChild(index)!=-1){
            rightNode = items.get(rightChild(index));
            leftNode = null;
        }else{
            rightNode = null;
            leftNode = null;
        }

        while(items.get(index).compareTo(rightNode) > 0 ||
              items.get(index).compareTo(leftNode) > 0){
            if(rightNode!= null && rightNode.compareTo(leftNode) <= 0){
                swap(index,rightChild(index));
                sink(rightChild(index));
            }else{
                swap(index,leftChild(index));
                sink(leftChild(index));
            }
        }

    }

    //assume input item will never be null
    @Override
    public void add(T item, double priority){
        if(contains(item)){
            throw new IllegalArgumentException("The ArrayHeapMinPQ contains this item already");
        }else {
            items.add(new PriorityNode(item, priority));
            swim(items.size() - 1);
        }
    }

    private boolean containsHelper(T item, int startInd){
        PriorityNode temp = new PriorityNode(item, 0);
        if(items.get(startInd).equals(temp)){
            return true;
        }else{
            return (leftChild(startInd) != -1 && containsHelper(item, leftChild(startInd))) || (rightChild(startInd) != -1 && containsHelper(item, rightChild(startInd)));
        }
    }

    @Override
    public boolean contains(T item){
        if(items.size() ==0){
            return false;
        }
        return containsHelper(item, 0);
    }

//    @Override
//    public boolean contains(T item){
//        //built-in contains method uses equals method to check if the ArrayList contains certain item
//        return items.contains(new PriorityNode(item,0));    //runtime O(n)
//    }

    //for testing purpose only
    T getNodeValue(int index){
        return items.get(index).getItem();
    }

    //for testing purpose only
    String printableMinPQ(){
        return items.toString();
    }

    //for testing purpose only
    void clear(){
        items.clear();
    }

    @Override
    public T getSmallest(){
        if(items.size()==0){
            throw new NoSuchElementException("ArrayHeapMinPQ is empty");
        }else {
            return items.get(0).getItem();
        }
    }

    @Override
    public T removeSmallest(){
        if(items.size()==0){
            throw new NoSuchElementException("ArrayHeapMinPQ is empty");
        }else {
            T result = items.get(0).getItem();
            swap(0,items.size()-1);
            items.remove(items.size()-1);
            sink(0);

            return result;
        }
    }

    @Override
    public int size(){return items.size();}

    @Override
    public void changePriority(T item, double priority){
        if(contains(item)){
            int targetInd=items.indexOf(new PriorityNode(item,0));
            items.get(targetInd).setPriorityNode(priority);
            if(items.get(targetInd).compareTo(items.get(parent(targetInd)))<0){
                swim(targetInd);
            }else if((rightChild(targetInd)!= -1 &&items.get(targetInd).compareTo(items.get(rightChild(targetInd))) > 0 )||
                    (leftChild(targetInd)!=-1 && items.get(targetInd).compareTo(items.get(leftChild(targetInd))) > 0)){
                sink(targetInd);
            }
        }else{
            throw new NoSuchElementException("The item is not in the ArrayHeapMinPQ");
        }
    }

    //set Node property
    private class PriorityNode implements Comparable<PriorityNode>{
        private T item;
        private double priority;

        PriorityNode(T e, double p){
            this.item = e;
            this.priority = p;
        }

        T getItem(){ return item;}

        double getPriority(){ return priority;}

        void setPriorityNode(double p){ this.priority = p;}

        @Override
        public int compareTo(PriorityNode other){
            if(other == null){
                return -1;
            }else{
                return Double.compare(this.getPriority(), other.getPriority());
            }
        }

        @Override
        public boolean equals(Object o){
            if(o == null ||o.getClass()!= this.getClass()){
                return false;
            }else {
                return ((PriorityNode) o).getItem().equals(this.getItem());
            }
        }

        @Override
        public String toString(){
            String result="";
            return result.concat("(").concat(item.toString()).concat(",").concat(Double.toString(priority)).concat(")");
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
