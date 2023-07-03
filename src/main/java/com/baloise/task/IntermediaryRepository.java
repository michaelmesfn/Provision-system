package com.baloise.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IntermediaryRepository extends JpaRepository<Intermediary, Long> {
    @Query(value = "SELECT MAX (intermediaryId) FROM Intermediary")
    Long getMaxIntermediaryId();
}
