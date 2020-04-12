public class LinkedListDeque<Item>{
    public class Node{
        public Item item;
        public Node prev;
        public Node next;
        public Node(Item _item, Node _prev, Node _next){
            item = _item;
            prev = _prev;
            next = _next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);    // create an empty list
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(Item x){
        sentinel = new Node(null, null, null);
        size = 1;
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for(int i = 0;i < other.size;i++){
            addLast((Item) other.get(i));
        }
    }

    public void addFirst(Item x){
        Node temp = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    public void addLast(Item x){
        Node temp = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }


    public void printDeque(){
        Node temp = sentinel.next;
        int tempInt = 0;
        while (tempInt < size){
            System.out.print(temp.item.toString() + " ");
            temp = temp.next;
            tempInt +=1;
        }
        System.out.println();
    }

    public Item removeFirst(){
        if (size == 0){
            return null;
        }else{
            Node temp = sentinel.next;
            sentinel.next = sentinel.next.next;
            temp.next.prev = sentinel;
            size -= 1;
            return temp.item;
        }
    }

    public Item removeLast(){
        if(size == 0){
            return null;
        }else{
            Node temp = sentinel.prev;
            temp.prev.next = sentinel;
            sentinel.prev = temp.prev;
            size -= 1;
            return temp.item;
        }
    }

    public Item get(int index){
        if(!isEmpty() && (index < size) && (index >=0)){
            Node temp = sentinel;
            for(int i=0; i<=index ;i++){
                temp = temp.next;
            }
            return temp.item;
        }else{
            return null;
        }
    }

    public Item getRecursive(int index){
        if(!isEmpty() && (index < size) && (index >=0)){
            return getRecursiveHelper(sentinel.next, index);
        }else{
            return null;
        }
    }

    private Item getRecursiveHelper(Node start, int index){
        Node temp = start;
        if(index == 0){
            return temp.item;
        }else{
            temp = temp.next;
            return getRecursiveHelper(temp, index-1);
        }
    }

//    public static void main(String[] args) {
//        LinkedListDeque test = new LinkedListDeque();
//        test.addFirst(2);
//        test.addLast(3);
//        //test.printDeque();
//        System.out.println(test.size);
//        System.out.println(test.get(1));
//        System.out.println(test.get(0));
//        System.out.println(test.get(2));
//        System.out.println(test.get(3));
//        System.out.println(test.get(-1));
//        test.printDeque();
//
////        System.out.println("test recursive");
////
////        System.out.println(test.size);
////        System.out.println(test.getRecursive(1));
////        System.out.println(test.getRecursive(0));
////        System.out.println(test.getRecursive(2));
////        System.out.println(test.getRecursive(3));
////        System.out.println(test.getRecursive(-1));
////        test.printDeque();
////        System.out.println(test.removeFirst());
////        test.printDeque();
////        System.out.println(test.size);
////        System.out.println(test.removeLast());
////        test.printDeque();
////        System.out.println(test.size);
//    }

}