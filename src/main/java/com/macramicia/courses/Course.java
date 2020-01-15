package com.macramicia.courses;

import com.macramicia.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    private String venue;

    private int maxSpots;

    @OneToMany
    private List<User> participants;


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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public List<User> getParticipants() { return this.participants; }

    public int getNumberOfParticipants() {
        if (this.participants.isEmpty()) return 0;
        else return this.participants.size();
    }

    public int getFreeSpots() { return this.maxSpots - this.participants.size(); }

    public boolean isFull() {
        if (getFreeSpots() == 0) return true;
        else return false;
    }

    public boolean addParticipant(User participant) {
        if (isFull()) return false;
        else {
            this.participants.add(participant);
            return true;
        }
    }

    public boolean removeParticipant(User cancellation) { return this.participants.remove(cancellation); }

    public boolean isParticipant(User user) { return this.participants.contains(user); }

}