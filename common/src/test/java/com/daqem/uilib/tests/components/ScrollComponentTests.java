package com.daqem.uilib.tests.components;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.scroll.ScrollBar;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScrollComponentTests {

    private ScrollPaneComponent scrollPaneComponent;
    private ScrollBarComponent scrollBarComponent;
    private ScrollContent scrollContent;

    @BeforeEach
    void setUp() {
        this.scrollPaneComponent = new ScrollPaneComponent(Textures.SCROLL_PANE, 0, 0, 100, 100);
        this.scrollBarComponent = new ScrollBarComponent(0, 0, 100, 100);
        this.scrollContent = new ScrollContent(0, 0, 5, ScrollOrientation.VERTICAL);
    }

    @Test
    void setScrollBar() {
        //Act
        scrollPaneComponent.setScrollBar(scrollBarComponent);

        //Assert
        assertNotNull(scrollPaneComponent.getScrollBar());
        assertEquals(scrollBarComponent, scrollPaneComponent.getScrollBar());
    }

    @Test
    void removeScrollBar() {
        //Arrange
        scrollPaneComponent.setScrollBar(scrollBarComponent);

        //Act
        scrollPaneComponent.removeScrollBar();

        //Assert
        assertNull(scrollPaneComponent.getScrollBar());
    }

    @Test
    void setBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getScrollBarBackground(1920, 1080);

        //Act
        scrollBarComponent.setBackground(background);

        //Assert
        assertEquals(background, scrollBarComponent.getBackground());
    }

    @Test
    void removeBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getScrollBarBackground(1920, 1080);
        scrollBarComponent.setBackground(background);

        //Act
        scrollBarComponent.removeBackground();

        //Assert
        assertNull(scrollBarComponent.getBackground());
    }

    @Test
    void ScrollBarComponentSetScrollBar() {
        //Arrange
        ScrollBar scrollBar = new ScrollBar(Textures.SCROLL_BAR, 0, 0, 100);

        //Act
        scrollBarComponent.setScrollBar(scrollBar);

        //Assert
        assertNotNull(scrollBarComponent.getScrollBar());
        assertEquals(scrollBar, scrollBarComponent.getScrollBar());
    }

    @Test
    void setScrollContent() {
        //Act
        scrollPaneComponent.setContent(scrollContent);

        //Assert
        assertNotNull(scrollPaneComponent.getContent());
        assertEquals(scrollContent, scrollPaneComponent.getContent());
    }
}
