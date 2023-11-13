package com.daqem.uilib.tests.components;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.scroll.ScrollBar;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarWrapper;
import com.daqem.uilib.client.gui.component.scroll.ScrollContent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScrollComponentTests {

    private ScrollPaneComponent scrollPaneComponent;
    private ScrollBarWrapper scrollBarWrapper;
    private ScrollContent scrollContent;

    @BeforeEach
    void setUp() {
        ScrollOrientation orientation = ScrollOrientation.VERTICAL;
        this.scrollPaneComponent = new ScrollPaneComponent(Textures.SCROLL_PANE, 0, 0, 100, 100, orientation);
        this.scrollBarWrapper = new ScrollBarWrapper(0, 0, 100, 100, orientation);
        this.scrollContent = new ScrollContent(0, 0, 5, orientation);
    }

    @Test
    void setScrollBar() {
        //Act
        scrollPaneComponent.setScrollBar(scrollBarWrapper);

        //Assert
        assertNotNull(scrollPaneComponent.getScrollBar());
        assertEquals(scrollBarWrapper, scrollPaneComponent.getScrollBar());
    }

    @Test
    void removeScrollBar() {
        //Arrange
        scrollPaneComponent.setScrollBar(scrollBarWrapper);

        //Act
        scrollPaneComponent.removeScrollBar();

        //Assert
        assertNull(scrollPaneComponent.getScrollBar());
    }

    @Test
    void setBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getSolidScrollBarBackground(scrollBarWrapper);

        //Act
        scrollBarWrapper.setBackground(background);

        //Assert
        assertEquals(background, scrollBarWrapper.getBackground());
    }

    @Test
    void removeBackgroundOfScrollBar() {
        //Arrange
        IBackground<?> background = Backgrounds.getSolidScrollBarBackground(scrollBarWrapper);
        scrollBarWrapper.setBackground(background);

        //Act
        scrollBarWrapper.removeBackground();

        //Assert
        assertNull(scrollBarWrapper.getBackground());
    }

    @Test
    void ScrollBarComponentSetScrollBar() {
        //Arrange
        ScrollBar scrollBar = new ScrollBar(Textures.SCROLL_BAR, 0, 0, 100);

        //Act
        scrollBarWrapper.setScrollBar(scrollBar);

        //Assert
        assertNotNull(scrollBarWrapper.getScrollBar());
        assertEquals(scrollBar, scrollBarWrapper.getScrollBar());
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
