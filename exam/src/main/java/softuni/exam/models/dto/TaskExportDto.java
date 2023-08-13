package softuni.exam.models.dto;

public class TaskExportDto {

    private String bookTitle;
    private String author;
    private String dateBorrowed;
    private LibraryUserInfoDto libraryUser;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public LibraryUserInfoDto getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUserInfoDto libraryUser) {
        this.libraryUser = libraryUser;
    }

    @Override
    public String toString() {
        String EXPORT_FORMAT = "Book title: %s%n*Book author: %s%n**Date borrowed: %s%n***Borrowed by: %s %s";

        return String.format(EXPORT_FORMAT,
                getBookTitle(),
                getAuthor(),
                getDateBorrowed(),
                getLibraryUser().getFirstName(),
                getLibraryUser().getLastName());
    }
}

