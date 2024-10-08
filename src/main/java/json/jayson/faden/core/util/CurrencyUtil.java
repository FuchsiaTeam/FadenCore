package json.jayson.faden.core.util;

import json.jayson.faden.core.common.objects.CoinMap;
import json.jayson.faden.core.common.objects.item.coin.CoinItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyUtil {

    public static class Currency {
        public static void sort(World world, BlockPos pos, Inventory inventory) {
            if(CoinMap.getCoinMap().isEmpty()) return;
            int amount = count(inventory);
            remove(world, pos, inventory, amount, true);
            add(world, pos, inventory, amount);
        }


        public static void removeAll(Inventory inventory) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.getStack(i).getItem() instanceof CoinItem) {
                    inventory.setStack(i, Items.AIR.getDefaultStack());
                }
            }
        }

        /*
         * ordered is faster, but gives higher coins
         */
        public static void remove(World world, BlockPos pos, Inventory inventory, int amount, boolean order) {
            if(CoinMap.getCoinMap().isEmpty()) return;
            if (order) {
                int currentAmount = count(inventory);
                removeAll(inventory);
                add(world, pos, inventory, currentAmount - amount);
            } else {
                for (int i = 0; i < inventory.size(); i++) {
                    if (amount == 0) {
                        break;
                    }
                    if (inventory.getStack(i).getItem() instanceof CoinItem coinItem) {
                        ItemStack itemStack = inventory.getStack(i);
                        int value = coinItem.getValue();
                        int count = itemStack.getCount();
                        int total = value * count;
                        if (amount >= total) {
                            inventory.setStack(i, Items.AIR.getDefaultStack());
                            amount -= total;
                        } else {
                            int numCoins = Math.floorDiv(amount, value);
                            itemStack.setCount(count - numCoins);
                            inventory.setStack(i, itemStack);
                            amount -= (numCoins*value);
                        }
                    }
                }
                if(amount > 0){
                    for (int i = 0; i < inventory.size(); i++) {
                        if (inventory.getStack(i).getItem() instanceof CoinItem coinItem) {
                            ItemStack itemStack = inventory.getStack(i);
                            int value = coinItem.getValue();
                            int count = itemStack.getCount();
                            if (amount < value) {
                                itemStack.setCount(count - 1);
                                inventory.setStack(i, itemStack);
                                amount -= value;
                                add(world, pos, inventory, -amount);
                                break;
                            }
                        }
                    }
                }
            }
        }

        public static void add(PlayerEntity player, int amount) {
            add(player.getWorld(), player.getBlockPos(), player.getInventory(), amount);
        }

        public static void add(World world, BlockPos pos, Inventory inventory, int amount) {
            if(CoinMap.getCoinMap().isEmpty()) return;
            if(amount < 1) return;
            Map<Item, Integer> itemStacks = Coin.generateCoins(amount);
            boolean drop = false;
            for (Item item : itemStacks.keySet()) {
                ItemStack itemStack = item.getDefaultStack();
                itemStack.setCount(itemStacks.get(item));
                if(inventory instanceof PlayerInventory playerInventory) {
                    drop = playerInventory.getEmptySlot() == -1;
                    if(!drop) {
                        playerInventory.insertStack(itemStack);
                    }
                } else {
                    for (int i = 0; i < inventory.size(); i++) {
                        if(i >= inventory.size()) {
                            drop = true;
                        }
                        if (inventory.getStack(i) == null || inventory.getStack(i).getItem() == Items.AIR) {
                            inventory.setStack(i, itemStack);
                            break;
                        }
                    }
                }
                if(drop && world != null & pos != null) {
                    world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
                }
            }
        }

        /*
         * Counts Currency Value of the Inventory
         * */
        public static int count(Inventory inventory) {
            if(CoinMap.getCoinMap().isEmpty()) return 0;
            int amount = 0;
            for (Item value : CoinMap.getCoinMap().values()) {
                amount += inventory.count(value) * Coin.getValue(value);
            }
            return amount;
        }


    }

    public static class Coin {
        public static void drop(World world, BlockPos pos, int amount) {
            if(CoinMap.getCoinMap().isEmpty()) return;
            if(amount < 1) return;
            Map<Item, Integer> itemStacks = Coin.generateCoins(amount);
            for (Item item : itemStacks.keySet()) {
                ItemStack itemStack = item.getDefaultStack();
                itemStack.setCount(itemStacks.get(item));
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
            }
        }

        /*
         * Counts which Coints and which Amount the player has in Inventory
         * */
        public static TreeMap<Item, Integer> count(Inventory inventory) {
            if(CoinMap.getCoinMap().isEmpty()) new TreeMap<>();
            TreeMap<Item, Integer> coinCounts = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                Item item = itemStack.getItem();
                if (item instanceof CoinItem) {
                    if(coinCounts.containsKey(item)){
                        coinCounts.put(item, coinCounts.get(item)+itemStack.getCount());
                    }
                    else{
                        coinCounts.put(item, itemStack.getCount());
                    }
                }
            }
            return coinCounts;
        }

        /*
         * Generates the ItemStacks of Coins given the Currency Amount
         * */
        public static Map<Item, Integer> generateCoins(int amount) {
            if(CoinMap.getCoinMap().isEmpty()) return new HashMap<>();
            Map<Item, Integer> itemStacks = new HashMap<>();
            while(amount > 0) {
                for (Integer i : CoinMap.getCoinMap().keySet()) {
                    if(i <= amount) {
                        itemStacks.put(CoinMap.getCoinMap().get(i), itemStacks.getOrDefault(CoinMap.getCoinMap().get(i), 0) + 1);
                        amount -= i;
                        break;
                    }
                }
            }
            return itemStacks;
        }


        /*
         * Returns Currency Value of the Coin
         * */
        public static Integer getValue(Item value) {
            for (Map.Entry<Integer, Item> entry : CoinMap.getCoinMap().entrySet()) {
                if(entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
            return 0;
        }

    }

}