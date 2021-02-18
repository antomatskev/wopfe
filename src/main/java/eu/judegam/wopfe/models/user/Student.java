package eu.judegam.wopfe.models.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Child class
 */
public class Student extends Person{
    private int studentClass;
    private String school;
    private List<Integer> grades = new ArrayList<>();
    private String timetble;

    public Student(){

    }

    public Student(int studentClass, String school, List<Integer> grades, String timetble){
        this.studentClass = studentClass;
        this.school = school;
        this.grades = grades;
        this.timetble = timetble;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public String getTimetble() {
        return timetble;
    }

    public void setTimetble(String timetble) {
        this.timetble = timetble;
    }
}
