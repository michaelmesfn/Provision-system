package com.baloise.task;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping
public class ContractController {

    private final ContractRepository contractRepository;
    private final IntermediaryRepository intermediaryRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository, IntermediaryRepository intermediaryRepository) {
        this.contractRepository = contractRepository;
        this.intermediaryRepository = intermediaryRepository;
    }

    @PostMapping("/contract/create")
    public ResponseEntity<?> createContract(@Valid @RequestBody Contract contract) {
        Long contractId = contract.getContractId();
        LocalDate beginDate = contract.getBeginDate();
        LocalDate endDate = contract.getEndDate();
        Long intermediaryId = contract.getIntermediaryId();
        Float intermediaryShare = contract.getIntermediaryShare();
        Float amount = contract.getAmount();
//      check that contractId is not provided
        if (contractId != null) {
            String errorMessage = "Contract id cannot be provided, will be auto assigned";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
//      check that the begin date is in the next 3 month
        if (beginDate.isAfter(LocalDate.now().plusMonths(3)) || beginDate.isBefore(LocalDate.now())) {
            String errorMessage = "The begin date should be in the next 3 months";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
//      check that end date is atleast after 5 years
        if (endDate.isBefore(beginDate.plusYears(5).minusDays(1))) {
            String errorMessage = "The end date must be at least 5 years after the start date";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
//      check that the intermediary provided exists
        Optional<Intermediary> intermediary = intermediaryRepository.findById(intermediaryId);
        if (intermediary.isEmpty()) {
            String errorMessage = "Intermediary Id provided doesn't exist";
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
//      adjust the sumofMonthlyprovisions of intermediary
        Float newSumMonthlyProvisions = intermediary.get().getSumMonthlyProvisions() + (amount * (intermediaryShare / 100));
        intermediary.get().setSumMonthlyProvisions(newSumMonthlyProvisions);
//      assign a contract ID and save contract
        contract.setContractId(contractRepository.getMaxContractId() + 1L);
        Contract savedContract = contractRepository.save(contract);
        return ResponseEntity.ok(savedContract);
    }

    @GetMapping("/contracts")
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @DeleteMapping("/contract/delete/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (contract.isPresent()) {
            Long intermediaryId = contract.get().getIntermediaryId();
            Float intermediaryShare = contract.get().getIntermediaryShare();
            Float amount = contract.get().getAmount();
//          adjust sumMonthlyProvisions of intermediary
            Optional<Intermediary> intermediary =  intermediaryRepository.findById(intermediaryId);
            if (intermediary.isPresent()) {
                Float sumMonthlyProvisions = intermediary.get().getSumMonthlyProvisions();
                intermediary.get().setSumMonthlyProvisions(sumMonthlyProvisions - (amount * (intermediaryShare / 100)));
            }
            contractRepository.deleteById(id);
            return ResponseEntity.ok("Successfully Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contract couldn't be found");
        }
    }
}



