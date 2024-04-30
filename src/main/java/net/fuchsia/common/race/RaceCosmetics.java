package net.fuchsia.common.race;

import net.fuchsia.common.race.cosmetic.RaceCosmetic;
import net.fuchsia.common.race.cosmetic.RaceCosmeticPalette;
import net.fuchsia.common.race.cosmetic.RaceCosmeticType;
import net.fuchsia.util.FadenIdentifier;
import net.minecraft.client.util.ModelIdentifier;

public class RaceCosmetics {

    public static RaceCosmeticPalette HARENGON = new RaceCosmeticPalette();

    public static void add() {
        HARENGON.addCosmetic("brown", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_brown"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("brown", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_brown"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("black", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_black"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("black", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_black"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("gold", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_gold"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("gold", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_gold"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("salt", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_salt"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("salt", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_salt"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("toast", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_toast"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("toast", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_toast"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("white", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_white"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("white", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_white"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("white_splotched", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/harengon_ears_white_splotched"), RaceCosmeticType.HEAD, "ear_0"));
        HARENGON.addCosmetic("white_splotched", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/ears/floppy/harengon_ears_floopy_white_splotched"), RaceCosmeticType.HEAD, "ear_1"));

        HARENGON.addCosmetic("brown", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_brown"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("black", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_black"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("gold", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_gold"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("salt", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_salt"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("toast", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_toast"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("white", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_white"), RaceCosmeticType.CHEST, "tail_0"));
        HARENGON.addCosmetic("white_splotched", new RaceCosmetic(FadenIdentifier.modelId("player_cosmetic/harengon/tail/harengon_tail_white_splotched"), RaceCosmeticType.CHEST, "tail_0"));

    }

}
