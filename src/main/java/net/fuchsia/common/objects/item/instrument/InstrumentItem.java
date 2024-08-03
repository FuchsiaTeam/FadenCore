package net.fuchsia.common.objects.item.instrument;

import net.fuchsia.common.init.FadenDataComponents;
import net.fuchsia.common.init.FadenMusicInstances;
import net.fuchsia.common.init.FadenSoundEvents;
import net.fuchsia.common.objects.music_instance.InstrumentedMusic;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.lwjgl.openal.AL10;

import java.util.Random;

public class InstrumentItem extends Item {
    public InstrumentItem(Settings settings) {
        super(settings);
    }
    AbstractSoundInstance music = PositionedSoundInstance.music(FadenSoundEvents.FADEN);

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack handItem = user.getStackInHand(hand);
        if(handItem.contains(FadenDataComponents.MUSIC_INSTANCE)) {
            for (Entity otherEntity : world.getOtherEntities(user, new Box(user.getBlockPos()))) {
                if(otherEntity instanceof PlayerEntity player) {
                    ItemStack otherStack = player.getMainHandStack();
                    if(otherStack.getItem() instanceof InstrumentItem instrumentItem) {
                        if (!otherStack.contains(FadenDataComponents.MUSIC_INSTANCE)) {
                            InstrumentedMusic instance = FadenMusicInstances.getMusic(handItem.get(FadenDataComponents.MUSIC_INSTANCE));
                            if (instance != null) {
                                if(instance.getInstrumentTypes().contains(instrumentItem.getInstrumentType())) {
                                    otherStack.set(FadenDataComponents.MUSIC_INSTANCE, handItem.get(FadenDataComponents.MUSIC_INSTANCE));
                                }
                            }
                        }
                    }
                }
            }
        } else {
            handItem.set(FadenDataComponents.MUSIC_INSTANCE, "");
        }

        InstrumentedMusic instance = FadenMusicInstances.getMusic(handItem.get(FadenDataComponents.MUSIC_INSTANCE));

        MinecraftClient.getInstance().getSoundManager().play(music);

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity player) {
            if(stack.isOf(player.getMainHandStack().getItem())) {
                stack.remove(FadenDataComponents.MUSIC_INSTANCE);
            }
        }
        Channel.SourceManager source = MinecraftClient.getInstance().getSoundManager().soundSystem.sources.get(music);
        if(source != null) {
            source.run(this::testFunc);
        }
    }

    public void testFunc(Source source) {
        AL10.alSourcef(source.pointer, 4106, 0f);
        AL10.alSourcef(source.pointer, AL10.AL_GAIN, 0f);
    }

    public InstrumentType getInstrumentType() {
        return InstrumentType.FLUTE;
    }
}
