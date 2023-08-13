package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.BookGenre;
import softuni.exam.models.entity.BorrowingRecord;

import java.sql.Date;
import java.util.List;
@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    //List<BorrowingRecord> findAllByBookBookGenreAndBorrowDateBeforeOrderByBorrowDateDesc(BookGenre bookGenre, Date borrowDate);

    //List<BorrowingRecord> findAllByBorrowDateBeforeEventAndBookBookGenreOrderByBorrowDateDesc(Date event, BookGenre bookGenre);

    List<BorrowingRecord> findAllByBorrowDateBeforeOrderByBorrowDateDesc(Date before);
}
