package com.daqem.uilib.tests.components;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPanelComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollWheelComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContentComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScrollComponentTests {

    private ScrollPanelComponent scrollPanelComponent;
    private ScrollBarComponent scrollBarComponent;
    private ScrollContentComponent scrollContentComponent;

    @BeforeEach
    void setUp() {
        ScrollOrientation orientation = ScrollOrientation.VERTICAL;
        this.scrollPanelComponent = new ScrollPanelComponent(Textures.SCROLL_PANE, 0, 0, 100, 100, orientation);
        this.scrollBarComponent = new ScrollBarComponent(0, 0, 100, 100, orientation);
        this.scrollContentComponent = new ScrollContentComponent(0, 0, 5, orientation);
    }

    @Test
    void setScrollBar() {
        //Act
        scrollPanelComponent.setScrollBar(scrollBarComponent);

        //Assert
        assertNotNull(scrollPanelComponent.getScrollBar());
        assertEquals(scrollBarComponent, scrollPanelComponent.getScrollBar());
    }

    @Test
    void removeScrollBar() {
        //Arrange
        scrollPanelComponent.setScrollBar(scrollBarComponent);

        //Act
        scrollPanelComponent.removeScrollBar();

        //Assert
        assertNull(scrollPanelComponent.getScrollBar());
    }

    @Test
    void setBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getSolidScrollBarBackground(scrollBarComponent);

        //Act
        scrollBarComponent.setBackground(background);

        //Assert
        assertEquals(background, scrollBarComponent.getBackground());
    }

    @Test
    void removeBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getSolidScrollBarBackground(scrollBarComponent);
        scrollBarComponent.setBackground(background);

        //Act
        scrollBarComponent.removeBackground();

        //Assert
        assertNull(scrollBarComponent.getBackground());
    }

    @Test
    void ScrollBarComponentSetScrollBar() {
        //Arrange
        ScrollWheelComponent scrollWheelComponent = new ScrollWheelComponent(Textures.SCROLL_BAR, 0, 0, 100);

        //Act
        scrollBarComponent.setScrollBar(scrollWheelComponent);

        //Assert
        assertNotNull(scrollBarComponent.getScrollWheel());
        assertEquals(scrollWheelComponent, scrollBarComponent.getScrollWheel());
    }

    @Test
    void setScrollContent() {
        //Act
        scrollPanelComponent.setScrollContentComponent(scrollContentComponent);

        //Assert
        assertNotNull(scrollPanelComponent.getScrollContentComponent());
        assertEquals(scrollContentComponent, scrollPanelComponent.getScrollContentComponent());
    }
}
