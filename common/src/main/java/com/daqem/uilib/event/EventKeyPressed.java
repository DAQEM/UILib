package com.daqem.uilib.event;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.test.TestScreen;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;

public class EventKeyPressed {

    public static void registerEvent() {
        ClientRawInputEvent.KEY_PRESSED.register((client, keyCode, keyEvent) -> {
            Screen screen = client.screen;
            if (UILib.OPEN_TEST_MENU.matches(keyEvent) && keyCode == 1) {
                if (screen instanceof TestScreen testScreen) {
                    if (testScreen.getFocused() instanceof IInputValidatable) {
                        return EventResult.pass();
                    }
                    client.setScreen(null);
                }
                else if (screen == null) client.setScreen(new TestScreen());
            }
            return EventResult.pass();
        });
    }
}
