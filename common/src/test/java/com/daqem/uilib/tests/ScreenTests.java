package com.daqem.uilib.tests;

import com.daqem.uilib.objects.TestScreen;
import com.daqem.uilib.objects.background.TestBackground;
import com.daqem.uilib.objects.component.TestComponent;
import net.minecraft.network.chat.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScreenTests {

    Component title;
    TestScreen testScreen;

    @BeforeEach
    void setup() {
        title = Component.literal("Test Screen");
        testScreen = new TestScreen(title);
    }

    @Test
    void title() {
        //Assert
        assertEquals(title, testScreen.getTitle());
    }

    @Test
    void addComponent() {
        //Arrange
        TestComponent testComponent = new TestComponent();

        //Act
        testScreen.addComponent(testComponent);

        //Assert
        assertEquals(1, testScreen.getComponents().size());
        assertEquals(testComponent, testScreen.getComponents().get(0));
        assertSame(testScreen, testComponent.getScreen());
        assertTrue(testScreen.getComponents().contains(testComponent));
    }

    @Test
    void addComponents() {
        //Arrange
        TestComponent testComponent1 = new TestComponent();
        TestComponent testComponent2 = new TestComponent();
        TestComponent testComponent3 = new TestComponent();

        //Act
        testScreen.addComponents(testComponent1, testComponent2, testComponent3);

        //Assert
        assertEquals(3, testScreen.getComponents().size());
        assertTrue(testScreen.getComponents().contains(testComponent1));
        assertTrue(testScreen.getComponents().contains(testComponent2));
        assertTrue(testScreen.getComponents().contains(testComponent3));
        assertEquals(testScreen, testComponent1.getScreen());
        assertEquals(testScreen, testComponent2.getScreen());
        assertEquals(testScreen, testComponent3.getScreen());
        assertFalse(testScreen.getComponents().isEmpty());
        assertEquals(testComponent1, testScreen.getComponents().get(0));
        assertEquals(testComponent2, testScreen.getComponents().get(1));
        assertEquals(testComponent3, testScreen.getComponents().get(2));
    }

    @Test
    void removeComponent() {
        //Arrange
        TestComponent testComponent = new TestComponent();
        testScreen.addComponent(testComponent);

        //Act
        testScreen.removeComponent(testComponent);

        //Assert
        assertTrue(testScreen.getComponents().isEmpty());
        assertNull(testComponent.getScreen());
        assertFalse(testScreen.getComponents().contains(testComponent));
    }

    @Test
    void setBackground() {
        //Arrange
        TestBackground testBackground = new TestBackground(1080, 1920);

        //Act
        testScreen.setBackground(testBackground);

        //Assert
        assertEquals(testBackground, testScreen.getBackground());
        assertSame(testScreen, testBackground.getScreen());
    }

    @Test
    void getWidth() {
        //Act
        //noinspection DataFlowIssue
        testScreen.resize(null, 1080, 1920);

        //Assert
        assertEquals(1080, testScreen.getWidth());
    }

    @Test
    void getHeight() {
        //Act
        //noinspection DataFlowIssue
        testScreen.resize(null, 1080, 1920);

        //Assert
        assertEquals(1920, testScreen.getHeight());
    }

    @Test
    void resize() {
        //Arrange
        class ResizeTestScreen extends TestScreen {

            final TestComponent testComponent = new TestComponent(10, 20, 30, 40);
            final TestBackground testBackground = new TestBackground(width, height);

            public ResizeTestScreen(Component title) {
                super(title);
            }

            @Override
            public void startScreen() {
                addComponent(testComponent);
                setBackground(testBackground);
            }
        }
        ResizeTestScreen resizeTestScreen = new ResizeTestScreen(title);
        resizeTestScreen.startScreen();
        resizeTestScreen.initialized = true;

        //Act
        //noinspection DataFlowIssue
        resizeTestScreen.resize(null, 1920, 1080);

        //Assert
        assertEquals(1920, resizeTestScreen.getWidth());
        assertEquals(1080, resizeTestScreen.getHeight());
        assertNotNull(resizeTestScreen.getBackground());
        assertEquals(1920, resizeTestScreen.getBackground().getWidth());
        assertEquals(1080, resizeTestScreen.getBackground().getHeight());
        assertNotNull(resizeTestScreen.getComponents());
        assertEquals(1, resizeTestScreen.getComponents().size());
        assertEquals(resizeTestScreen, resizeTestScreen.getComponents().get(0).getScreen());
        assertEquals(10, resizeTestScreen.getComponents().get(0).getX());
        assertEquals(20, resizeTestScreen.getComponents().get(0).getY());
        assertEquals(30, resizeTestScreen.getComponents().get(0).getWidth());
        assertEquals(40, resizeTestScreen.getComponents().get(0).getHeight());
    }
}
