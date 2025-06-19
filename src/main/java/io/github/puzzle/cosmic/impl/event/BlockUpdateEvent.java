package io.github.puzzle.cosmic.impl.event;

import finalforeach.cosmicreach.blocks.BlockPosition;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockUpdateEvent implements IBlockUpdateEvent {

    BlockPosition position;
    Object object;

    public BlockUpdateEvent() {
        this.position = null;
        this.object = null;
    }

    public BlockUpdateEvent(Object o) {
        this.position = null;
        this.object = o;
    }

    @Override
    public @NotNull BlockPosition getSourcePosition() {
        if (this.position == null) throw new NullPointerException("Do not call IBlockUpdateEvent.getSourcePosition until it has been sent to other blocks via \"IBlockPosition._updateNeighbors\", \"IBlockPosition._updateNeighborInDirection\", \"IBlockEntity._updateNeighbors\", or \"IBlockEntity._updateNeighborInDirection\" or set using \"BlockUpdateEvent.setSourcePosition\"");
        return this.position;
    }

    @Override
    public @Nullable Object getAttachedObject() {
        return object;
    }

    @Override
    public void setSourcePosition(@NotNull BlockPosition blockPosition) {
        this.position = blockPosition;
    }

    @Override
    public void setAttachedObject(@Nullable Object o) {
        this.object = o;
    }
}
