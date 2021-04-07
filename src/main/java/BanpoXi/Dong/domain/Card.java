package BanpoXi.Dong.domain;

import java.time.LocalDateTime;

public class Card {
    int id;
    String title;
    String contents;
    String category;
    String dateTime;
    boolean isDeleted;
    int order;

    public Card() {

    }

    public Card(int id, String title, String contents, String category) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.dateTime = LocalDateTime.now().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", category='" + category + '\'' +
                ", dateTime='" + LocalDateTime.now().toString() + '\'' +
                ", isDeleted=" + isDeleted +
                ", order=" + order +
                '}';
    }
}
