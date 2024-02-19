package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancementTree;
import com.daqem.uilib.client.gui.component.texture.RepeatingTextureComponent;
import com.daqem.uilib.client.gui.texture.Texture;

public class AdvancementBackgroundComponent extends RepeatingTextureComponent {

    public AdvancementBackgroundComponent(int x, int y, int width, int height, IAdvancementTree advancementTree) {
        super(new Texture(advancementTree.getBackgroundTexture(), 0, 0, 32, 32, 32), x, y, width, height);
    }
}
