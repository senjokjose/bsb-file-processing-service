package com.bsbFileProcessingService.fileProcessing.repository;

import com.bsbFileProcessingService.fileProcessing.model.BSBFileTracking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BSBFileTrackingRepository extends CrudRepository<BSBFileTracking, Long> {

    @Query(value = "Select b from BSBFileTracking b where b.fileName = ?1")
    BSBFileTracking findBSBFileTrackingByFileName(String fileName);
}
