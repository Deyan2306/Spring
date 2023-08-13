package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class SingleBorrowingRecordHolder {

    @XmlElement(name = "borrow_date")
    @NotNull
    private String borrowDate;

    @XmlElement(name = "return_date")
    @NotNull
    private String returnDate;

    @XmlElement
    @NotNull
    private BookBase book;

    @XmlElement
    @NotNull
    private MemberBase member;

    @XmlElement
    @Size(min = 3, max = 100)
    private String remarks;

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookBase getBook() {
        return book;
    }

    public void setBook(BookBase book) {
        this.book = book;
    }

    public MemberBase getMember() {
        return member;
    }

    public void setMember(MemberBase member) {
        this.member = member;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
