package com.baloise.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractId")
    private Long contractId;
    @NotNull
    private LocalDate beginDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Long intermediaryId;
    @NotNull @Min(value = 5, message = ErrorResponse.INTERMEDIARYSHARE_RANGE_ERROR) @Max(value = 25, message = ErrorResponse.INTERMEDIARYSHARE_RANGE_ERROR)
//    intermediary share in percent
    private Float intermediaryShare;
    @NotNull
    private Float amount;

    public Contract() {
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(Long intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public float getIntermediaryShare() {
        return intermediaryShare;
    }

    public void setIntermediaryShare(float intermediaryShare) {
        this.intermediaryShare = intermediaryShare;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
