package com.example.trip_day_description.service;

import com.example.trip_day_description.model.DayDescription;
import java.util.List;

public interface DayDescriptionService {
    DayDescription createDayDescription(Long tripId, DayDescription dayDescription);
    List<DayDescription> getAllForTrip(Long tripId);
    List<DayDescription> getLibraryItems();
    DayDescription getById(Long id);
    DayDescription update(Long id, DayDescription updated);
    void softDelete(Long id);
    DayDescription copyFromLibrary(Long tripId, Long libraryId);

    DayDescription partialUpdate(Long id, DayDescription updates);
}
