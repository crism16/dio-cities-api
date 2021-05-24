package com.github.cristianesilva.cites.api.distances;


import java.time.LocalTime;
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
        LocalTime time = timeTrip(distance,50);
        return new Trip(distance,time);
    }

    public Trip distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();
        Double distance = cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        LocalTime time = timeTrip(distance/1000, 80);

        return new Trip(distance,time);

    }
        public LocalTime timeTrip(Double distance, int VELOCITY){
            double time = (distance / VELOCITY);
            LocalTime formattedTime = formatTime(time);
            return formattedTime;
        }

    public  LocalTime formatTime (Double time){

        long millis = (long) (time * 60 * 60 * 1000);
        int seconds = (int) (millis / 1000) % 60 ;
        int minutes = (int) ((millis / (100060)) % 60);
        int hours   = (int) ((millis / (100060*60)) % 24);

        return LocalTime.of(hours,minutes,seconds);
    }

    }




