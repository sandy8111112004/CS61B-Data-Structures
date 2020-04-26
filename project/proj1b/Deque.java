public interface Deque<Item> {
//An interface is implicitly abstract. No need to use abstract keyword
    void addFirst(Item x);
    void addLast(Item x);
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
    default boolean isEmpty(){
        return size()==0;
    }
    // methods in interface are implicitly public and abstract. No need to specify
}
