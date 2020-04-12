public class ArrayDeque<Item>{
    private int size;
    private Item[] items;
    private int RFactor = 2;
    private double MinUsageRatio = 0.25;

    private void resize(int capacity, int desPos, int sourcePos){
        if(capacity==0){
            capacity = RFactor;
        }
        Item[] temp = (Item[]) new Object[capacity];
        System.arraycopy(items, sourcePos, temp, desPos, size);
        items = temp;
    }


    public ArrayDeque(){
        items = (Item[]) new Object[0];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (Item[]) new Object[0];
        size = 0;
        System.arraycopy(other, 0, items, 0, other.size);
    }

    public void addFirst(Item x){
        if(size == items.length){
            resize(size * RFactor, 1, 0);
        }
        items[0] = x;
        size += 1;
    }

    public void addLast(Item x){
        if(size == items.length){
            resize(size * RFactor, 0,0);
        }
        items[size] = x;
        size += 1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }


    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(items[i].toString() + " ");
        }
        System.out.println();
    }

    public Item removeFirst(){
        if (size == 0){
            return null;
        }else{
            Item temp = items[0];
            items[0] = null;
            size -= 1;
            if((size/items.length)< MinUsageRatio){
                resize(items.length/2,0,1);
            }else{
                resize(items.length,0,1);
            }
            return temp;
        }
    }

    public Item removeLast(){
        if(size == 0){
            return null;
        }else {
            Item temp = items[size-1];
            items[size-1] = null;
            size -= 1;
            if((size/items.length) < MinUsageRatio){
                resize(items.length/2,0,0);
            }
            return temp;
        }
    }

    public Item get(int index){
        if(!isEmpty() && (index < size) && (index >=0)){
            return items[index];
        }else{
            return null;
        }
    }


}