package PBL;

import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

import java.io.*;
import java.util.Scanner;


public class Main {
     double[] magnitude=new double[1104];
    //country array contains total list of countries on the basis of earth quack in each year
     String[] country=new String[1104];
    //city array contains name of cities in which earth quack occurred each year
     String[] city=new String[1104];
    //year array stores list of years for every single occasion
     int[] year=new int[1104];
    //date of every occasion
     String[] date=new String[1104];
    //type of every earth quack occurred
      String[] earthQuackType=new String[1104];
    //totalDifferentCountries array is for total different countries from 1965 to 2016
    /*
    First I write a code to print total different countries and added a counter variable
    which counted total number of different countries to be 52
    Algo: using for each loop and comparing each country whether it has been stored in the new array or
    not if already present then continue that iteration elso store that country in the new array
     */
    private final String[] differentCountries=new String[52];
    //array of linked list to store the linked list of each year
    private final LinkedList[] yearlyLinked_List=new LinkedList[52];
    //a queue  for storing the biggest earth quack of each year
    private final LinkedQueue earthQuackQueue=new LinkedQueue();
    // linked list for storing the recent earth quack of each country with most recent on top
    private final LinkedList recentCountryWiseQuacksList=new LinkedList();
    //array of stacks for storing stack of every country
    private final LinkedStack[] countryStack=new LinkedStack[52];
    //making constructor to call all the important methods to store data in local variables
    public Main(){
        readingData_CSV();
        insertCountry_City();
        insertIntoCollections();
        addIntoQueue();
        DifferentCountries();
        //initializing stack of each country
        for(int i=0;i<countryStack.length;i++)countryStack[i]=new LinkedStack(differentCountries[i]);
        //Storing countries information in their corresponding stacks
        for (int i=0;i< country.length;i++)
            for (LinkedStack myLinkedStack : countryStack)
                if (country[i].equals(myLinkedStack.getStackName()))
                    myLinkedStack.push(new earthQuakeRecord(earthQuackType[i], magnitude[i], date[i]));
        recentCountryQuacksList();
    }
    public void readingData_CSV(){
        File file=new File("src/PBL/Valid countries and cities.csv");
        try {
            int i=0;
            //reading from csv file  line by line
            Scanner scan=new Scanner(file);
            //reading the first line and not needed because it columns names
            scan.nextLine();
            while (scan.hasNextLine()){
                //converting each line of Excel into columns
                String[] s=scan.nextLine().split(",");

                /* once i read the data from csv file we know  that there is required year
                 and we can found year from data column for that reason first we  convert the
                 data type of string array to char array so i can get year  from date column which is at
                 index 2
                 */
                char[] s1=s[2].toCharArray();
                StringBuilder sb=new StringBuilder();
                for(int j=s1.length-4;j<s1.length;j++)sb.append(s1[j]);
                //putting each year at corresponding index into year array
                year[i]= Integer.parseInt(sb+"");
                date[i]=s[2];
                magnitude[i]=Double.parseDouble(s[10]);
                earthQuackType[i]=s[6];
                i++;
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertCountry_City(){



       /*
        in this method i read the CSV file and stored  latitude  and longitude in the arrays
        double[] latitude=new double[1104];
        double[] longitude = new double[1104];

            As there is large data of than 1104 so to get every country and city
            it took a lot of time in executing the program, so I did this for one time
            and stored the only important columns which required in program in txt file then work
            on that txt file .

            NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI();
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("C:\Users\Sumair Ali\PBL\src\PBL\Countries_Cities.txt));

            for(int i=1;i<longitude.length;i++) {
                country[i]=nominatim1.getAdress(latitude[i],(longitude[i])).getCountry();
                city[i]=nominatim1.getAdress(latitude[i],longitude[i]).getCity();
                writer.write(country[i]+" : "+city[i]+"\n");
                System.out.println(country[i]+" : "+city[i]);
            }
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

         */
        File f=new File("C:\\Users\\Sumair Ali\\PBL\\src\\PBL\\County.txt");
        File f1=new File("C:\\Users\\Sumair Ali\\PBL\\src\\PBL\\Countries_Cities.txt");
        try {

            Scanner scan = new Scanner(f);
            Scanner scanner=new Scanner(f1);
            int i=0;
            while (scan.hasNextLine()){
                String s=scan.nextLine();
                String[] s1=scanner.nextLine().split(":");
                country[i]=s;
                city[i++]=s1[1];
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertIntoCollections() {
        int i=0;
        int j=1;
        for(int k=0;k<52;k++) {
            yearlyLinked_List[k]=new LinkedList();
            while (year[i] == year[j]) {
                yearlyLinked_List[k].insert(new earthQuakeRecord(country[i],city[i],magnitude[i],date[i]));
                i++;
                j++;
                if(j==1104)break;
            }
            yearlyLinked_List[k].insert(new earthQuakeRecord(country[i],city[i],magnitude[i],date[i]));
            if(i==1104)break;
            i=j;
            j++;
        }
    }
    public void addIntoQueue(){
        for (LinkedList myLinkedList : yearlyLinked_List) earthQuackQueue.add(myLinkedList.getMax());
    }
    public void DifferentCountries(){
        int count=0;
        label:      for(int i=0;i<country.length;i++){
            String cy=country[i];
            for(int j=i-1;j>=0;j--)if(cy.equals(country[j]))continue label;
            differentCountries[count++]=cy;
        }
    }
    public void recentCountryQuacksList( ) {
        LinkedStack[] arr=countryStack;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                String[] s=arr[j].peek().getDate().split("/");
                int day1=Integer.parseInt(s[1]);
                int month1=Integer.parseInt(s[0]);
                int year1=Integer.parseInt(s[2]);
                s=arr[j+1].peek().getDate().split("/");
                int day2=Integer.parseInt(s[1]);
                int month2=Integer.parseInt(s[0]);
                int year2=Integer.parseInt(s[2]);
                if(year2!=year1){
                    if(year1>year2){
                        LinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
                else if(month2!=month1){
                    if(month1>month2){
                        LinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
                else {
                    if(day1>day2){
                        LinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }

            }

        }
        for(LinkedStack linkedStack:arr)
            recentCountryWiseQuacksList.insert(new earthQuakeRecord(linkedStack.peek().getMagnitude(),linkedStack.peek().getDate(),linkedStack.getStackName()));
    }

    public double[] getMagnitude() {
        return magnitude;
    }

    public String[] getCountry() {
        return country;
    }

    public String[] getCity() {
        return city;
    }

    public int[] getYear() {
        return year;
    }

    public String[] getDate() {
        return date;
    }

    public String[] getEarthQuackType() {
        return earthQuackType;
    }

    public String[] getTotalDifferentCountries() {
        return differentCountries;
    }

    public LinkedList[] getYearWiseLL() {
        return yearlyLinked_List;
    }

    public LinkedQueue getEarthQuackQueue() {
        return earthQuackQueue;
    }

    public LinkedList getRecentCountryWiseQuacksList() {
        return recentCountryWiseQuacksList;
    }

    public LinkedStack[] getCountryStack() {
        return countryStack;
    }

}

