package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordsHolderDto;
import softuni.exam.models.dto.SingleBorrowingRecordHolder;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private final static String BORROWING_RECORD_FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private LibraryMemberRepository libraryMemberRepository;

    @Autowired
    public BorrowingRecordsServiceImpl(ValidationUtils validationUtils, ModelMapper modelMapper, XmlParser xmlParser, BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository) {
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
    }


    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(BORROWING_RECORD_FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {

        final StringBuilder stringBuilder = new StringBuilder();

        List<SingleBorrowingRecordHolder> DTORecords =
                this.xmlParser.fromFile(Path.of(BORROWING_RECORD_FILE_PATH).toFile(), BorrowingRecordsHolderDto.class).getRecords();

        for (SingleBorrowingRecordHolder record : DTORecords) {

            final Optional<Book> bookByTitle =
                    this.bookRepository.findFirstByTitle(record.getBook().getTitle());

            final Optional<LibraryMember> memberById =
                    this.libraryMemberRepository.findById(record.getMember().getId());

            // Not valid record
            if (bookByTitle.isPresent() || memberById.isPresent() || !this.validationUtils.isValid(record)) {

                stringBuilder.append("Invalid borrowing record").append(System.lineSeparator());

                continue;
            }

            // If valid
            final BorrowingRecord recordToSave = this.modelMapper.map(record, BorrowingRecord.class);
            this.borrowingRecordRepository.save(recordToSave);

            stringBuilder.append(String.format("Successfully imported borrowing record %s - %s%n",
                    record.getBook().getTitle(),
                    record.getBorrowDate()));
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        return null;
    }
}
