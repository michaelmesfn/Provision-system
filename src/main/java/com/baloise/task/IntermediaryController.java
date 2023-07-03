package com.baloise.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class IntermediaryController {
    private IntermediaryRepository intermediaryRepository;
    @Autowired
    public IntermediaryController (IntermediaryRepository intermediaryRepository) {
        this.intermediaryRepository = intermediaryRepository;
    }

    @PostMapping("/intermediary/create")
    public ResponseEntity<Intermediary> createIntermediary () {
        Intermediary intermediary = new Intermediary();
        intermediary.setIntermediaryId(intermediaryRepository.getMaxIntermediaryId()+1L);
        intermediary.setSumMonthlyProvisions((float) 0);
        Intermediary savedIntermediary = intermediaryRepository.save(intermediary);
        return ResponseEntity.ok(savedIntermediary);
    }

    @GetMapping("/intermediaries")
    public List<Intermediary> getAllIntermediaries() {
        return intermediaryRepository.findAll();
    }

    @GetMapping("/intermediaries-overview")
    public Float getSumOfAllIntermediaryProvisions() {
        Float sumOfProvisionsAllIntermediaries = (float) 0;
        List<Intermediary> allIntermediaries = intermediaryRepository.findAll();
        for (Intermediary intermediary : allIntermediaries) {
            sumOfProvisionsAllIntermediaries = sumOfProvisionsAllIntermediaries + intermediary.getSumMonthlyProvisions();
        }
        return sumOfProvisionsAllIntermediaries;
    }
}
