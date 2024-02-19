package com.daqem.uilib.client.gui.component.tab;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.event.OnChange;
import com.daqem.uilib.api.client.gui.component.tab.ITabInformation;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.arrow.ArrowButtonComponent;
import com.daqem.uilib.client.gui.component.arrow.ArrowComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;
import java.util.Optional;

public class TabsContainerComponent extends AbstractComponent<TabsContainerComponent> {

    public static final int TAB_SPACING = 4;

    private final List<TabComponent> tabComponents;
    private final ArrowButtonComponent leftArrow;
    private final ArrowButtonComponent rightArrow;
    private final int maxTabsPerPage;
    private int page;
    private final OnChange<TabComponent> onTabChange;

    public TabsContainerComponent(int x, int y, List<? extends ITabInformation> tabInformationList, int maxTabsPerPage, OnChange<TabComponent> onTabChange) {
        super(null, x, y, Math.max(0, Math.min(maxTabsPerPage, tabInformationList.size()) * (TabComponent.TAB_WIDTH + TAB_SPACING) - TAB_SPACING), TabComponent.TAB_HEIGHT);
        final int buttonSize = 20;
        final int buttonSpacing = 4;
        this.leftArrow = new ArrowButtonComponent(Textures.MINECRAFT_BUTTON, -buttonSize - buttonSpacing, (TabComponent.TAB_HEIGHT - buttonSize) / 2, buttonSize, buttonSize, ArrowComponent.Direction.LEFT, 8, 0xFFFFFFFF, 3);
        this.rightArrow = new ArrowButtonComponent(Textures.MINECRAFT_BUTTON, getWidth() + buttonSpacing, (TabComponent.TAB_HEIGHT - buttonSize) / 2, buttonSize, buttonSize, ArrowComponent.Direction.RIGHT, 8, 0xFFFFFFFF, 3);
        this.maxTabsPerPage = maxTabsPerPage;
        this.onTabChange = onTabChange;
        this.page = 0;
        this.tabComponents = tabInformationList.stream().map(tabInformation -> {
            int index = tabInformationList.indexOf(tabInformation);
            return new TabComponent((index % maxTabsPerPage) * (TAB_SPACING + TabComponent.TAB_WIDTH), 0, tabInformation);
        }).toList();

        this.tabComponents.forEach(tabComponent -> tabComponent.setOnClickEvent((clickedObject, screen, mouseX, mouseY, button) -> {
            deselectAll();
            tabComponent.setSelected(true);
            this.onTabChange.onChange(tabComponent);
        }));

        assignTabsForPage(false);

        this.addChildren(leftArrow, rightArrow);

        leftArrow.setOnClickEvent((clickedObject, screen, mouseX, mouseY, button) -> {
            if (page > 0) {
                page--;
                assignTabsForPage(true);
            }
        });

        rightArrow.setOnClickEvent((clickedObject, screen, mouseX, mouseY, button) -> {
            if (page < tabInformationList.size() / maxTabsPerPage) {
                page++;
                assignTabsForPage(true);
            }
        });
    }

    private void assignTabsForPage(boolean callOnChange) {
        tabComponents.forEach(tabComponent -> {
            int index = tabComponents.indexOf(tabComponent);
            int page = index / maxTabsPerPage;
            if (page == this.page) {
                this.addChild(tabComponent);
            } else {
                this.removeChild(tabComponent);
            }
        });

        this.deselectAll();

        Optional<IComponent<?>> firstTab = this.getChildren().stream()
                .filter(child -> child instanceof TabComponent)
                .findFirst();

        firstTab.ifPresent(tab -> ((TabComponent) tab).setSelected(true));
        if (callOnChange) {
            this.onTabChange.onChange((TabComponent) firstTab.orElse(null));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        leftArrow.setTexture(leftArrow.isTotalHovered(mouseX, mouseY) ? Textures.MINECRAFT_BUTTON_HOVERED : Textures.MINECRAFT_BUTTON);
        rightArrow.setTexture(rightArrow.isTotalHovered(mouseX, mouseY) ? Textures.MINECRAFT_BUTTON_HOVERED : Textures.MINECRAFT_BUTTON);

        if (page == 0) {
            removeChild(leftArrow);
            if (tabComponents.size() > maxTabsPerPage) {
                if (!getChildren().contains(rightArrow)) {
                    addChild(rightArrow);
                }
            }
        } else if (page == tabComponents.size() / maxTabsPerPage) {
            removeChild(rightArrow);
            if (tabComponents.size() > maxTabsPerPage) {
                if (!getChildren().contains(leftArrow)) {
                    addChild(leftArrow);
                }
            }
        } else {
            if (!getChildren().contains(leftArrow)) {
                addChild(leftArrow);
            }
            if (!getChildren().contains(rightArrow)) {
                addChild(rightArrow);
            }
        }
    }

    public void deselectAll() {
        tabComponents.forEach(tabComponent -> tabComponent.setSelected(false));
    }

    public List<TabComponent> getTabComponents() {
        return tabComponents;
    }

    public int getMaxTabsPerPage() {
        return maxTabsPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
