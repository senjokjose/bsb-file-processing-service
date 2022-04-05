package com.bsbFileProcessingService.fileProcessing.service;

import com.bsbFileProcessingService.fileProcessing.model.BsbFileType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBSBFileProcessingServiceTest {

    @InjectMocks
    private DefaultBSBFileProcessingService bsbFileProcessingService = new DefaultBSBFileProcessingService();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void processBSBFile() throws IOException {
        bsbFileProcessingService.processBSBFile("/Users/senjosmac/Desktop/ftp-downloads/BSBDirectoryMar22-312.csv", 1L);
    }

    @Test(expected = IOException.class)
    public void processBSBFile_whenFileNotExist()  throws IOException {
        bsbFileProcessingService.processBSBFile("/Users/senjosmac/Desktop/ftp-downloads/BSBDirectoryMar22sss-312.csv", 1L);
    }

    @Test
    public void getBSBFileType() {
        Assert.assertEquals(BsbFileType.FULL_LIST,bsbFileProcessingService.getBSBFileType());
    }
}