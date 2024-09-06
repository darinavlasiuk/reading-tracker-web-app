package pja.edu.pl.readingtrackerwebapp.DTOs;

import jakarta.annotation.Nullable;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;

import java.util.Date;

public class User_BookWithoutUserDTO {
    private Integer userid;
    private BookDTO book;
    private Short isRead;
    private Short isWantToRead;
    @Nullable
    private Integer rating;
    @Nullable
    private Date dateRead;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
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
}
