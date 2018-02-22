package simple.java.spring.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Role {

    private final static String SEQ_GENERATOR_NAME = "role_seq_gen";

    @Id
    @GeneratedValue(generator = SEQ_GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQ_GENERATOR_NAME, sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name="role_id")
    private Long id;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}