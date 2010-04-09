/**
	Copyright (C) 2009,2010  Tobias Domhan

    This file is part of AndOpenGLCam.

    AndObjViewer is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AndObjViewer is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AndObjViewer.  If not, see <http://www.gnu.org/licenses/>.
 
 */
package edu.dhbw.andar;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 
 * @author tobi
 *
 */
public abstract class ARObject {
	/**
	 * Is this object visible? -> is the marker belonging to this object visible?
	 */
	private boolean visible = false;
	private String name;
	private String patternName;
	private double markerWidth;
	private double[] center;
	//this object must be locked while altering the glMatrix
	private float[] glMatrix = new float[16];
	//this object must be locked while altering the transMat
	private double[] transMat = new double[16];//[3][4] array
	private int id;
	
	/**
	 * Create a new AR object.
	 * @param name the name of the the object, an arbitrary string
	 * @param patternName the file name of the pattern(the file must reside in the res/raw folder)
	 * @param markerWidth
	 * @param markerCenter
	 */
	public ARObject(String name, String patternName, double markerWidth, double[] markerCenter) {
		this.name = name;
		this.patternName = patternName;
		this.markerWidth = markerWidth;
		if(markerCenter.length == 2) {
			this.center = markerCenter;
		} else {
			this.center = new double[]{0,0};
		}
	}
	
	
	
	
	public double getMarkerWidth() {
		return markerWidth;
	}




	public double[] getCenter() {
		return center;
	}




	public int getId() {
		return id;
	}




	protected void setId(int id) {
		this.id = id;
	}

	



	public String getPatternName() {
		return patternName;
	}


	/**
	 * 
	 * @return Is this object visible? -> is the marker belonging to this object visible?
	 */
	public boolean isVisible() {
		return visible;
	}


	/**
	 * Get the current translation matrix.
	 * @return
	 */
	public synchronized double[] getTransMatrix() {
		return transMat;
	}
	
	/**
	 * Do OpenGL stuff.
	 * Everything draw here will be drawn directly onto the marker.
	 * @param gl
	 */
	public synchronized void draw(GL10 gl) {
		gl.glLoadMatrixf(FloatBuffer.wrap(glMatrix));
	}
	
}