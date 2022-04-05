package com.bsbFileProcessingService.fileProcessing.service;

import com.bsbFileProcessingService.bsb.model.BSBData;
import com.bsbFileProcessingService.fileProcessing.model.BsbFileType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BSBUpdateFileProcessingService extends  AbstractBSBFileProcessingService{
    Logger logger = LogManager.getLogger(BSBUpdateFileProcessingService.class);

    /**
     *
     * @param fileName
     * @param bsbFileTrackingId
     * @throws IOException
     */
    @Override
    public void processBSBFile(String fileName, Long bsbFileTrackingId) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        String [] nextLine;
        try {
            List<BSBData> addBsbBatch = new ArrayList<>();
            List<BSBData> delBsbBatch = new ArrayList<>();
            List<BSBData> chgBsbBatch = new ArrayList<>();
            long startTime = System.nanoTime();
            logger.info(String.format("Started processing file %s at %s", fileName, startTime));
            while ((nextLine = reader.readNext()) != null) {
                String changeType = nextLine[0]; //probably change to enum
                BSBData bsbData = new BSBData(nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], bsbFileTrackingId);
                if(changeType.equalsIgnoreCase("ADD")){
                    addBsbBatch.add(bsbData);
                }else if(changeType.equalsIgnoreCase("CHG")){
                    chgBsbBatch.add(bsbData);
                }else if(changeType.equalsIgnoreCase("DEL")){
                    delBsbBatch.add(bsbData);
                }
            }
            processAddEntries(addBsbBatch);
            processChgEntries(chgBsbBatch);
            processDelEntries(delBsbBatch);
            logger.info(String.format("Completed processing file %s took %s milliseconds", fileName, (System.nanoTime()- startTime)/1000000));
        } catch (CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to process the file");
        }
    }
    private void processAddEntries(List<BSBData> bsbDataList){
        if(bsbDataList.size() == 0){
            return;
        }
        bsbDataRepository.saveAll(bsbDataList);
    }
    private void processDelEntries(List<BSBData> bsbDataList){
        if(bsbDataList.size() == 0){
            return;
        }
        List<String> bsbNumberList = bsbDataList.stream().map(BSBData::getBsbNumber).collect(Collectors.toList());
        bsbDataRepository.deleteAll(bsbDataRepository.findAllByBsbNumbers(bsbNumberList));

    }

    private void processChgEntries(List<BSBData> bsbDataList){
        if(bsbDataList.size() == 0){
            return;
        }
        processDelEntries(bsbDataList);
        processAddEntries(bsbDataList);
    }

    @Override
    BsbFileType getBSBFileType() {
        return BsbFileType.UPDATE;
    }
}
