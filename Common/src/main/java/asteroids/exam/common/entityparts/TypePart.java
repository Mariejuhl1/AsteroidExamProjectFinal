package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public class TypePart implements EntityPart {

    public enum EntityType {
        PLAYER,
        ENEMY,
        ASTEROID,
        BULLET
    }

    private final EntityType type;

    public TypePart(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
