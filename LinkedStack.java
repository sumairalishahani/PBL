package PBL;


import java.util.EmptyStackException;

public class LinkedStack{
    private  String stackName;

    public LinkedStack(String stackName){
        this.stackName=stackName;
    }
    public LinkedStack (){
    }

    public String getStackName() {
        return stackName;
    }

    private static class Node{
        earthQuakeRecord data;
        Node next;
        Node(earthQuakeRecord data){
            this.data=data;
        }
    }
    private Node head=null;
    private int size=0;
    public boolean isEmpty(){
        return head==null;
    }
    public int getSize(){
        return size;
    }
    public int search(earthQuakeRecord target){
        int count=1;
        for(Node n=head;n!=null;n=n.next){
            if(n.data.equals(target))return count;
            count++;
        }
        return -1;
    }
    public void push(earthQuakeRecord t){
        Node node= new Node(t);
        node.next=head;
        head=node;
        size++;
    }
    public earthQuakeRecord peek(){
        if(isEmpty())throw new EmptyStackException();
        return head.data;}
    public earthQuakeRecord pop(){
        earthQuakeRecord t=head.data;
        head=head.next;
        --size;
        return t;
    }
    public LinkedStack copy(){
        LinkedStack stack=new LinkedStack(this.getStackName());
        earthQuakeRecord[] a=new earthQuakeRecord[this.size];
        int count=0;
        for(Node n=head;n!=null;n=n.next)a[count++]=n.data;
        for(int i=a.length-1;i>=0;i--)stack.push(a[i]);
        return stack;
    }
    public void display(){
        for(Node n=head;n!=null;n=n.next) System.out.println(n.data);
    }
}
