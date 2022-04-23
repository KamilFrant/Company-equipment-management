package pl.kfrant.personelmanagement.assignment.dto;

import java.time.LocalDateTime;

public class AssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userID;
    private Long ItemID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getItemID() {
        return ItemID;
    }

    public void setItemID(Long itemID) {
        ItemID = itemID;
    }
}
