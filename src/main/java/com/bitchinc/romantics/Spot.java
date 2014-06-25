
package com.bitchinc.romantics;

import org.springframework.data.annotation.Id;

public class Spot {

	public Spot(String id, String description, double[] position) {
		super();
		this.id = id;
		this.description = description;
		this.position = position;
	}

	@Id private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String description;
	
	private double[] position;
	
	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] pos) {
		this.position = pos;
	}

	@Override
	public String toString() {
		return String.format("%s(%1.3f, %1.3f)", id, position[0], position[1]);
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
	public void setDescription(String description) {
		this.description = description;
	}

	
}
