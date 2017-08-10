/*
 * Copyright (c) 2017, bandie
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.bandie.circleart;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author bandie
 */
public class Pen {

    private boolean on;
    private int[] colorMode;
    private int sizeMode;
    private int distance;
    private boolean isUsingY;

    Pen() {
        this.on = false;
        this.colorMode = new int[3];
        this.colorMode[0] = 0;
        this.colorMode[1] = 1;
        this.colorMode[2] = 2;
        this.sizeMode = 200;
        this.distance = 100;
    }

    
    public enum RGB{
        RED,
        GREEN,
        BLUE;
    }
    
    /**
     *
     * @return Shows if the pen is enabled or not
     */
    protected boolean isOn() {
        return on;
    }

    /**
     *
     * @return Toggled value of the pen
     */
    protected boolean toggle() {
        return this.on = !on;
    }

    protected void setColorMode(RGB rgb, int mode) {
        this.colorMode[rgb.ordinal()] = mode;
    }

    protected Color getColor(double x, double y) {

        return Color.rgb(
                ObjectMath.calcColor(x, y, colorMode[0]), 
                ObjectMath.calcColor(x, y, colorMode[1]), 
                ObjectMath.calcColor(x, y, colorMode[2])
        );

    }

    protected double getSize(double x, double y) {
        if (this.isUsingY) {
            return Math.abs(Math.sin(y / distance)) * sizeMode;
        } else {
            return Math.abs(Math.sin(x / distance)) * sizeMode;
        }
    }

    /**
     * @return the colorMode
     */
    public int[] getColorMode() {
        return colorMode;
    }

    /**
     * @return the sizeMode
     */
    public int getSizeMode() {
        return sizeMode;
    }

    /**
     * @param sizeMode the sizeMode to set
     */
    public void setSizeMode(int sizeMode) {
        this.sizeMode = sizeMode;
    }

    /**
     * @return the isUsingY
     */
    public boolean isIsUsingY() {
        return isUsingY;
    }

    /**
     * @param isUsingY the isUsingY to set
     */
    public void setIsUsingY(boolean isUsingY) {
        this.isUsingY = isUsingY;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

}
