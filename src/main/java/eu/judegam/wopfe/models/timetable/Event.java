package eu.judegam.wopfe.models.timetable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String place;
    private String teacher;
    @ManyToOne
    @JoinColumn(name = "timetable_id", nullable = false)
    @JsonIgnore
    private Timetable timetable;
    @Transient
    @JsonIgnore
    private Long timetableId;

    public Event() {
    }

    public Event(String name, String place, String teacher, Timetable timetable) {
        this.name = name;
        this.place = place;
        this.teacher = teacher;
        this.timetable = timetable;
    }

    public Event(String name, String place, String teacher, Long timetableId) {
        this.name = name;
        this.place = place;
        this.teacher = teacher;
        this.timetableId = timetableId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Long getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Long timetableId) {
        this.timetableId = timetableId;
    }
}
