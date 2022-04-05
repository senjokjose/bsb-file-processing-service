package com.bsbFileProcessingService.fileProcessing.service;

import com.bsbFileProcessingService.bsb.repository.BSBDataRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BSBFileProcessingServiceIntegrationTest {

    @Autowired
    private BSBDataRepository bsbDataRepository;

    @Autowired
    private BSBUpdateFileProcessingService bsbUpdateFileProcessingService;

    @Autowired
    private DefaultBSBFileProcessingService defaultBSBFileProcessingService;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void processFile() {
        Assert.assertEquals(0,bsbDataRepository.findAll().size());
        defaultBSBFileProcessingService.processFile("/Users/senjosmac/Desktop/ftp-downloads/BSBDirectoryMar22-312.csv");
        Assert.assertTrue(bsbDataRepository.findAll().size() > 0);
    }



}