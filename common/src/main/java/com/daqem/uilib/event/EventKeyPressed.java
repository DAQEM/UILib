package com.daqem.uilib.event;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.test.TestScreen;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import net.minecraft.client.gui.screens.Screen;

public class EventKeyPressed {

    public static void registerEvent() {
        ClientRawInputEvent.KEY_PRESSED.register((client, keyCode, scanCode, action, modifiers) -> {
            Screen screen = client.screen;
            if (UILib.OPEN_TEST_MENU.matches(keyCode, scanCode) && action == 1) {
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
