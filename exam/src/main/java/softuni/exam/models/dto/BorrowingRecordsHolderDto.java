package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsHolderDto {

    @XmlElement(name = "borrowing_record")
    private List<SingleBorrowingRecordHolder> records;

    public List<SingleBorrowingRecordHolder> getRecords() {
        return records;
    }

    public void setRecords(List<SingleBorrowingRecordHolder> records) {
        this.records = records;
    }
}

