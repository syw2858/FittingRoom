package net.nwrn.pf_contest.clothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "top")
public class TopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "top_id")
    private Long topId;

//    @Column(name="top_url")
//    private String topUrl;

    @Column(name="top_register_dt", nullable = false)
    private Timestamp topRegisterDt;

    @PrePersist
    protected void onCreate() {
        topRegisterDt = new Timestamp(System.currentTimeMillis());
    }
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "category")
//    private Category category;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "color")
//    private Color color;
}
