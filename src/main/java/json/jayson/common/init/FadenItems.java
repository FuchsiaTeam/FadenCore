package json.jayson.common.init;

import json.jayson.common.objects.item.CoinItem;
import json.jayson.common.objects.item.PouchItem;
import json.jayson.common.objects.item.TestItem;
import json.jayson.datagen.DataItemModel;
import json.jayson.datagen.FadenDataItem;
import json.jayson.util.FadenIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class FadenItems {

    public static List<FadenDataItem> ITEMS = new ArrayList<>();

    // Coins
    public static Item COPPER_COIN = registerItem("copper_coin", new CoinItem(new FabricItemSettings(), 1), "coins/copper_coin");
    public static Item IRON_COIN = registerItem("iron_coin", new CoinItem(new FabricItemSettings(), 5), "coins/iron_coin");
    public static Item ENDER_COIN = registerItem("ender_coin", new CoinItem(new FabricItemSettings(), 10), "coins/ender_coin");
    public static Item SILVER_COIN = registerItem("silver_coin", new CoinItem(new FabricItemSettings(), 50), "coins/silver_coin");
    public static Item GOLD_COIN = registerItem("gold_coin", new CoinItem(new FabricItemSettings(), 100), "coins/gold_coin");
    public static Item AMETHYST_COIN = registerItem("amethyst_coin", new CoinItem(new FabricItemSettings(), 500), "coins/amethyst_coin");
    public static Item NETHERITE_COIN = registerItem("netherite_coin", new CoinItem(new FabricItemSettings(), 1000), "coins/netherite_coin");

    // Pouches
    public static Item LEATHER_POUCH = registerItem("leather_pouch", new PouchItem(new FabricItemSettings(), 1000), "pouches/leather_pouch");

    // Resources
    public static Item SILVER_INGOT = registerItem("silver_ingot", new Item(new FabricItemSettings()), "ingots/silver_ingot");

    // Functional
    public static Item RANDOM_COIN_GIVER = registerItem("random_coin_giver", new TestItem(new FabricItemSettings()), "");

    private static Item registerItem(String name, Item item, String texture, DataItemModel itemModel) {
        Item i = Registry.register(Registries.ITEM, FadenIdentifier.create(name), item);
        ITEMS.add(new FadenDataItem(i, texture, itemModel));
        return i;
    }

    private static Item registerItem(String name, Item item, String texture) {
        return registerItem(name, item, texture, DataItemModel.GENERATED);
    }

    public static void register() {}

}
