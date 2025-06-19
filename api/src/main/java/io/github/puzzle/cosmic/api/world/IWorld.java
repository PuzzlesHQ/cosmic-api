package io.github.puzzle.cosmic.api.world;

import finalforeach.cosmicreach.world.Zone;
import finalforeach.cosmicreach.worldgen.ZoneGenerator;
import io.github.puzzle.cosmic.util.annotation.Note;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@Note("A lot of zone addition or creation methods on the world class got redirected/relocated into the new class called IZoneMap for API cleanliness.")
@ApiGen("World")
public interface IWorld {

    /**
     * Gets the zone map.
     * @return a {@link IZoneMap}
     */
    IZoneMap getZoneMap();

    interface IZoneMap {

        /**
         * Gets the zone of the given zone ID.
         *
         * @param zoneId the zone ID to get.
         * @return a {@link Zone}
         */
        Zone get(String zoneId);

        /**
         * Gets or create the zone of the given zone ID.
         *
         * @param zoneId the zone ID to get or create.
         * @return a {@link Zone}
         */
        Zone getOrCreate(String zoneId);

        /**
         * Adds a zone to the world.
         * @param zone the zone to be added.
         */
        void put(Zone zone);

        /**
         * Adds the zoneGenerator to the world.
         * @param zoneId the ID of the zoneGenerator.
         * @param zoneGenerator the zoneGenerator to add.
         */
        void put(String zoneId, ZoneGenerator zoneGenerator);

        /**
         * Gets all the zoneIDs in the world.
         * @return a {@link Set<String>} of Strings.
         */
        Set<String> getKeys();

        /**
         * Gets all the zones in the world.
         * @return a {@link Collection<Zone>} of Zone.
         */
        Collection<Zone> getValues();

    }

}
