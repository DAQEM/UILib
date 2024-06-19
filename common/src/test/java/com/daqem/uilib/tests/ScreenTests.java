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
    }
}
