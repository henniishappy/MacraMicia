package com.groupnine.macramicia.courses;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String venue;
    private User[] participants;
    private int maxSpots;

    private static List<Course> courses = new ArrayList<>();

    public Course(Date date) {
        this.title = "Learn Macramee";
        this.description = "Are you interested in macramee but you do not know " +
                "how to do it? Let's create something together. I'll show you " +
                "how to make your own wall hanging or planthanger, explaining " +
                "to you step by step how to knot.";
        this.date = date;
        this.venue = "Friedrichshain";
        this.participants = new User[5];
        this.maxSpots = participants.length;
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

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public int getMaxSpots() {
        return maxSpots;
    }

    public int getTakenSpots() {
        int taken = 0;
        for (int i = 0; i < maxSpots; i++) {
            if(participants[i] != null)
                taken++;
        }
        return taken;
    }

    public int getFreeSpots() {
        System.out.println(maxSpots - getTakenSpots());
        return maxSpots - getTakenSpots();
    }

    public static boolean addCourse(Course c) {
        return courses.add(c);
    }

    public static List getCourses() {
        return courses;
    }

    public Boolean isFull() {
        if (getTakenSpots() >= maxSpots) return true;
        else return false;
    }

    /*

    public void updateMaxParticipants(int maxParticipants) {
        if (getTakenSpots() > maxParticipants)
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

     */
}