package com.daqem.uilib.test.component;

import com.daqem.uilib.api.skilltree.ISkillTreeItem;
import com.daqem.uilib.api.widget.skilltree.ISkillTreeItemWidget;
import com.daqem.uilib.gui.component.skilltree.SkillTreeComponent;
import com.daqem.uilib.gui.component.sprite.SpriteComponent;
import com.daqem.uilib.gui.widget.CustomButtonWidget;
import com.daqem.uilib.skilltree.AbstractSkillTree;
import com.daqem.uilib.skilltree.AbstractSkillTreeItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TestSkillTreeComponent extends SpriteComponent {

    public TestSkillTreeComponent() {
        super(0, 0, 200, 200, ResourceLocation.withDefaultNamespace("recipe_book/overlay_recipe"));

        TestSkillTreeItem rootItem = new TestSkillTreeItem(true, new ArrayList<>());
        TestSkillTreeItem childItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem childItem2 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandChildItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandChildItem2 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandGrandChildItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandGrandGrandChildItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandGrandGrandGrandChildItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        TestSkillTreeItem grandGrandGrandGrandGrandChildItem1 = new TestSkillTreeItem(false, new ArrayList<>());
        rootItem.addChild(childItem1);
        rootItem.addChild(childItem2);
        childItem2.addChild(grandChildItem1);
        childItem2.addChild(grandChildItem2);
        grandChildItem1.addChild(grandGrandChildItem1);
        grandGrandChildItem1.addChild(grandGrandGrandChildItem1);
        grandGrandGrandChildItem1.addChild(grandGrandGrandGrandChildItem1);
        grandGrandGrandGrandChildItem1.addChild(grandGrandGrandGrandGrandChildItem1);

        List<ISkillTreeItem> items = new ArrayList<>(List.of(rootItem, childItem1, childItem2, grandChildItem1, grandChildItem2,
                grandGrandChildItem1, grandGrandGrandChildItem1, grandGrandGrandGrandChildItem1, grandGrandGrandGrandGrandChildItem1));
        TestSkillTree skillTree = new TestSkillTree(items);

        SkillTreeComponent skillTreeComponent = new SkillTreeComponent(10, 10, 180, 180, skillTree);
        this.addComponent(skillTreeComponent);
    }

    public static class TestSkillTree extends AbstractSkillTree {

        public TestSkillTree(List<ISkillTreeItem> items) {
            super(items);
        }
    }

    public static class TestSkillTreeItem extends AbstractSkillTreeItem {

        public TestSkillTreeItem(boolean isRoot, List<ISkillTreeItem> items) {
            super(isRoot, items);
        }

        @Override
        public ISkillTreeItemWidget createWidget() {
            return new TestSkillTreeItemWidget(this);
        }
    }

    public static class TestSkillTreeItemWidget extends CustomButtonWidget implements ISkillTreeItemWidget {

        private final ISkillTreeItem skillTreeItem;

        public TestSkillTreeItemWidget(ISkillTreeItem skillTreeItem) {
            super(0, 0, 32, 32, Component.empty(), new WidgetSprites(
                    ResourceLocation.withDefaultNamespace("advancements/task_frame_obtained"),
                    ResourceLocation.withDefaultNamespace("advancements/task_frame_unobtained")
            ));
            this.skillTreeItem = skillTreeItem;
        }

        @Override
        public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
            guiGraphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    ResourceLocation.withDefaultNamespace("advancements/title_box"),
                    getX() - 5,
                    getY() + 5,
                    getWidth() + 100,
                    getHeight() + 30
            );
            guiGraphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    ResourceLocation.withDefaultNamespace("advancements/task_frame_obtained"),
                    getX(),
                    getY(),
                    getWidth(),
                    getHeight()
            );
        }

        @Override
        public ISkillTreeItem getSkillTreeItem() {
            return this.skillTreeItem;
        }
    }
}
