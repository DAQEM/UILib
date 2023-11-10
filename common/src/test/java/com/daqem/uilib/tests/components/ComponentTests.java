package com.daqem.uilib.tests.components;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.objects.component.TestComponent;
import com.daqem.uilib.objects.text.TestText;
import com.daqem.uilib.objects.texture.TestTexture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentTests {

    private TestComponent testComponent;

    @BeforeEach
    void setup() {
           testComponent = new TestComponent();
    }

    @Test
    void setParent() {
        //Arrange
        TestComponent parentComponent = new TestComponent();

        //Act
        testComponent.setParent(parentComponent);

        //Assert
        assertEquals(parentComponent, testComponent.getParent());
        assertEquals(1, parentComponent.getChildren().size());
        assertEquals(testComponent, parentComponent.getChildren().get(0));
    }

    @Test
    void setChildren() {
        //Arrange
        TestComponent childComponent1 = new TestComponent();
        TestComponent childComponent2 = new TestComponent();
        TestComponent childComponent3 = new TestComponent();
        List<IComponent<?>> children = new ArrayList<>();
        children.add(childComponent1);
        children.add(childComponent2);
        children.add(childComponent3);

        //Act
        testComponent.setChildren(children);

        //Assert
        assertEquals(3, testComponent.getChildren().size());
        assertEquals(childComponent1, testComponent.getChildren().get(0));
        assertEquals(childComponent2, testComponent.getChildren().get(1));
        assertEquals(childComponent3, testComponent.getChildren().get(2));
        assertNotNull(childComponent1.getParent());
        assertNotNull(childComponent2.getParent());
        assertNotNull(childComponent3.getParent());
        assertEquals(testComponent, childComponent1.getParent());
        assertEquals(testComponent, childComponent2.getParent());
        assertEquals(testComponent, childComponent3.getParent());
        assertEquals(3, childComponent1.getParent().getChildren().size());
        assertEquals(childComponent1, childComponent1.getParent().getChildren().get(0));
        assertEquals(3, childComponent2.getParent().getChildren().size());
        assertEquals(childComponent2, childComponent2.getParent().getChildren().get(1));
        assertEquals(3, childComponent3.getParent().getChildren().size());
        assertEquals(childComponent3, childComponent3.getParent().getChildren().get(2));
    }

    @Test
    void addChild() {
        //Arrange
        TestComponent childComponent = new TestComponent();

        //Act
        testComponent.addChild(childComponent);

        //Assert
        assertEquals(1, testComponent.getChildren().size());
        assertEquals(childComponent, testComponent.getChildren().get(0));
        assertNotNull(childComponent.getParent());
        assertEquals(testComponent, childComponent.getParent());
        assertEquals(1, childComponent.getParent().getChildren().size());
        assertEquals(childComponent, childComponent.getParent().getChildren().get(0));
    }

    @Test
    void addChildrenList() {
        TestComponent childComponent1 = new TestComponent();
        TestComponent childComponent2 = new TestComponent();
        TestComponent childComponent3 = new TestComponent();
        List<IComponent<?>> children = new ArrayList<>();
        children.add(childComponent1);
        children.add(childComponent2);
        children.add(childComponent3);

        //Act
        testComponent.addChildren(children);

        //Assert
        assertEquals(3, testComponent.getChildren().size());
        assertEquals(childComponent1, testComponent.getChildren().get(0));
        assertEquals(childComponent2, testComponent.getChildren().get(1));
        assertEquals(childComponent3, testComponent.getChildren().get(2));
        assertNotNull(childComponent1.getParent());
        assertNotNull(childComponent2.getParent());
        assertNotNull(childComponent3.getParent());
        assertEquals(testComponent, childComponent1.getParent());
        assertEquals(testComponent, childComponent2.getParent());
        assertEquals(testComponent, childComponent3.getParent());
        assertEquals(3, childComponent1.getParent().getChildren().size());
        assertEquals(childComponent1, childComponent1.getParent().getChildren().get(0));
        assertEquals(3, childComponent2.getParent().getChildren().size());
        assertEquals(childComponent2, childComponent2.getParent().getChildren().get(1));
        assertEquals(3, childComponent3.getParent().getChildren().size());
        assertEquals(childComponent3, childComponent3.getParent().getChildren().get(2));
    }

    @Test
    void addChildrenArray() {
        TestComponent childComponent1 = new TestComponent();
        TestComponent childComponent2 = new TestComponent();
        TestComponent childComponent3 = new TestComponent();

        //Act
        testComponent.addChildren(childComponent1, childComponent2, childComponent3);

        //Assert
        assertEquals(3, testComponent.getChildren().size());
        assertEquals(childComponent1, testComponent.getChildren().get(0));
        assertEquals(childComponent2, testComponent.getChildren().get(1));
        assertEquals(childComponent3, testComponent.getChildren().get(2));
        assertNotNull(childComponent1.getParent());
        assertNotNull(childComponent2.getParent());
        assertNotNull(childComponent3.getParent());
        assertEquals(testComponent, childComponent1.getParent());
        assertEquals(testComponent, childComponent2.getParent());
        assertEquals(testComponent, childComponent3.getParent());
        assertEquals(3, childComponent1.getParent().getChildren().size());
        assertEquals(childComponent1, childComponent1.getParent().getChildren().get(0));
        assertEquals(3, childComponent2.getParent().getChildren().size());
        assertEquals(childComponent2, childComponent2.getParent().getChildren().get(1));
        assertEquals(3, childComponent3.getParent().getChildren().size());
        assertEquals(childComponent3, childComponent3.getParent().getChildren().get(2));
    }

    @Test
    void removeChild() {
        //Arrange
        TestComponent childComponent = new TestComponent();
        testComponent.addChild(childComponent);

        //Act
        testComponent.removeChild(childComponent);

        //Assert
        assertEquals(0, testComponent.getChildren().size());
        assertNull(childComponent.getParent());
        assertFalse(testComponent.getChildren().contains(childComponent));
    }

    @Test
    void setTexture() {
        //Arrange
        TestTexture testTexture = new TestTexture();

        //Act
        testComponent.setTexture(testTexture);

        //Assert
        assertEquals(testTexture, testComponent.getTexture());
    }

    @Test
    void setZ() {
        //Arrange
        int z = 1;

        //Act
        testComponent.setZ(z);

        //Assert
        assertEquals(z, testComponent.getZ());
    }

    @Test
    void setText() {
        //Arrange
        TestText testText = new TestText();

        //Act
        testComponent.setText(testText);

        //Assert
        assertEquals(testText, testComponent.getText());
    }

    @Test
    void setScale() {
        //Arrange
        float scale = 1.0F;

        //Act
        testComponent.setScale(scale);

        //Assert
        assertEquals(scale, testComponent.getScale());
    }

    @Test
    void setOpacity() {
        //Arrange
        float opacity = 1.0F;

        //Act
        testComponent.setOpacity(opacity);

        //Assert
        assertEquals(opacity, testComponent.getOpacity());
    }

    @Test
    void setRotation() {
        //Arrange
        float rotation = 1.0F;

        //Act
        testComponent.setRotation(rotation);

        //Assert
        assertEquals(rotation, testComponent.getRotation());
    }
}
