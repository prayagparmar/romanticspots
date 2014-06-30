package com.bitchinc.romantics.service;

import com.bitchinc.romantics.pojo.Spot;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("deprecation")
@Repository
public interface SpotRepository extends MongoRepository<Spot, String> {
    List<Spot> findByPositionWithin(Circle c);

    List<Spot> findByPositionWithin(Box b);

    List<Spot> findByPositionNear(Point p, Distance d);
}
