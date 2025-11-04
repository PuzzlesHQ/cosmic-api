package io.github.puzzle.cosmic.impl.mixin.util;

import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Internal
@Mixin(Identifier.class)
public abstract class IdentifierMixin implements IIdentifier {

    @Shadow public abstract String getNamespace();

    @Shadow public abstract String getName();

    @Override
    public String asString() {
        return getNamespace() + ":" + getName();
    }
}
