package com.bitchinc.romantics.pojo;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * Created by shashankdass on 8/1/14.
 */
public class SpotBuilder {
    private Spot spot = new Spot();

    public SpotBuilder addPosition(double[] position) {
        spot.setPosition(position);
        return this;
    }

    public SpotBuilder addDescription(String description) {
        spot.setDescription(description);
        return this;
    }
    public SpotBuilder addSecluded(Boolean secluded) {
        spot.setSecluded(secluded);
        return this;
    }

    public SpotBuilder addImageFile(GridFSDBFile imageFile) {
        spot.setImageFile(imageFile);
        return this;
    }

    public SpotBuilder addRatings(double rating) {
        spot.setRating(rating);
        return this;
    }

    public Spot build(){
        return spot;
    }
}
