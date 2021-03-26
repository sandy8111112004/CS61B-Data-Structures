package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    private Integer size;
    private HashMap<T,Integer> itemMapping = new HashMap<>();
    public ArrayHeapMinPQ(){
        items = new ArrayList<>();
        size = 0;
    }
    private int parent(int index){
        if((index-1)/2 <0){
            return -1;
        }else {
            return (index - 1) / 2;
        }
    }
    private int leftChild(int index){
        if(2*index+1 <size) {
            return 2 * index + 1;
        }else{
            return -1;
        }
    }
    private int rightChild(int index){
        if(2*index+2 < size) {
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
        if(size==0){
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

    /**
     * Add PriorityNode to the MinPQ
     * May Assume input item will never be null
     * @param item      the given value of the node
     * @param priority  the given priority of the node
     */
    @Override
    public void add(T item, double priority){
        if(contains(item)){
            throw new IllegalArgumentException("The ArrayHeapMinPQ contains this item already");
        }else {
            items.add(new PriorityNode(item, priority));
            itemMapping.put(item, size);
            size = size+1;
            swim(size - 1);
        }
    }

    /**
     * Check if the item is already in the MinPQ or not
     * @param item  the given item to be checked
     * @return      return true if the MinPQ contains the item
     */
    @Override
    public boolean contains(T item){
        if(size ==0){
            return false;
        }
        return itemMapping.containsKey(item);
    }

    //for testing purpose only
    String printableMinPQ(){
        return items.toString();
    }

    //for testing purpose only
    void clear(){
        items.clear();
        itemMapping.clear();
        size = 0;
    }

    /**
     * get the smallest node and return the item value
     * @return  return the value(item) of the smallest priority node
     */
    @Override
    public T getSmallest(){
        if(size==0){
            throw new NoSuchElementException("ArrayHeapMinPQ is empty");
        }else {
            return items.get(0).getItem();
        }
    }

    /**
     * remove the smallest node and return the value(item)
     * @return  return the value(item) of the smallest priority node
     */
    @Override
    public T removeSmallest(){
        if(size==0){
            throw new NoSuchElementException("ArrayHeapMinPQ is empty");
        }else {
            T result = items.get(0).getItem();
            swap(0,size-1);
            items.remove(size-1);
            size = size-1;
            sink(0);
            return result;
        }
    }

    /**
     * return the size of the MinPQ
     * @return  return the integer type of the size
     */
    @Override
    public int size(){return size;}

    /**
     * change the priority of the node that's in the MinPQ
     * @param item      the value whose priority is going to be change
     * @param priority  the new priority of the node
     */
    @Override
    public void changePriority(T item, double priority){
        if(contains(item)){
            int targetInd=itemMapping.get(item);
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
