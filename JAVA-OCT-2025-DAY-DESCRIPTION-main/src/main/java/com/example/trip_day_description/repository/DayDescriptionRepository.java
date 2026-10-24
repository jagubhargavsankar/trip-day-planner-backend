package com.example.trip_day_description.repository;

import com.example.trip_day_description.model.DayDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayDescriptionRepository extends JpaRepository<DayDescription, Long> {
    List<DayDescription> findByTripId(Long tripId);
    List<DayDescription> findBySaveInLibraryTrue();
}
