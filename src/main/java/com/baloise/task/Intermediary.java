package com.baloise.task;
import jakarta.persistence.*;

@Entity
@Table(name = "intermediaries")
public class Intermediary {
    @Id
    @Column(name = "intermediaryId")
    private Long intermediaryId;
    private Float sumMonthlyProvisions;

    public Intermediary() {
    }

    public Long getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(Long intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public Float getSumMonthlyProvisions() {
        return sumMonthlyProvisions;
    }

    public void setSumMonthlyProvisions(Float sumMonthlyProvisions) {
        this.sumMonthlyProvisions = sumMonthlyProvisions;
    }
}
