package io.github.puzzle.cosmic.impl.ray;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.blockentities.BlockEntity;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.world.Zone;

import java.util.function.Function;

public class Raycaster {
    private static final float MIN_STEP_SIZE = 0.001f;

    public static RaycastHitResult castRay(RaycastContext context, Function<Vector3, Boolean> hitTest) {
        Vector3 direction = context.end.cpy().sub(context.start).nor();
        float distance = context.start.dst(context.end);

        float stepSize = context.initialStepSize;
        Vector3 currentPos = new Vector3(context.start);
        int iterations = 0;

        while (distance > 0) {
            iterations++;
            float step = Math.min(distance, stepSize);
            currentPos.mulAdd(direction, step);
            distance -= step;

            if (hitTest.apply(currentPos)) {
                currentPos.mulAdd(direction, -stepSize);
                stepSize *= context.refinementFactor;

                int guard = 0;
                while (!hitTest.apply(currentPos) && guard++ < 3000) {
                    currentPos.mulAdd(direction, stepSize);
                }
                break;
            }
        }

        return new RaycastHitResult(currentPos, context.start.dst(currentPos), context.zone);
    }

    public static class RaycastContext {
        public Vector3 start = new Vector3();
        public Vector3 end = new Vector3();
        public final float initialStepSize;
        public final float refinementFactor;
        public final Zone zone;

        public RaycastContext(Zone zone, float stepSize, float refinementFactor) {
            this.zone = zone;
            if (stepSize < MIN_STEP_SIZE) stepSize = MIN_STEP_SIZE;
            this.initialStepSize = stepSize;
            this.refinementFactor = refinementFactor;
        }

        public void start(Camera camera) {
            this.start.set(camera.position);
        }

        public void end(Camera camera) {
            this.end.set(camera.position);
        }

        public void start(Vector3 vector3) {
            this.start.set(vector3);
        }

        public void end(Vector3 vector3) {
            this.end.set(vector3);
        }

        public void start(BlockPosition blockPosition) {
            this.start.set(blockPosition.getCenterOfBlock());
        }

        public void end(BlockPosition blockPosition) {
            this.end.set(blockPosition.getCenterOfBlock());
        }

        public void end(Vector3 direction, float distance) {
            if (this.start != null) {
                this.end.set(this.start).mulAdd(direction.nor(), distance);
            }
        }
    }

    public static class RaycastHitResult {
        private final Vector3 hitPosition;
        private final Zone hitZone;
        private final float hitDistance;

        public RaycastHitResult(Vector3 hit, float hitDistance,Zone hitZone) {
            this.hitPosition = hit;
            this.hitZone = hitZone;
            this.hitDistance = hitDistance;
        }

        public Entity getEntity() {
            Array<Entity> entities = hitZone.getAllEntities();
            for (Entity entity : entities) {
                if (entity.globalBoundingBox.contains(hitPosition) && entity.isMob() && !entity.isDead()) return entity;
            }
            return null;
        }

        public Entity getPlayerEntity() {
            Array<Player> players = hitZone.getPlayers();
            for (Player player : players) {
                PlayerEntity entity = (PlayerEntity) player.getEntity();
                if (entity.globalBoundingBox.contains(hitPosition) && !entity.isDead()) return entity;
            }
            return null;
        }

        public BlockEntity getBlockEntity() {
            return hitZone.getBlockEntity((int)Math.floor(hitPosition.x), (int)Math.floor(hitPosition.y), (int)Math.floor(hitPosition.z));
        }

        public BlockPosition getBlockPosition() {
            return BlockPosition.ofGlobal(this.hitZone, (int)Math.floor(hitPosition.x), (int)Math.floor(hitPosition.y), (int)Math.floor(hitPosition.z));
        }

        public BlockState getBlockState() {
            return this.getBlockPosition().getBlockState();
        }

        public Block getBlock() {
            return this.getBlockState().getBlock();
        }

        public Vector3 getVector() {
            return hitPosition;
        }

        public float getDistance() {
            return hitDistance;
        }
    }
}