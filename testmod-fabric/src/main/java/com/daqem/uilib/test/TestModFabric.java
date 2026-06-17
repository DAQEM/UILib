package com.daqem.uilib.test;

import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.test.client.gui.TestScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.gui.screens.Screen;

public class TestModFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TestMod.init();

        KeyMappingHelper.registerKeyMapping(TestMod.OPEN_TEST_MENU);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // consumeClick() returns true if the key was pressed since the last check.
            // This effectively replaces "matches()" and "keyCode == 1".
            while (TestMod.OPEN_TEST_MENU.consumeClick()) {
                Screen screen = client.gui.screen();

                if (screen instanceof TestScreen testScreen) {
                    // If focusing a validatable widget (like a text box), ignore the toggle to allow typing
                    if (testScreen.getFocused() instanceof IInputValidatable) {
                        return; // Continue without closing the screen
                    }
                    client.gui.setScreen(null);
                } else if (screen == null) {
                    client.gui.setScreen(new TestScreen());
                }
            }
        });
    }
}
