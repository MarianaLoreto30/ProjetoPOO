package Projeto;

import java.io.Serializable;
import java.util.Date;

public abstract class Task implements Serializable {

    private String name;
    private Date startDate;
    private Date endDate;
    private int duration;
    private int conclusionState;
    private Person responsible;
    private double effortRate;
    private int index;

    public Task(){
        name="Unknown";
        conclusionState=0;
    }

    public Task(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible,double effortRate, int index) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.conclusionState = conclusionState;
        this.responsible = responsible;
        this.effortRate = effortRate;
        this.index=index;
    }

    //abstract boolean checkOverload();

    public void addPersonToTask(Person person) {
        setResponsible(person);
    }

    // Getters and Setters

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getConclusionState() {
        return conclusionState;
    }

    public void setConclusionState(int conclusionState) {
        this.conclusionState = conclusionState;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public double  getEffortRate() {
        return effortRate;
    }

    public void setEffortRate(double  effortRate) {
        this.effortRate = effortRate;
    }

    @Override
    public String toString() {
        return "Task." + index + " - " + effortRate + " name: " + name + ", responsible: " + responsible.getName() + ", startDate:" + startDate+ ", endDate: " + endDate +
                ", duration: " + duration + ", conclusionState: " + conclusionState + '%';
    }
}


