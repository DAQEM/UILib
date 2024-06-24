package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestAdvancement implements IAdvancement {

    private final @Nullable IAdvancement parent;
    private final List<IAdvancement> children = new ArrayList<>();

    public TestAdvancement(@Nullable IAdvancement parent) {
        this.parent = parent;
    }

    @Override
    public Optional<IAdvancement> getParent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public List<IAdvancement> getChildren() {
        return children;
    }

    public void addChild(IAdvancement advancement) {
        children.add(advancement);
    }

    @Override
    public ItemStack getIcon() {
        return Items.ACACIA_LOG.getDefaultInstance();
    }

    @Override
    public Component getName() {
        return Component.literal("Test Advancement");
    }

    @Override
    public List<Component> getDescription() {
        return List.of(Component.literal("This is a test advancement"));
    }

    @Override
    public boolean isObtained() {
        return Math.random() > 0.5;
    }

    @Override
    public FrameType getFrameType() {
        return FrameType.TASK;
    }
}
