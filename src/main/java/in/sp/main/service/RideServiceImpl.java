package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Ride;
import in.sp.main.repository.RideRepository;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository repo;

    @Override
    public Ride saveRide(Ride ride) {
        return repo.save(ride);
    }
    @Override
    public List<Ride> getAllRides() {
        return repo.findAll();   // DB se data
    }
    
}
