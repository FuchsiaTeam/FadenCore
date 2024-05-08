package net.fuchsia.common.objects.item.gear.bracelet;

import net.fuchsia.common.objects.item.FadenItem;
import net.fuchsia.common.objects.item.ItemTier;
import net.fuchsia.common.objects.item.gear.FadenGearItem;
import net.fuchsia.common.objects.item.gear.Gear;
import net.fuchsia.common.slot.GearSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collection;

public abstract class BraceletItem extends FadenGearItem {

    public BraceletItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public GearSlot getGearType() {
        return GearSlot.BRACELET;
    }


    @Override
    public ItemStack upgrade(Collection<ItemStack> inputs, ItemStack itemStack) {
        return null;
    }
}
