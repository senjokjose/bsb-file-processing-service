package com.bsbFileProcessingService.fileProcessing.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bsb_file_tracking")
@Setter
@Getter
public class BSBFileTracking {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BsbFileType bsbFileType;
    @Column(unique = true)
    private String fileName;
    private Date createDate;
    private String createdBy;

    public BSBFileTracking(BsbFileType bsbFileType, String fileName, Date createDate, String createdBy) {
        this.bsbFileType = bsbFileType;
        this.fileName = fileName;
        this.createDate = createDate;
        this.createdBy = createdBy;
    }

    public BSBFileTracking() {

    }
}
