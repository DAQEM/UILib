package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.SolidColorComponent;
import com.daqem.uilib.client.gui.component.advancement.AdvancementHoverComponent;
import com.daqem.uilib.client.gui.text.Text;
import com.daqem.uilib.client.gui.texture.Textures;
import com.daqem.uilib.client.screen.test.components.TestBackgroundComponent;
import net.minecraft.advancements.FrameType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    TestBackgroundComponent testBackgroundComponent;
    AdvancementHoverComponent advancementHoverComponent;

    @Override
    public void startScreen() {
        this.setPauseScreen(false);
        this.setBackground(Backgrounds.getDefaultBackground(this.getWidth(), this.getHeight()));

        this.testBackgroundComponent = new TestBackgroundComponent();
        this.advancementHoverComponent = new AdvancementHoverComponent(75, 75, font, new ItemStack(Items.DIAMOND_PICKAXE), Component.literal("Test22222222222222222222222222222"), Component.literal("this is a very nice and long description to test the advancement thingy the thing thing you know? I have no idea what the hell I'm doing im just typing stuff and hoping it make sense lol"), AdvancementWidgetType.UNOBTAINED, FrameType.CHALLENGE);

        advancementHoverComponent.center();

        addComponents(advancementHoverComponent);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
    }
}
