package com.bsbFileProcessingService.fileProcessing.service;

import com.bsbFileProcessingService.bsb.repository.BSBDataRepository;
import com.bsbFileProcessingService.fileProcessing.model.BsbFileType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class BSBUpdateFileProcessingServiceTest {

    @Mock
    private BSBDataRepository bsbDataRepository;
    @InjectMocks
    private BSBUpdateFileProcessingService bsbFileProcessingService = new BSBUpdateFileProcessingService();

    @BeforeEach
    void setUp() {

    }

    @Test
    public void processBSBFile() throws IOException {
        bsbFileProcessingService.processBSBFile("/Users/senjosmac/Desktop/ftp-downloads/BSB Directory Update 02Mar22-01Apr22.csv", 1L);
        Mockito.verify(bsbDataRepository, atLeastOnce()).saveAll(any());
    }

    @Test(expected = IOException.class)
    public void processBSBFile_whenFileNotExist()  throws IOException {
        bsbFileProcessingService.processBSBFile("/Users/senjosmac/Desktop/ftp-downloads/BSBDirectoryMar22sss-312.csv", 1L);
    }

    @Test
    public void getBSBFileType() {
        Assert.assertEquals(BsbFileType.UPDATE,bsbFileProcessingService.getBSBFileType());
    }
}