package com.example.trip_day_description.controller;
import com.example.trip_day_description.model.DayDescription;
import com.example.trip_day_description.service.DayDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class DayDescriptionController {
    @Autowired
    private DayDescriptionService dayDescriptionService;

    @PostMapping("/trips/{tripId}/day-descriptions")
    public ResponseEntity<DayDescription> createDayDescription(
            @PathVariable Long tripId,
            @RequestBody DayDescription dayDescription) {
        return ResponseEntity.ok(dayDescriptionService.createDayDescription(tripId, dayDescription));
    }

    @GetMapping("/trips/{tripId}/day-descriptions")
    public ResponseEntity<List<DayDescription>> getAllForTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(dayDescriptionService.getAllForTrip(tripId));
    }

    @GetMapping("/library/day-descriptions")
    public ResponseEntity<List<DayDescription>> getLibraryItems() {
        return ResponseEntity.ok(dayDescriptionService.getLibraryItems());
    }

    @GetMapping("/day-descriptions/{id}")
    public ResponseEntity<DayDescription> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dayDescriptionService.getById(id));
    }

    @PutMapping("/day-descriptions/{id}")
    public ResponseEntity<DayDescription> update(
            @PathVariable Long id,
            @RequestBody DayDescription dayDescription) {
        return ResponseEntity.ok(dayDescriptionService.update(id, dayDescription));
    }
    @PatchMapping("/day-descriptions/{id}")
    public ResponseEntity<DayDescription> partialUpdate(
            @PathVariable Long id,
            @RequestBody DayDescription updates) {
        return ResponseEntity.ok(dayDescriptionService.partialUpdate(id, updates));
    }

    @DeleteMapping("/day-descriptions/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        dayDescriptionService.softDelete(id);
        return ResponseEntity.ok("Day description deleted successfully");
    }

    @PostMapping("/trips/{tripId}/day-descriptions/copy/{libraryId}")
    public ResponseEntity<DayDescription> copyFromLibrary(
            @PathVariable Long tripId,
            @PathVariable Long libraryId) {
        return ResponseEntity.ok(dayDescriptionService.copyFromLibrary(tripId, libraryId));
    }
}
