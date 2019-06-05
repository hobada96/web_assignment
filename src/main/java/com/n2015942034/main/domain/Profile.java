package com.n2015942034.main.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Profile implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private String network;

    @Column
    private String userName;

    @Column
    private String url;

    @Column
    private LocalDateTime createDat;

    @Column
    private LocalDateTime updateDat;

    @Builder
    public Profile(String network, String userName, String url, LocalDateTime createDat, LocalDateTime updateDat) {
        this.network = network;
        this.userName = userName;
        this.url = url;
        this.createDat = createDat;
        this.updateDat = updateDat;
    }
    public void setCreateDatNow(){
        this.createDat = LocalDateTime.now();
        this.updateDat = LocalDateTime.now();
    }

    public void update(Profile profile) {
        this.network = profile.network;
        this.userName = profile.userName;
        this.url = profile.url;
        this.updateDat = LocalDateTime.now();
    }
}
