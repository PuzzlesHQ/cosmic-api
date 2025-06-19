package io.github.puzzle.cosmic.impl.mixin.gdx;

import com.badlogic.gdx.utils.Array;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.NoSuchElementException;

@Mixin(Array.ArrayIterator.class)
public class ArrayIterator<T> {

    @Shadow(remap = false) private int index;

    @Shadow(remap = false) @Final private Array<T> array;

    /**
     * @author Mr_Zombii
     * @reason Fuck you libgdx
     */
    @Overwrite(remap = false)
    public boolean hasNext() {
        return this.index < this.array.size;
    }

    /**
     * @author Mr_Zombii
     * @reason Fuck you libgdx
     */
    @Overwrite(remap = false)
    public T next() {
        if (this.index >= this.array.size) {
            throw new NoSuchElementException(String.valueOf(this.index));
        } else {
            return this.array.items[this.index++];
        }
    }

}
