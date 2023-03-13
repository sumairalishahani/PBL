package PBL;

public class LinkedQueue {
    private static class Node{
        earthQuakeRecord data;
        private Node next=this;
        private Node pre=this;
        public Node(earthQuakeRecord data){this.data=data;}
        public Node(earthQuakeRecord data, Node pre, Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
    }
    private int size=0;
    private final Node head=new Node(null);
    public void add(earthQuakeRecord data) {
        head.pre.next=new Node(data,head.pre,head);
        head.pre=head.pre.next;
        size++;
    }

    public earthQuakeRecord remove() {
        if(isEmpty())throw new IllegalArgumentException("Queue is empty!");
        earthQuakeRecord temp=head.next.data;
        head.next=head.next.next;
        size--;
        return temp;
    }
    public earthQuakeRecord first() {
        if(isEmpty())throw new IllegalArgumentException("Queue is empty!");
        return head.next.data;
    }

    public int size() {
        return size;
    }
    public void print(){
        for(Node n=head.next;n!=head;n=n.next) System.out.println(n.data);
    }
    public LinkedQueue copy(){
        LinkedQueue queue=new LinkedQueue();
        for(Node n=head.next;n!=head;n=n.next)queue.add(n.data);
        return queue;
    }
    public boolean isEmpty(){
        return size==0;
    }
}