package com.baloise.task;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private IntermediaryRepository intermediaryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void posCreateContract() {
        Contract contract = new Contract();
        contract.setBeginDate(LocalDate.now().plusMonths(1));
        contract.setEndDate(LocalDate.now().plusYears(6));
        contract.setIntermediaryId(1L);
        contract.setIntermediaryShare(10.0f);
        contract.setAmount(1000.0f);

        ContractController contractController = new ContractController(contractRepository, intermediaryRepository);
        ResponseEntity<?> response = contractController.createContract(contract);
        // Assert that the response status code is 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void negCreateContract() {
        Contract contract = new Contract();
        contract.setBeginDate(LocalDate.now().minusMonths(1));
        contract.setEndDate(LocalDate.now().plusYears(6));
        contract.setIntermediaryId(1L);
        contract.setIntermediaryShare(10.0f);
        contract.setAmount(1000.0f);

        ContractController contractController = new ContractController(contractRepository, intermediaryRepository);
        ResponseEntity<?> response = contractController.createContract(contract);
        // Assert that the response status code is 400
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
