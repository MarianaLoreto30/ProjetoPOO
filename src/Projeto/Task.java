package Projeto;

import java.util.Date;

public class Task {

    private Date startDate;
    private Date endDate;
    private int duration;
    private int conclusionState;
    private Person responsible;
    private float effortRate;

    public Task(){

    }

    public Task(Date startDate, Date endDate, int duration, int conclusionState, Person responsible, float effortRate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.conclusionState = conclusionState;
        this.responsible = responsible;
        this.effortRate = effortRate;
    }


    private boolean checkOverload(){
        return true;
    }

    // Getters and Setters
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

    public float getEffortRate() {
        return effortRate;
    }

    public void setEffortRate(float effortRate) {
        this.effortRate = effortRate;
    }
}
