package com.bitchinc.romantics;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository
public interface SpotRepository extends MongoRepository<Spot, String> {

	List<Spot> findByPositionWithin(Circle c);

	List<Spot> findByPositionWithin(Box b);

	List<Spot> findByPositionNear(Point p, Distance d);
	
}
