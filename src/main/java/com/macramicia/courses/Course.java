package com.macramicia.courses;

import com.macramicia.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.EAGER,
            mappedBy = "courses")
    private Set<User> participants = new HashSet<>();

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

    public int getMaxSpots() {
        return this.maxSpots;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public Set<User> getParticipants() { return this.participants; }

    public int getNumberOfParticipants() {
        if (this.participants.isEmpty()) return 0;
        else return this.participants.size();
    }

    public int getFreeSpots() {
        return this.maxSpots - this.participants.size();
    }

    public boolean isFull() {
        return this.getFreeSpots() == 0;
    }

    public boolean addParticipant(User user) {
        if (isFull()) return false;
        else {
            this.participants.add(user);
            this.setMaxSpots(this.getMaxSpots() - 1);
            return true;
        }
    }

    public boolean removeParticipant(User user) {
        return this.participants.remove(user);
    }

    public boolean isParticipant(User user) {
        return this.participants.contains(user);
    }
}