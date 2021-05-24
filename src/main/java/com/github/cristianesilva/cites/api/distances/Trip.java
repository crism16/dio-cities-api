package com.github.cristianesilva.cites.api.distances;

import java.time.LocalTime;

public class Trip {
    private Double distance;
    private LocalTime LocalTime;

    public Trip(Double distance, LocalTime time) {
        this.distance = distance;
        this.LocalTime = time;

    }

    public Double getDistance() {
        return distance;

    }

    public void setDistance(Double distance) {
        this.distance = distance;

    }

    public LocalTime getTime() {
        return LocalTime;
    }

    public void setTime(LocalTime time) {
        this.LocalTime = time;
    }


    }



