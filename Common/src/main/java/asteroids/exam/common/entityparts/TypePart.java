package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public class TypePart implements EntityPart {

    public enum EntityType { PLAYER, ENEMY, ASTEROID, BULLET }

    private final EntityType type;                       // this entity’s type

    public TypePart(EntityType type) {
        this.type = type;                                // init type
    }

    public EntityType getType() {
        return type;                                     // retrieve type
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        // no per-frame logic
    }
}
