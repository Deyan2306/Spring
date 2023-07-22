package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wizards")
public class Wizard extends BaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(name = "note", length = 1000, columnDefinition = "TEXT")
    private String note;

    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(name = "wizard_deposits")
    private List<Deposit> deposits;

}
