package com.bsbFileProcessingService.fileProcessing.service;
import com.bsbFileProcessingService.fileProcessing.model.BsbFileType;
import com.bsbFileProcessingService.bsb.model.BSBData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBSBFileProcessingService extends AbstractBSBFileProcessingService{
    Logger logger = LogManager.getLogger(DefaultBSBFileProcessingService.class);
    @Value("${jpa.batch.size}")
    private int jpaBatchSize;

    @Override
    /**
     * Assuming the file do not have any validation failures and file always exists
     * Assuming the full list file is trustworthy we can replace it with existing records
     * Following are not done
     * Create a back-up table
     * truncate the table
     * Insert the records
     * If any failure/Exception replace the back-up table to actual table
     */
    public void processBSBFile(String fileName, Long bsbFileTrackingId) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        String [] nextLine;
        try {
            List<BSBData> bsbDataBatchList = new ArrayList<>();
            long startTime = System.nanoTime();
            logger.info(String.format("Started processing file %s at %s", fileName, startTime));
            while ((nextLine = reader.readNext()) != null) {
                BSBData bsbData = new BSBData(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5],
                        nextLine[6], nextLine[7], bsbFileTrackingId);
                bsbDataBatchList.add(bsbData);
                if(bsbDataBatchList.size() == jpaBatchSize){
                    logger.info("committing batch");
                    bsbDataRepository.saveAll(bsbDataBatchList);
                    bsbDataBatchList.clear();
                }
            }
            logger.info(String.format("Completed processing file %s took %s milliseconds", fileName, (System.nanoTime()- startTime)/1000000));
        } catch (CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to process the file");
        }
    }

    @Override
    BsbFileType getBSBFileType() {
        return BsbFileType.FULL_LIST;
    }
}
