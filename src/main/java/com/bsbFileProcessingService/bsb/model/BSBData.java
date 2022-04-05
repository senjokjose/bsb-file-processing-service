package com.bsbFileProcessingService.bsb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "bsb_data")
@Getter
@Setter
public class BSBData {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(unique=true)
    private String bsbNumber;
    private String fiMnemonic;
    private String bsbName;
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private String paymentFlag;
    private Date createDate = new Date();
    @JsonIgnore
    private Long bsbFileTrackingId;


    public BSBData(String bsbNumber, String fiMnemonic, String bsbName, String street, String suburb, String state, String postcode, String paymentFlag, Long bsbFileTrackingId) {
        this.bsbNumber = bsbNumber;
        this.fiMnemonic = fiMnemonic;
        this.bsbName = bsbName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.paymentFlag = paymentFlag;
        this.bsbFileTrackingId = bsbFileTrackingId;
    }

    public BSBData() {

    }
}
