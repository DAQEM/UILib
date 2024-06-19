package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancement;
import com.daqem.uilib.api.client.gui.component.advancement.IAdvancementTree;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class TestAdvancementTree implements IAdvancementTree {

    @Override
    public ItemStack getIcon() {
        return Items.ACACIA_LOG.getDefaultInstance();
    }

    private final IAdvancement root = new TestAdvancement(null);
    private final IAdvancement child1 = new TestAdvancement(root);
    private final IAdvancement child2 = new TestAdvancement(root);
    private final IAdvancement child3 = new TestAdvancement(root);
    private final IAdvancement childChild1 = new TestAdvancement(child1);
    private final IAdvancement childChild2 = new TestAdvancement(child1);
    private final IAdvancement childChild3 = new TestAdvancement(child1);
    private final IAdvancement childChild4 = new TestAdvancement(child1);
    private final IAdvancement childChild5 = new TestAdvancement(child1);
    private final IAdvancement childChildChild1 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild2 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild3 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild4 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild5 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild6 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild7 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChild8 = new TestAdvancement(childChild2);
    private final IAdvancement childChildChildChild1 = new TestAdvancement(childChildChild2);
    private final IAdvancement childChildChildChildChild1 = new TestAdvancement(childChildChildChild1);
    private final IAdvancement childChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChildChildChildChildChild1);
    private final IAdvancement childChildChildChildChildChildChildChildChildChildChildChildChildChildChild1 = new TestAdvancement(childChildChildChildChildChildChildChildChildChildChildChildChildChild1);

    {
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        child1.addChild(childChild1);
        child1.addChild(childChild2);
        child1.addChild(childChild3);
        child1.addChild(childChild4);
        child1.addChild(childChild5);
        childChild2.addChild(childChildChild1);
        childChild2.addChild(childChildChild2);
        childChild2.addChild(childChildChild3);
        childChild2.addChild(childChildChild4);
        childChild2.addChild(childChildChild5);
        childChild2.addChild(childChildChild6);
        childChild2.addChild(childChildChild7);
        childChild2.addChild(childChildChild8);
        childChildChild2.addChild(childChildChildChild1);
        childChildChildChild1.addChild(childChildChildChildChild1);
        childChildChildChildChild1.addChild(childChildChildChildChildChild1);
        childChildChildChildChildChild1.addChild(childChildChildChildChildChildChild1);
        childChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChildChildChildChildChild1);
        childChildChildChildChildChildChildChildChildChildChildChildChildChild1.addChild(childChildChildChildChildChildChildChildChildChildChildChildChildChildChild1);
    }

    @Override
    public Optional<IAdvancement> getRoot() {
        return Optional.of(root);
    }

    @Override
    public ResourceLocation getBackgroundTexture() {
        return ResourceLocation.parse("textures/block/stone.png");
    }

    @Override
    public Component getName() {
        return Component.nullToEmpty("Test Advancement Tree");
    }
}
