package PBL;

public class earthQuakeRecord {
    private double magnitude;
    private String country,city,type,date;


    public earthQuakeRecord(String country,String city,double magnitude,String date){
        this.country=country;
        this.city=city;
        this.magnitude=magnitude;
        this.date=date;
    }
    public earthQuakeRecord(double magnitude,String date,String country){
        this.country=country;
        this.date=date;
        this.magnitude=magnitude;

    }
    public earthQuakeRecord(String type, double magnitude,String date){
        this.date=date;
        this.type=type;
        this.magnitude=magnitude;
    }
    public earthQuakeRecord(){}

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }


    public String toString(){
        return "Date: "+date+" , Magnitude: "+magnitude+" , Country: "+country;
    }
}
