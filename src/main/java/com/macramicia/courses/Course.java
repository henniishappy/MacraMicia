package com.macramicia.courses;

import com.macramicia.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    private String venue;

    private int maxSpots;

    @OneToMany
    private List<User> user;

    public Course(String title, String description, LocalDateTime date, String venue, int maxSpots) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.maxSpots = maxSpots;
    }

    public Course() { }


    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public int getMaxSpots() {
        return this.maxSpots;
    }

    /*
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

    public Boolean isFull() {
        return getTakenSpots() >= maxSpots;
    }

    public void updateMaxParticipants(int maxParticipants) {
        if (getTakenSpots() > maxParticipants)
            throw new IllegalArgumentException("Cannot reduce number of " +
                    "particpants. More than " +  maxParticipants + " people" +
                    " have already signed up for this course.");
        User[] updatedP = new Participants[maxParticipants];
        for (int i = 0; i < getTakenSpots(); i++) {
            updatedP[i] = this.participants[i];
        }
        this.participants = updatedP;
    }

    public void addParticipant(User newP) {
        if (isFull()) throw new RuntimeException("This course is already full");
        this.participants[getNumberOfParticipants()] = newP;
    }

    public void removeParticipant(User cancellation) {
        if (!isParticipant(cancellation))
            throw new IllegalArgumentException("This User is not a participant of this course");
        List<User> userList = Array.asList(this.participants);
        userList.remove(cancellation);
        this.participants
    }

    private boolean isParticipant(User person) {
        List<User> userList = Array.asList(this.participants);
        return userList.contains(person);
        this.participants = userList.toArray(this.participants);
    }
     */
}