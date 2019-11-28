package com.groupnine.macramicia.courses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column
    private String title;

    @Column
    private String description;

    private Date date;
    private String venue;
    private User[] participants;

    private static List<Course> courses = new ArrayList<>();

    public Course(Date date) {
        this.title = "Learn Macramee";
        this.description = "Are you interested in macramee but you do not know " +
                "how to do it? Let's create something together. I'll show you " +
                "how to make your won wall hanging or planthanger, explaining " +
                "to you step by step how to knot.";
        this.date = date;
        this.venue = "Friedrichshain";
        this.participants = new User[5];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getMaxParticipants() {
        return this.participants.length;
    }

    public int getNumberOfParticipants() {
        int taken = 0;
        for(int i = 0; i < getMaxParticipants(); i++) {
            if(participants[i] != null)
                taken++;
        }
        return taken;
    }

    public int getFreeSpots() {
        return getMaxParticipants() - getNumberOfParticipants();
    }

    public static boolean addCourse(Course c) {
        return courses.add(c);
    }

    public static List getCourses() {
        return courses;
    }

    public Boolean isFull() {
        if (getNumberOfParticipants() == getMaxParticipants()) return true;
        else return false;
    }

    public void updateMaxParticipants(int maxParticipants) {
        if (getNumberOfParticipants() > maxParticipants)
            throw new IllegalArgumentException("Cannot reduce number of " +
                    "particpants. More than " +  maxParticipants + " people" +
                    " have already signed up for this course.");
        User[] updatedP = new Participants[maxParticipants];
        //TODO copy existing participants

    }

    public void addParticipant(User newP) {
        if (isFull()) throw new RuntimeException("This course is already full");
        this.particpants[getNumberOfParticipants()] = newP;
    }

    public void removeParticipant(User cancellation) {
        //TODO delete participant
    }
}