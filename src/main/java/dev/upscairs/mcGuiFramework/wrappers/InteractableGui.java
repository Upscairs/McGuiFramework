package dev.upscairs.mcGuiFramework.wrappers;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import dev.upscairs.mcGuiFramework.functionality.PreventCloseGui;

public class InteractableGui extends GuiDecorator {

    public InteractableGui(InventoryGui inventoryGui) {
        super(inventoryGui);
    }

    public InventoryGui handleInvClick(int slot) {
        return new PreventCloseGui();
    }

    public void placeItems() {
        return;
    }

}
