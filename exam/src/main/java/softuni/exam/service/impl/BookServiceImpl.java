package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "src/main/resources/files/json/books.json";

    private BookRepository bookRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ValidationUtils validationUtils, ModelMapper modelMapper, Gson gson) {
        this.bookRepository = bookRepository;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        List<BookImportDto> books = Arrays.stream(this.gson.fromJson(readBooksFromFile(), BookImportDto[].class))
                .collect(Collectors.toList());

        for (BookImportDto currentBook : books) {

            // The current book is not valid
            if (this.bookRepository.findFirstByTitle(currentBook.getTitle()).isPresent() // The book is in the repo
                    || !this.validationUtils.isValid(currentBook)) {

                stringBuilder.append("Invalid book").append(System.lineSeparator());

                continue;
            }

            Book toSave = this.modelMapper.map(currentBook, Book.class);
            bookRepository.save(toSave);

            stringBuilder.append(String.format("Successfully imported book %s - %s%n",
                    toSave.getAuthor(),
                    toSave.getTitle()));
        }

        return stringBuilder.toString().trim();
    }
}
