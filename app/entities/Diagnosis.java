package entities;

import java.util.LinkedList;
import java.util.List;

public class Diagnosis {

    private int patientId;
    private LeucemiaType type;
    private int symptomIndex;
    private float riskPercentage;
    private int riskFactorIndex;
    private RiskProbability riskFactor;

    public Diagnosis(){
        this.type = null;
        this.symptomIndex = 0;
        this.riskFactorIndex = 0;
        this.riskFactor = RiskProbability.Baja;
    }

    public Diagnosis(LeucemiaType type) {
        this();
        this.type = type;
    }

    //Para establecer el porcentaje de probabilidad de padecer la enfermedad basado en sintomatología
    public void incrementSymptomIndex(int score){
        symptomIndex = symptomIndex + score;
        calculatePercentageProbability();
    }
    private void calculatePercentageProbability(){
        int maxIndex = 0;
        switch (type){
            // cambiar valores
            case MC: maxIndex = 255; break;
            case MA: maxIndex = 267; break;
            case LC: maxIndex = 293; break;
            case LA: maxIndex = 506; break;
        }
        riskPercentage = ((float) symptomIndex/maxIndex)*100;
    }

    //Para estblecer el riesgo de padecer la enfermedad basado en su etiología
    public void incrementRiskFactorIndex(int score){
        riskFactorIndex = riskFactorIndex + score;
        stablishRiskFactor();
    }
    private void stablishRiskFactor(){
        if(riskFactorIndex>8) {
            riskFactor = RiskProbability.Alta;
        } else if(riskFactorIndex>5) {
            riskFactor = RiskProbability.Media;
        } else {
            riskFactor = RiskProbability.Baja;
        }
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LeucemiaType getType() {
        return type;
    }

    public void setType(LeucemiaType type) {
        this.type = type;
    }


    public int getSymptomIndex() {
        return symptomIndex;
    }

    public void setSymptomIndex(int symptomIndex) {
        this.symptomIndex = symptomIndex;
    }

    public float getRiskPercentage() {
        return riskPercentage;
    }

    public void setRiskPercentage(float riskPercentage) {
        this.riskPercentage = riskPercentage;
    }

    public int getRiskFactorIndex() {
        return riskFactorIndex;
    }

    public void setRiskFactorIndex(int riskFactorIndex) {
        this.riskFactorIndex = riskFactorIndex;
    }

    public RiskProbability getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(RiskProbability riskFactor) {
        this.riskFactor = riskFactor;
    }

    public String getLeucemiaTypeString(LeucemiaType type){
        
        switch(this.type){
            case MC: return "Leucemia Mieloide Crónica";
            case MA: return "Leucemia Mieloide Aguda";
            case LC: return "Leucemia Linfocítica Crónica";
            case LA: return "Leucemia Linfocítica Aguda";
        }
        return "";
    }

    @Override
    public String toString() {
        return "\n   Riesgo de padecer " + getLeucemiaTypeString(type) + ": "
                + "\n    Probabilidad basada en su etiología: " + riskFactor
                + "\n    Porcentaje de sintomatología clínica: " + riskPercentage + "%";
        //RIESGO ETIOLÓGICO DEL PACIENTE: alto/medio/bajo
        //PROBABILIDAD BASADA EN SINTOMATOLOGÍA CLÍNICA: x%
    }
}
