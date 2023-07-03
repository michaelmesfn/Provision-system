package com.baloise.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "SELECT MAX (contractId) FROM Contract")
    Long getMaxContractId();
}
