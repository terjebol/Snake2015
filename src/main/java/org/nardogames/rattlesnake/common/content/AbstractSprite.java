package org.nardogames.rattlesnake.common.content;

import org.newdawn.slick.geom.Vector2f;

public abstract class AbstractSprite implements IEntity {

    public abstract Vector2f getVelocity();
    public abstract Bounds getBounds();

    @Override
    public void update(float deltaTime) {
        float dx = deltaTime * getVelocity().x;
        float dy = deltaTime * getVelocity().y;
        getBounds().translate(dx, dy);
    }

    public abstract void notifyCollidedWith(AbstractSprite otherSprite);
}
