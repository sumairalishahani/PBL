package PBL;

import java.util.Queue;
import java.util.Scanner;

public class Answers {
    public Answers(){
        new Main();
    }
    //this method will return two-dimensional String array containing name of country and its average earth quack of each year
    public String[][] solution1(){
        //calling array of stack of each country
        LinkedStack[] countries=new Main().getCountryStack();
        //for storing country name and its average
        String[][] quakes=new String[52][2];
        //index of each country in quacks array is handled by index variable
        int index=0;
        double avgMax=0;
        String country="";//for storing name of country having max average
        int countYears=0;//for counting total no of years in which earth quack came for each country
        int count=0;//for comparison
        int counter=1965;//for comparison
        int a=1;//to ensure that else runs only once
        for(int i=1965;i<=2016;i++){
            //taking copy of every stack one by one so that pop method can be called
            LinkedStack stack=countries[count++].copy();
            //size of selected stack
            int x=stack.getSize();
            //getting data from selected stack
            for(int y=0;y<x;y++){
                //splitting date into columns so that year can be separated
                String[] date=stack.pop().getDate().split("/");
                //converting string year into int year
                int year=Integer.parseInt(date[2]);
                if(a==2){
                    if(year!=counter){
                        countYears++;
                        counter=year;
                    }
                }
                else {
                    counter=year;
                    countYears++;
                    a++;
                }
            }
            double average=(double)x/countYears;
            //choosing max average and assigning it to tha maxAverage variable
            if(average>avgMax){
                avgMax=average;
                country=stack.getStackName();
            }
            quakes[index][0]="Name:  "+stack.getStackName();
            quakes[index++][1]="Average: "+average;
            countYears=0;
            a=1;
        }
        System.out.println("Country having maximum average of earth quack in each year is "+country+" and maximum average is: "+avgMax);
        return quakes;
    }
    public LinkedQueue solution2(){
        LinkedQueue queue=new Main().getEarthQuackQueue().copy();
        LinkedQueue queue1=new LinkedQueue();
        for(int i=1965;i<2016;i++){
            if(i>=2005)queue1.add(queue.remove());
            else queue.remove();
        }
        return queue1;
    }
    //this method will get country name and will return 5 recent earth quack information
    public LinkedStack solution3(String countryName){
        //declaring stack for making copy of actual stack so that pop method can be called
        LinkedStack stack=new LinkedStack(countryName);
        //for matching country name with stack and then assign copy of matched stack to stack
        for(LinkedStack s:new Main().getCountryStack()){
            if(countryName.equals(s.getStackName())){
                stack=s.copy();
                break;
            }
        }
        //for storing  5 recent earth quacks declaring stack1
        LinkedStack stack1=new LinkedStack(countryName);
        //storing earth quacks into array so that can be stored into stack1 as directly pop method will store in reverse order
        earthQuakeRecord[] quakes=new earthQuakeRecord[5];
        for(int i=0;i<5;i++)quakes[i]=stack.pop();
        for(int j=quakes.length-1;j>=0;j--)stack1.push(quakes[j]);
        return stack1;
    }
    public LinkedList solution4(){
        LinkedList list=new Main().getRecentCountryWiseQuacksList().copy();
        LinkedList list1=new LinkedList();
        int size= list.getSize();
        for(int i=0;i<size;i++){
            earthQuakeRecord quake=list.delete();
            if(quake.getMagnitude()>6)list1.insert(quake);
        }
        list1.reverse();
        return list1;
    }

    public static void main(String[] args) {
        Answers A = new Answers();

        // Question 1:
//        String[][] results = A.solution1();
//
//        for(int i = 0; i < results.length; i++){
//            System.out.println(results[i][0] + " " + results[i][1]);
//        }
        // Question2

//        LinkedQueue lq=A.solution2();
//        int j=lq.size();
//        for (int i=0;i<j;i++){
//            lq.print();
//        }
         // Question 3
//        LinkedStack ls=A.solution3("Japan");
//        ls.display();
        // Question 4
        LinkedList list=A.solution4();
        int k=list.getSize();
        for(int i=0;i<k;i++) System.out.println(list.delete());






    }
}
