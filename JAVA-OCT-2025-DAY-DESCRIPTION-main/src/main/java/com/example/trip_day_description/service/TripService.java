package com.example.trip_day_description.service;

import com.example.trip_day_description.model.DayDescription;
import com.example.trip_day_description.model.Trip;
import java.util.List;

public interface TripService {
    Trip createTrip(Trip trip);
    List<Trip> getAllTrips();
    Trip getTripById(Long id);

    void deleteTrip(Long id);

}
