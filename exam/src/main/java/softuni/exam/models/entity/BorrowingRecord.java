package softuni.exam.models.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord extends BaseEntity {

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    @Column(nullable = true)
    private String remarks;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book bookId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private LibraryMember memberId;

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public LibraryMember getMemberId() {
        return memberId;
    }

    public void setMemberId(LibraryMember memberId) {
        this.memberId = memberId;
    }
}
