package org.nardogames.rattlesnake.common.content;

import java.util.List;

public interface IController<T extends AbstractSprite> extends IEntity {
    public void addEntity(T entity);
    public void removeEntity(AbstractSprite entity);
    public void notifyContentChanged();

    List<T> getEntities();


}
