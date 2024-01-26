package entities;

public class Symptom {

    private int patientId;
    private String name;
    private int score;
    private boolean treated;

    public Symptom(){
        this.name="";
        this.score=0;
    }
    public Symptom(String name, int score) {
        this();
        this.name = name;
        this.score = score;
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isTreated() {
        return treated;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    @Override
    public String toString() {
        return "\n   " + name + " (+" + score + ")";
    }
}
