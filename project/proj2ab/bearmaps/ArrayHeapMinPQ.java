package bearmaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    public ArrayHeapMinPQ(){ items = new ArrayList<>();}
    private int parent(int index){ return (index-1)/2;}
    private int leftChild(int index){ return 2*index+1;}
    private int rightChild(int index){ return 2*index+2;}

    private void swap(int index1, int index2){
        PriorityNode temp1 = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp1);
    }

    private void swim(int index){
        while(items.get(index).compareTo(items.get(parent(index))) < 0){
            swap(index, parent(index));
            index = parent(index);
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

    @Override
    public boolean contains(T item){
        //built-in contains method uses equals method to check if the ArrayList contains certain item
        return items.contains(new PriorityNode(item,0));
    }

    //for testing purpose
    public T getNodeValue(int index){
        return items.get(index).getItem();
    }

    public String printMinPQ(){
        String[] printableArray = new String[items.size()];
        for(int i=0;i<items.size();i++){
            String temp;
            PriorityNode tempNode = items.get(i);
            temp = "(" + tempNode.getItem().toString() +","+ Double.toString(tempNode.getPriority())  + ")";
            printableArray[i] = temp;
        }
        return Arrays.toString(printableArray);
    }

    public void clear(){
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

    //to be finished
    public T removeSmallest(){return items.get(0).getItem(); }
    public int size(){return items.size();}
    public void changePriority(T item, double priority){items.set(0, new PriorityNode(item, priority));}

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
        public int hashCode() {
            return item.hashCode();
        }
    }
}
