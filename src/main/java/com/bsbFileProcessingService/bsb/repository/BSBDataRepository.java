package com.bsbFileProcessingService.bsb.repository;

import com.bsbFileProcessingService.bsb.model.BSBData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BSBDataRepository extends CrudRepository<BSBData, Long> {

    @Query(value = "Select b from BSBData b where b.bsbNumber = ?1")
    BSBData findBSBDataByBsbNumber(String bsbNumber);

    @Query(value = "Select b from BSBData b where b.bsbNumber in ?1")
    List<BSBData> findAllByBsbNumbers(List<String> bsbNumbers);

    @Query(value = "Select b from BSBData b")
    List<BSBData> findAll();

}
