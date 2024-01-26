package entities;

import java.util.LinkedList;
import java.util.List;

public class Patient {

    private int id;
    private String name;
    private String surname;
    private int age;
    private Sex sex;
    private List<Diagnosis> diagnosis;
    private List<Symptom> symptoms;
    
    private boolean farmacosQuimio;
    private boolean transplanteMO;
    private boolean razaBlanca;
    private boolean radOQuimicos;
    private boolean trastornosSang;
    private boolean trastornosGeneticos;
    private boolean genFiladelfia;
    private boolean antecedentesFamiliares;
    private boolean hermanosLeucemia;

    public Patient(){
        this.name = "";
        this.surname = "";
        this.age = 0;
        this.sex = Sex.MALE;
        this.farmacosQuimio = false;
        this.transplanteMO = false;
        this.razaBlanca = false;
        this.radOQuimicos = false;
        this.trastornosSang = false;
        this.trastornosGeneticos = false;
        this.genFiladelfia = false;
        this.antecedentesFamiliares = false;
        this.hermanosLeucemia = false;
        createDiagnosis();
    }


    public Patient(String name, String surname, int age, Sex sex) {
        this();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    private void createDiagnosis(){
        diagnosis = new LinkedList<Diagnosis>();
        diagnosis.add(new Diagnosis(LeucemiaType.MC));
        diagnosis.add(new Diagnosis(LeucemiaType.MA));
        diagnosis.add(new Diagnosis(LeucemiaType.LC));
        diagnosis.add(new Diagnosis(LeucemiaType.LA));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        for(Diagnosis d: this.diagnosis){
            d.setPatientId(id);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void addDiagnosis(Diagnosis diagnosis){
        this.diagnosis.add(diagnosis);
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
        for(Symptom s: this.symptoms){
            s.setPatientId(id);
        }
    }

    public boolean isFarmacosQuimio() {
        return farmacosQuimio;
    }

    public void setFarmacosQuimio(boolean farmacosQuimio) {
        this.farmacosQuimio = farmacosQuimio;
    }

    public boolean isTransplanteMO() {
        return transplanteMO;
    }

    public void setTransplanteMO(boolean transplanteMO) {
        this.transplanteMO = transplanteMO;
    }

    public boolean isRazaBlanca() {
        return razaBlanca;
    }

    public void setRazaBlanca(boolean razaBlanca) {
        this.razaBlanca = razaBlanca;
    }

    public boolean isRadOQuimicos() {
        return radOQuimicos;
    }

    public void setRadOQuimicos(boolean radOQuimicos) {
        this.radOQuimicos = radOQuimicos;
    }

    public boolean isTrastornosSang() {
        return trastornosSang;
    }

    public void setTrastornosSang(boolean trastornosSang) {
        this.trastornosSang = trastornosSang;
    }

    public boolean isTrastornosGeneticos() {
        return trastornosGeneticos;
    }

    public void setTrastornosGeneticos(boolean trastornosGeneticos) {
        this.trastornosGeneticos = trastornosGeneticos;
    }

    public boolean isGenFiladelfia() {
        return genFiladelfia;
    }

    public void setGenFiladelfia(boolean genFiladelfia) {
        this.genFiladelfia = genFiladelfia;
    }

    public boolean isAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(boolean antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public boolean isHermanosLeucemia() {
        return hermanosLeucemia;
    }

    public void setHermanosLeucemia(boolean hermanosLeucemia) {
        this.hermanosLeucemia = hermanosLeucemia;
    }

    public String getSexoPaciente(Sex s){
  
        switch(this.sex){
            case MALE: return "Hombre";
            case FEMALE: return "Mujer";
        }
        return "";
    }
    
    @Override
    public String toString() {
        String d = "";
        for(int i=0; i<diagnosis.size(); i++){
            d = d + diagnosis.get(i).toString();
        }
        return "Paciente [" + id + "]: " + name + " " + surname +
                "\n -Edad: " + age +
                "\n -Sexo: " + getSexoPaciente(sex) +
                "\n -Diagnóstico: " + d;
    }
}
