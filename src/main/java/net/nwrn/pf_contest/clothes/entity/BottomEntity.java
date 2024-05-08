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
@Table(name = "bottom")
public class BottomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bottom_id")
    private Long bottomId;

    @Column(name="bottom_url")
    private String bottomUrl;

    @Column(name="bottom_register_dt", nullable = false)
    private Timestamp bottomRegisterDt;

    @PrePersist
    protected void onCreate() {
        bottomRegisterDt = new Timestamp(System.currentTimeMillis());
    }
}
