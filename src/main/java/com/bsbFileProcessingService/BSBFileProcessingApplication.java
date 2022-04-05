package com.bsbFileProcessingService;

import com.bsbFileProcessingService.fileProcessing.service.DefaultBSBFileProcessingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@EnableCaching
@SpringBootApplication
public class BSBFileProcessingApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BSBFileProcessingApplication.class, args);
        DefaultBSBFileProcessingService service = applicationContext.getBean(DefaultBSBFileProcessingService.class);
        service.processFile("/Users/senjosmac/Desktop/ftp-downloads/BSBDirectoryMar22-312.csv");


        /*SpringApplication.run(BsbSupplierApplication.class, args);
        */

    }
}
