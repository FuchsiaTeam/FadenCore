package net.fuchsia.common.data;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import net.fabricmc.loader.api.FabricLoader;
import net.fuchsia.FadenCore;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;

public class ItemValues {

    public static HashMap<Item, Integer> VALUES = new HashMap<>();

    /*
        HashMap<String, Integer> map = new HashMap<>();
        String json = FadenOnlineUtil.getJSONDataOrCache("https://raw.githubusercontent.com/FuchsiaTeam/FadenData/main/item_values.json", CACHE_PATH.toFile(), Faden.CHECKSUMS.item_values);
        map = Faden.GSON.fromJson(json, new TypeToken<HashMap<String, Integer>>(){}.getType());
        reload(map, json);
     */

    public static void load() {
        try {
            HashMap<String, Integer> map  = FadenCore.GSON.fromJson(new FileReader(FabricLoader.getInstance().getGameDir().toString() + "/faden/cache/" + FadenCore.MC_VERSION + "/item_values.json"), new TypeToken<HashMap<String, Integer>>() {}.getType());
            reload(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        new File(FabricLoader.getInstance().getGameDir().toString() + "/faden/cache/" + FadenCore.MC_VERSION + "/").mkdirs();
        try {
            HashMap<String, Integer> map = new HashMap<>();
            for (Item item : VALUES.keySet()) {
                map.put(Registries.ITEM.getId(item).toString(), VALUES.get(item));
            }
            FileUtils.writeStringToFile(new File(FabricLoader.getInstance().getGameDir().toString() + "/faden/cache/" + FadenCore.MC_VERSION + "/item_values.json"), FadenCore.GSON.toJson(map), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveClient() {
        try {
            HashMap<String, Integer> map = new HashMap<>();
            for (Item item : ItemValues.VALUES.keySet()) {
                map.put(Registries.ITEM.getId(item).toString(), ItemValues.VALUES.get(item));
            }
            FileUtils.writeStringToFile(new File(FabricLoader.getInstance().getGameDir().toString() + "/faden/cache/client/" + FadenCore.MC_VERSION + "/item_values.json"), FadenCore.GSON.toJson(map), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reload(HashMap<String, Integer> map) {
        VALUES.clear();
        for (String s : map.keySet()) {
            Identifier id = Identifier.of(s);
            if(Registries.ITEM.containsId(id)) {
                Item item = Registries.ITEM.get(id);
                VALUES.put(item, map.get(s));
            }
        }
    }
}


