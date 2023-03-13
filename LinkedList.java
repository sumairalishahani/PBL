package PBL;

public class LinkedList {
    private static class Node{
        earthQuakeRecord data;
        Node next;
        public Node(earthQuakeRecord data){
            this.data=data;
        }
    }
    private int size;
    private Node head;
    public void insert(earthQuakeRecord data){
        if(head==null){
            head=new Node(data);
            size++;
            return;
        }
        Node n=new Node(data);
        n.next=head;
        head=n;
        size++;
    }
    public Node getHead(){return head;}
    public void traverse(){
        for(Node n=head;n!=null;n=n.next) System.out.println(n.data);    }
    public earthQuakeRecord delete(){
        if(head==null)throw new IllegalStateException("List is exception");
        Node n=head;
        head=head.next;
        size--;
        return n.data;

    }
    public LinkedList copy(){
        LinkedList list=new LinkedList();
        for(Node n=head;n!=null;n=n.next)list.insert(n.data);
        return list;
    }
    public void reverse(){
        //creating array to store data of linked list
        earthQuakeRecord[] quakes =new earthQuakeRecord[this.getSize()];
        int i=0;
        for(Node n=head;n!=null;n=n.next)quakes[i++]=n.data;
        //now inserting values into linked list
        for(int k=quakes.length-1;k>=0;k--)this.insert(quakes[k]);
    }
    public earthQuakeRecord getMax(){
        Node max=head;
        for(Node n=head.next;n!=null;n=n.next)if(max.data.getMagnitude()<n.data.getMagnitude())max=n;
        return max.data;
    }

    public int getSize() {
        return size;

    }

}
