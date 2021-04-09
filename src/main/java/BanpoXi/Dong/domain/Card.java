package BanpoXi.Dong.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public class Card {

    @Id
    private Long id;
    private String title;
    private String contents;
    private String category;

    @Column(value = "date_time")
    private LocalDateTime dateTime;
    @Column(value = "is_deleted")
    private int isDeleted;

    protected Card() {
    }

    public Card(String title, String contents, String category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.dateTime = LocalDateTime.now();
        this.isDeleted = 1;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getIsDeleted() {
        return isDeleted;
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
                '}';
    }
}
