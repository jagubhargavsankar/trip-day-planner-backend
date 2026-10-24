package com.example.trip_day_description.service.impl;

import com.example.trip_day_description.model.DayDescription;
import com.example.trip_day_description.model.Trip;
import com.example.trip_day_description.repository.DayDescriptionRepository;
import com.example.trip_day_description.repository.TripRepository;
import com.example.trip_day_description.service.DayDescriptionService;
import com.example.trip_day_description.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayDescriptionServiceImpl implements DayDescriptionService {

    @Autowired
    private DayDescriptionRepository dayDescriptionRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public DayDescription createDayDescription(Long tripId, DayDescription dayDescription) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with ID: " + tripId));
        dayDescription.setTrip(trip);
        return dayDescriptionRepository.save(dayDescription);
    }

    @Override
    public List<DayDescription> getAllForTrip(Long tripId) {
        return dayDescriptionRepository.findByTripId(tripId);
    }

    @Override
    public List<DayDescription> getLibraryItems() {
        return dayDescriptionRepository.findBySaveInLibraryTrue();
    }

    @Override
    public DayDescription getById(Long id) {
        DayDescription dayDescription = dayDescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DayDescription not found with ID: " + id));

        if (dayDescription.isDeleted()) {
            throw new ResourceNotFoundException("DayDescription is deleted with ID: " + id);
        }

        return dayDescription;
    }


    @Override
    public DayDescription update(Long id, DayDescription updated) {
        DayDescription existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setTime(updated.getTime());
        existing.setPackingSuggestions(updated.getPackingSuggestions());
        existing.setImages(updated.getImages());
        existing.setSaveInLibrary(updated.isSaveInLibrary());
        return dayDescriptionRepository.save(existing);
    }

    @Override
    public void softDelete(Long id) {
        DayDescription existing = getById(id);
        existing.setDeleted(true);
        dayDescriptionRepository.save(existing);
    }
    public DayDescription partialUpdate(Long id, DayDescription updates) {
        DayDescription existing = dayDescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DayDescription not found with ID: " + id));

        if (updates.getTitle() != null) existing.setTitle(updates.getTitle());
        if (updates.getDescription() != null) existing.setDescription(updates.getDescription());
        if (updates.getLocation() != null) existing.setLocation(updates.getLocation());
        if (updates.getTime() != null) existing.setTime(updates.getTime());
        if (updates.getPackingSuggestions() != null) existing.setPackingSuggestions(updates.getPackingSuggestions());
        if (updates.getImages() != null) existing.setImages(updates.getImages());
        existing.setSaveInLibrary(updates.isSaveInLibrary());
        existing.setDeleted(updates.isDeleted());

        return dayDescriptionRepository.save(existing);
    }

    @Override
    public DayDescription copyFromLibrary(Long tripId, Long libraryId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with ID: " + tripId));

        DayDescription libraryItem = dayDescriptionRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Library item not found with ID: " + libraryId));

        DayDescription copy = new DayDescription();
        copy.setTrip(trip);
        copy.setTitle(libraryItem.getTitle());
        copy.setDescription(libraryItem.getDescription());
        copy.setLocation(libraryItem.getLocation());
        copy.setTime(libraryItem.getTime());
        copy.setPackingSuggestions(libraryItem.getPackingSuggestions());
        copy.setImages(libraryItem.getImages() != null
                ? new java.util.ArrayList<>(libraryItem.getImages())
                : null);

        copy.setSaveInLibrary(false);
        copy.setDeleted(false);

        return dayDescriptionRepository.save(copy);
    }

}
