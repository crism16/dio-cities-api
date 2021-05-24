package com.github.cristianesilva.cites.api.distances;


import java.util.Arrays;
import java.util.List;

import com.github.cristianesilva.cites.api.cities.City;
import com.github.cristianesilva.cites.api.cities.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private final CityRepository cityRepository;
    Logger log = LoggerFactory.getLogger(DistanceService.class);

    public DistanceService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public Trip distanceByPointsInMiles(final Long city1, final Long city2) {
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        Double distance = cityRepository.distanceByPoints(city1,city2);
        Double time = timeTrip(distance);
        return new Trip(distance,time);
    }

    public Trip distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();
        Double distance = cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        Double time = timeTrip(distance);

        return new Trip(distance,time);

    }
        public double timeTrip(Double distance){
            int VELOCITY = 80;
            double time = (distance / VELOCITY);
            return time * 60;
        }

    }




