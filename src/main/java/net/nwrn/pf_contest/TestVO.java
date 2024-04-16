package net.nwrn.pf_contest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="test")
public class TestVO {

    @Id
    @Column(name="test_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="test_name")
    private String name;

    @Column(name="test_age")
    private Integer age;
}
