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
    private int index;

    public Task(){
        name="Unknown";
        conclusionState=0;
    }

    /**
     * Task's constructor
     * @param name task's name
     * @param startDate start date
     * @param endDate end date
     * @param duration estimated duration in days
     * @param conclusionState conclusion state
     * @param responsible responsible (person)
     * @param index task's index in the project that belongs
     */
    public Task(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, int index) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.conclusionState = conclusionState;
        this.responsible = responsible;
        this.index=index;
    }

    abstract double returnEffortRate();

    /**
     * add a responsible to the task
     * @param person Person
     */
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

    @Override
    public String toString() {
        return index + " name: " + name + ", responsible: " + responsible.getName() + ", startDate:" + startDate+ ", endDate: " + endDate +
                ", duration: " + duration + ", conclusionState: " + conclusionState + '%';
    }
}


