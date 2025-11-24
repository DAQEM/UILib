package com.daqem.uilib.test.neoforge;

import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.test.TestMod;
import com.daqem.uilib.test.client.gui.TestScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@Mod(value = TestMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TestMod.MOD_ID, value = Dist.CLIENT)
public class TestModNeoForge {

    public TestModNeoForge() {
        TestMod.init();
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // Check if the action is a Press (1)
        if (event.getAction() == GLFW.GLFW_PRESS) {
            // Check if the key matches the configured KeyMapping
            if (TestMod.OPEN_TEST_MENU.matches(event.getKeyEvent())) {
                Minecraft client = Minecraft.getInstance();
                Screen screen = client.screen;

                if (screen instanceof TestScreen testScreen) {
                    // If focusing a validatable widget (like a text box), ignore the toggle to allow typing
                    if (testScreen.getFocused() instanceof IInputValidatable) {
                        return;
                    }
                    client.setScreen(null);
                } else if (screen == null) {
                    client.setScreen(new TestScreen());
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(TestMod.OPEN_TEST_MENU);
    }
}
