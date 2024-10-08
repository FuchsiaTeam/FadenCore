package json.jayson.faden.core.common.race.skin.client;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class SkinTexture extends ResourceTexture {
    byte[] skinData = null;

    public SkinTexture(Identifier location) {
        super(location);
    }

    private void onTextureLoaded(NativeImage image) {
        MinecraftClient.getInstance().execute(() -> {
            if (!RenderSystem.isOnRenderThread()) {
                RenderSystem.recordRenderCall(() -> {
                    this.uploadTexture(image);
                });
            } else {
                this.uploadTexture(image);
            }

        });
    }

    public void setSkinData(byte[] skinData) {
        this.skinData = skinData;
    }

    private void uploadTexture(NativeImage image) {
        TextureUtil.prepareImage(this.getGlId(), image.getWidth(), image.getHeight());
        image.upload(0, 0, 0, true);
    }


    public void load(ResourceManager manager) {
        if(skinData != null) {
            MinecraftClient.getInstance().execute(() -> {
                NativeImage nativeImage = this.loadTexture(skinData);
                if (nativeImage != null) {
                    onTextureLoaded(nativeImage);
                }
            });
        }
    }

    @Nullable
    private NativeImage loadTexture(byte[] bytes) {
        NativeImage nativeImage = null;
        try {
            nativeImage = NativeImage.read(bytes);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return nativeImage;
    }

}


