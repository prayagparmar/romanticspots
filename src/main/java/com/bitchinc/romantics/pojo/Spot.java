
package com.bitchinc.romantics.pojo;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;

public class Spot {
    public Spot(){

    }

    public Spot(String id, String description, double[] position) {
        super();
        this.id = id;
        this.description = description;
        this.position = position;
    }

    public Spot(String id, String description, double[] position, double rating, boolean secluded, GridFSDBFile image) {
        this.id = id;
        this.description = description;
        this.position = position;
        this.rating = rating;
        this.secluded = secluded;
        this.imageFile = image;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String description;

    private double[] position;

    private double rating;

    private boolean secluded;

    private GridFSDBFile imageFile;

    public double[] getPosition() {
        return position;
    }
    @Required
    public void setPosition(double[] pos) {
        this.position = pos;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    @Required
    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isSecluded() {
        return secluded;
    }

    public void setSecluded(boolean secluded) {
        this.secluded = secluded;
    }

    public GridFSDBFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(GridFSDBFile imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return String.format("%s(%1.3f, %1.3f)", id, position[0], position[1]);
    }





}
