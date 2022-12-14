package com.nikolastrapp.coffeestand.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="title", nullable = false, length = 80)
    private String title;

    @Column(name = "content", nullable = false, length = 255)
    private String content;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Brazil/East")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postdate", nullable = false)
    private Date postDate;

    @Column(name = "image", nullable = true, length = 64)
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(Long id, String title, String content, Date postDate, String image, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.image = image;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}