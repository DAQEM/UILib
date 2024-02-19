package com.daqem.uilib.api.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.tab.ITabInformation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public interface IAdvancementTree extends ITabInformation {

    Optional<IAdvancement> getRoot();
    ResourceLocation getBackgroundTexture();

    Component getName();
}
