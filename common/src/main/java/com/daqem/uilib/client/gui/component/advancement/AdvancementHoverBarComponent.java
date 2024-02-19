package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.client.gui.component.texture.NineSlicedTextureComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;

public class AdvancementHoverBarComponent extends NineSlicedTextureComponent {

    public AdvancementHoverBarComponent(int x, int y, int width, AdvancementWidgetType type) {
        super(getTexture(type), x, y, width, 26);
    }

    private static INineSlicedTexture getTexture(AdvancementWidgetType type) {
        return switch (type) {
            case OBTAINED -> Textures.Advancement.ADVANCEMENT_HOVER_BAR_OBTAINED;
            case UNOBTAINED -> Textures.Advancement.ADVANCEMENT_HOVER_BAR_UNOBTAINED;
        };
    }
}
