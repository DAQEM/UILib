package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.advancements.FrameType;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;

public class AdvancementIconComponent extends TextureComponent {

    public AdvancementIconComponent(int x, int y, AdvancementWidgetType type, FrameType frameType) {
        super(getTexture(type, frameType), x, y, 26, 26);
    }

    private static ITexture getTexture(AdvancementWidgetType type, FrameType frameType) {
        return switch (type) {
            case OBTAINED -> switch (frameType) {
                case TASK -> Textures.Advancement.ADVANCEMENT_ICON_TASK_OBTAINED;
                case CHALLENGE -> Textures.Advancement.ADVANCEMENT_ICON_CHALLENGE_OBTAINED;
                case GOAL -> Textures.Advancement.ADVANCEMENT_ICON_GOAL_OBTAINED;
            };
            case UNOBTAINED -> switch (frameType) {
                case TASK -> Textures.Advancement.ADVANCEMENT_ICON_TASK_UNOBTAINED;
                case CHALLENGE -> Textures.Advancement.ADVANCEMENT_ICON_CHALLENGE_UNOBTAINED;
                case GOAL -> Textures.Advancement.ADVANCEMENT_ICON_GOAL_UNOBTAINED;
            };
        };
    }
}
