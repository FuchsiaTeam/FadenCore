package net.fuchsia.common.objects.tooltip;

import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import net.fuchsia.common.objects.item.ItemToolTipEntryRenderer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public interface ToolTipEntry {

    @Nullable
    default Item getItem(FadenTooltipComponent component) {
        return null;
    }
    @Nullable
    default Identifier getTexture(FadenTooltipComponent component) {
        return null;
    }
    default int getTextureScale() {
        return 16;
    }
    Text getText(FadenTooltipComponent component);

    default int getTextColor(FadenTooltipComponent component) {
        return 0xAFFFFFFF;
    }

    default int getTextBackgroundColor(FadenTooltipComponent component) {
        return 0;
    }

    default void renderItem(ItemToolTipEntryRenderer entryRenderer, FadenTooltipComponent component, TextRenderer textRenderer, int x, int y, DrawContext context) {
        context.getMatrices().push();
        context.getMatrices().scale(0.5f, 0.5f,0.5f);
        Item item = getItem(component);
        Identifier identifier = getTexture(component);

        if(item != null) {
            context.drawItem(item.getDefaultStack(), x * 2, y * 2);
        }

        if(identifier != null) {
            context.drawTexture(identifier, x * 2, y * 2, 0, 0, 16, 16, getTextureScale(), getTextureScale());
        }
        context.getMatrices().pop();
    }

    default void renderText(FadenTooltipComponent component, TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        int xOffset = getItem(component) != null || getTexture(component) != null ? 10 : 0;
        textRenderer.draw(getText(component), x + xOffset, y, getTextColor(component), true, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, getTextBackgroundColor(component), 15728880);
    }


}
