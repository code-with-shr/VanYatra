package in.sp.main.service;

import java.util.List;

import in.sp.main.entity.Ride;

public interface RideService {
    public Ride saveRide(Ride ride);
    List<Ride> getAllRides();   //  MUST
}
