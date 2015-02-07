package org.nardogames.rattlesnake.common.content;

import org.nardogames.rattlesnake.domain.ControllerType;

public interface IScene extends IEntity {
    public boolean isInitialized();
    public void initialize();
    public <T extends AbstractSprite> IController<T> getController(ControllerType controllerType);
}
