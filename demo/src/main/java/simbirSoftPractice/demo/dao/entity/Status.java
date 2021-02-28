package simbirSoftPractice.demo.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusName name;

    @OneToMany(mappedBy = "status")
    private Set<Item> items;
    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusName getName() {
        return name;
    }

    public void setName(StatusName name) {
        this.name = name;
    }
}
