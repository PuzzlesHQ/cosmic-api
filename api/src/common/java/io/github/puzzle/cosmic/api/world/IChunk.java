package io.github.puzzle.cosmic.api.world;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.blockentities.BlockEntity;
import finalforeach.cosmicreach.rendering.IChunkMeshGroup;
import finalforeach.cosmicreach.world.Zone;

import java.util.function.Consumer;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IChunk {


    interface IMeshingController {

        /**
         * Flags the chunk for remeshing.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagForRemeshing(boolean immediate);

        /**
         * Flags the horizontal touching chunk for remeshing.
         * @param zone the zone the chunk is in.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagHorizontalTouchingChunksForRemeshing(Zone zone, boolean immediate);

        /**
         * Flags the touching chunk for remeshing.
         * @param zone the zone the chunk is in.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagTouchingChunksForRemeshing(Zone zone, boolean immediate);

        /**
         * Flags the current chunk and its touching chunks for remeshing if necessary.
         * @param zone the zone the chuk is in.
         * @param localX the local X pos to start at.
         * @param localY the local Y pos to start at.
         * @param localZ the local Z pos to start at.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagTouchingChunksForRemeshing(Zone zone, int localX, int localY, int localZ, boolean immediate);

        /**
         * Sets the mesh group of the chunk.
         * @param meshGroup the new mesh group to set.
         */
        void setMeshGroup(IChunkMeshGroup<?> meshGroup);

        /**
         * Gets the mesh group of the chunk.
         */
        IChunkMeshGroup<?> getMeshGroup();
    }

    interface IBlockEntityController {

        /**
         * Gets the blockEntity at the position.
         *
         * @param localX the local X of the blockEntity.
         * @param localY the local Y of the blockEntity.
         * @param localZ the local Z of the blockEntity.
         * @return a {@link BlockEntity}
         */
        BlockEntity get(int localX, int localY, int localZ);

        /**
         * Puts the blockEntity of the blockState.
         *
         * @param blockState the blockState of the blockEntity.
         * @param localX     the local X of where to put the blockEntity.
         * @param localY     the local Y of where to put the blockEntity.
         * @param localZ     the local Z of where to put the blockEntity.
         * @return a {@link BlockEntity}
         */
        BlockEntity put(BlockState blockState, int localX, int localY, int localZ);

        /**
         * Puts the blockEntity as the blockState.
         *
         * @param blockState   the blockState to set the BlockEntity as.
         * @param entity       the blockEntity to set the blockState as.
         * @param localX       the local X of where to put the blockEntity.
         * @param localY       the local Y of where to put the blockEntity.
         * @param localZ       the local Z of where to put the blockEntity.
         * @param callOnCreate whether to call create on the blockEntity.
         * @return a {@link BlockEntity}
         */
        BlockEntity put(BlockState blockState, BlockEntity entity, int localX, int localY, int localZ, boolean callOnCreate);

        /**
         * Iterates over each blockEntity in the chunk.
         * @param entityConsumer action to be performed.
         */
        void foreach(Consumer<BlockEntity> entityConsumer);

        /**
         * Gets the number of blockEntity in the chunk.
         */
        int getCount();

        /**
         * Checks if the chunk has any BlockEntity in it.
         */
        boolean isEmpty();
    }

    default IMeshingController getMeshingController() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    default IBlockEntityController getBlockEntityController() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
