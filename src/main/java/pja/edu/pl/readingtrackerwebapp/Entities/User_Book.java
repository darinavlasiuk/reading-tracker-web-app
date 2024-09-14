package pja.edu.pl.readingtrackerwebapp.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@IdClass(User_Book.User_BookId.class)
public class User_Book {
    @Id
    @ManyToOne
    private MyUser user;
    @Id
    @ManyToOne
    private Book book;
    private Short isRead;
    private Short isWantToRead;
    @Nullable
    private Integer rating;
    @Nullable
    private Date dateRead;
    public User_Book(){

    }
    public User_Book(MyUser user, Book book, Short isRead, Short isWantToRead, Integer rating, Date dateRead) {
        this.user = user;
        this.book = book;
        this.isRead = isRead;
        this.isWantToRead = isWantToRead;
        this.rating = rating;
        this.dateRead = dateRead;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Short getIsRead() {
        return isRead;
    }

    public void setIsRead(Short isRead) {
        this.isRead = isRead;
    }

    public Short getIsWantToRead() {
        return isWantToRead;
    }

    public void setIsWantToRead(Short isWantToRead) {
        this.isWantToRead = isWantToRead;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDateRead() {
        return dateRead;
    }

    public void setDateRead(Date dateRead) {
        this.dateRead = dateRead;
    }

    public static class User_BookId implements Serializable {
        private MyUser user;
        private Book book;

        public User_BookId(MyUser user, Book book) {
            this.user = user;
            this.book = book;
        }
        public User_BookId() {
        }

        public MyUser getUser() {
            return user;
        }

        public void setUser(MyUser user) {
            this.user = user;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User_BookId that = (User_BookId) o;
            return getUser().equals(that.getUser()) && getBook().equals(that.getBook());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUser(), getBook());
        }
    }
}
