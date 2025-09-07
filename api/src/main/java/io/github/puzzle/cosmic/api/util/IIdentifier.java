package io.github.puzzle.cosmic.api.util;


/**
 *
 * @author Mr_Zombii, replet
 * @since 0.3.26
 */
public interface IIdentifier {

    default String asString() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
