package org.nardogames.rattlesnake.domain;

/**
 * Created by Terje on 03.02.2015.
 */
public interface IAmTimed {
    public void setActivationTime(float time);
    public float getActivationTime();
    public boolean shouldActivate();

}
