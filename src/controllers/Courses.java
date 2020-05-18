package controllers;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Courses implements Serializable
{
    private String coursename;
    private String coursefee;
    private String startdate;
    private int duration;
    
    private Faculty coordinator;
    //private Faculty[] faculty = new Faculty[5];
    private ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    
    private ArrayList<Participants> participant = new ArrayList<Participants>();
    //private Participants[] participant = new Participants[5];
    
    Courses(){
        coursename = null;
        coursefee = null;
        startdate = null;
        duration = 0;
        
        coordinator = new Faculty();
        /*for (int i=0;i<5;i++){
            faculty[i] = new Faculty();
            participant[i] = new Participants();
        }*/
    }
    
    public void setcoursename(String name){
        this.coursename = name;
    }
    public void setcoursefee(String fee){
        this.coursefee = fee;
    }
    public void setstartdate(String date){
        this.startdate = date;
    }
    public void setduration(int duration){
        this.duration = duration;
    }
    public void setcoordinator(Faculty coord){
        this.coordinator = coord;
    }
    
    public String getcoursename(){
        return this.coursename;
    }
    public String getcoursefee(){
        return this.coursefee;
    }
    public String getstartdate(){
        return this.startdate;
    }
    public int getduration(){
        return this.duration;
    }
    public Faculty getcoordinator(){
        return this.coordinator;
    }
    public ArrayList<Faculty> getfaclist(){
        return this.faculty;//.get(i);//this.faculty[i];
    }
    public ArrayList<Participants> getparticipant(){
        return this.participant;//.get(i);
    }
    
}