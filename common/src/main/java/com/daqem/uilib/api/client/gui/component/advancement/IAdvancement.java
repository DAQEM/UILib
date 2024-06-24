package com.daqem.uilib.api.client.gui.component.advancement;

import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public interface IAdvancement {

    Optional<IAdvancement> getParent();
    List<IAdvancement> getChildren();
    void addChild(IAdvancement advancement);

    ItemStack getIcon();
    Component getName();
    List<Component> getDescription();
    boolean isObtained();
    FrameType getFrameType();
}
