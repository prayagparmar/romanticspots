package com.bitchinc.romantics;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "spot", path = "spot")
public interface SpotRepository extends MongoRepository<Spot, String> {

	List<Spot> findByLatitude(@Param("latitude") String latitude);

}
