package softuni.exam.models.dto;

import softuni.exam.models.entity.BookGenre;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class BookImportDto {

    @NotNull
    @Size(min = 3, max = 40)
    private String title;

    @NotNull
    @Size(min = 3, max = 40)
    private String author;

    @NotNull
    @Size(min = 5)
    private String description;

    @NotNull
    private Boolean available;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookGenre genre;

    @Positive
    @NotNull
    @DecimalMin(value = "0.00")
    private Double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
