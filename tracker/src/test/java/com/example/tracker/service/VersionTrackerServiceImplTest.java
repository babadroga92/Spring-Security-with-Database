package com.example.tracker.service;
import com.example.tracker.dao.IVersionTrackerRepo;
import com.example.tracker.enums.ValidStatuses;
import com.example.tracker.exception.WrongIdException;
import com.example.tracker.model.VersionTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;



@SpringBootTest
public class VersionTrackerServiceImplTest {

    @Autowired
    private IVersionTrackerRepo iVersionTrackerRepo;
    @Autowired // zasto moram autowired i dao i service kad u nasem projektu stavljamo autowired samo na dao???
    private VersionTrackerServiceImpl versionTrackerServiceImpl;
    private VersionTracker versionTracker;

    //ZASTO MI BeforeEach setup ne radi???
    @BeforeEach
    public void setup(){
        versionTrackerServiceImpl = new VersionTrackerServiceImpl(iVersionTrackerRepo); //zasto mi ovde trazi DAO?
        this.versionTracker = new VersionTracker(17, "deseta verzija", "description", ValidStatuses.DONE,
               null, null,
                null);
    }

    @Test
    public void findAll(){
        List<VersionTracker> listaService = versionTrackerServiceImpl.getAll();
        List<VersionTracker> listaRepo = iVersionTrackerRepo.findAll();
        assertNotNull(listaService);
        assertNotNull(listaRepo);
        assertEquals(listaService.size(), listaRepo.size()); // kako da znam koji aseert dodati??? Zavisi od dependency po pom.xml

    }

    @Test
    public void findById(){
        VersionTracker versionTracker1 = versionTrackerServiceImpl.findById(3);
        Optional<VersionTracker> optional = iVersionTrackerRepo.findById(3);
        assertNotNull(versionTracker1);
        assertTrue(optional.isPresent());
        assertEquals(versionTracker1.getId(), optional.get().getId());
    }
    @Test
    public void findByWrongId() {
        Assertions.assertThrows(WrongIdException.class, () -> {
            versionTrackerServiceImpl.findById(10);
        });
    }
    @Test

    public void addVersionTracker(){
//        VersionTracker vt = new VersionTracker(12, "12 verzija", "description", ValidStatuses.DONE,
//                   null, null,
//               null);
        versionTrackerServiceImpl.addVersionTracker(this.versionTracker);
        Optional<VersionTracker> searchForTracker = iVersionTrackerRepo.findById(this.versionTracker.getId());
        assertTrue(searchForTracker.isPresent());
        assertEquals(this.versionTracker.getId(), searchForTracker.get().getId());
        assertEquals(this.versionTracker.getName(), searchForTracker.get().getName());

    }
    @Test
    public void removeTracker(){
        versionTrackerServiceImpl.removeTracker(this.versionTracker.getId());
        Optional<VersionTracker> findTracker = iVersionTrackerRepo.findById(this.versionTracker.getId());
        assertFalse(findTracker.isPresent());
    }
    @Test
    public void updateTracker(){
        this.versionTracker.setId(20);
        versionTrackerServiceImpl.addVersionTracker(this.versionTracker);
        this.versionTracker.setName("jedanaesta verzija");
        versionTrackerServiceImpl.updateTracker(this.versionTracker, this.versionTracker.getId());
        Optional<VersionTracker> optional = iVersionTrackerRepo.findById(this.versionTracker.getId());
        assertTrue(optional.isPresent());
        assertEquals("jedanaesta verzija", optional.get().getName());
    }

}
