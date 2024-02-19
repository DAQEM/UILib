package com.daqem.uilib.api.client.gui.component.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public interface ITabInformation {

    ItemStack getIcon();
    Component getName();
}
