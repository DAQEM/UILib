package com.daqem.uilib.api.client.gui;

import org.jetbrains.annotations.Nullable;

public interface ICloneable extends Cloneable {

    @Nullable Object getClone();
}
