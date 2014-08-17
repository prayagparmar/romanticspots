package com.bitchinc.romantics.service;

import com.bitchinc.romantics.pojo.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shashankdass on 7/24/14.
 */

@SuppressWarnings("deprecation")
@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}
