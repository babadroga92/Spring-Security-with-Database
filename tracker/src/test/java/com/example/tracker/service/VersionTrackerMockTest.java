package com.example.tracker.service;

import com.example.tracker.dao.IVersionTrackerRepo;
import com.example.tracker.model.VersionTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VersionTrackerMockTest {
    @Autowired
    private IVersionTrackerRepo iVersionTrackerRepo;
    @Autowired
    private VersionTrackerServiceImpl versionTrackerService;

    private VersionTracker vt;

    @BeforeEach
    public void setup(){
        iVersionTrackerRepo = Mockito.mock(IVersionTrackerRepo.class);
        versionTrackerService = new VersionTrackerServiceImpl(iVersionTrackerRepo);
        this.vt = new VersionTracker(12, "12 verzija", null,null,null,null,null);
    }
    @Test
    public void findAll(){
        List<VersionTracker> listaService = versionTrackerService.getAll();
        List<VersionTracker> listaRepo = iVersionTrackerRepo.findAll();
        assertNotNull(listaService);
        assertNotNull(listaRepo);
        assertEquals(listaService.size(), listaRepo.size());
    }
    @Test
    public void findById(){
        when(iVersionTrackerRepo.findById(this.vt.getId())).thenReturn(Optional.of(this.vt));
        VersionTracker trackerPoServisu = versionTrackerService.findById(this.vt.getId());
        assertEquals(this.vt.getName(),trackerPoServisu.getName());
    }
    @Test
    public void removeTracker(){
        when(iVersionTrackerRepo.findById(this.vt.getId())).thenReturn(Optional.of(this.vt));
        String result = versionTrackerService.removeTracker(this.vt.getId());
        assertEquals("The tracker with id "+ vt.getId()+ " has been deleted", result);
    }
    @Test
    public void addTracker(){
        when(iVersionTrackerRepo.save(any())).thenReturn(this.vt);
        VersionTracker serviceVerzija = versionTrackerService.addVersionTracker(this.vt);
        assertEquals(this.vt.getId(), serviceVerzija.getId());
    }
    @Test
    public void updateTracker(){
        when(iVersionTrackerRepo.findById(this.vt.getId())).thenReturn(Optional.of(this.vt));
        this.vt.setName("test verzija");
        when(iVersionTrackerRepo.save(any())).thenReturn(this.vt);
        VersionTracker serviseVerzija = versionTrackerService.updateTracker(this.vt, this.vt.getId());
        assertEquals(this.vt.getId(), serviseVerzija.getId());
    }


}
