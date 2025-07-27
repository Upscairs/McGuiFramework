package dev.upscairs.mcGuiFramework.gui_wrappers;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import dev.upscairs.mcGuiFramework.functionality.ClickHandler;
import dev.upscairs.mcGuiFramework.functionality.PreventCloseGui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InteractableGui extends GuiDecorator implements InventoryHolder {

    private ClickHandler clickHandler;

    public InteractableGui(InventoryGui inventoryGui) {
        super(inventoryGui);
        setHolder(this);
    }

    public InventoryGui handleInvClick(int rawSlot, ItemStack clickedItem, HumanEntity clickingPlayer) {

        if (clickHandler != null) {
            InventoryGui next = clickHandler.handle(rawSlot, clickedItem, this);
            if (next != null) {
                return next;
            }
        }

        return new PreventCloseGui();
    }

    public InteractableGui onClick(ClickHandler handler) {
        this.clickHandler = handler;
        return this;
    }

    public void placeItems() {
        return;
    }

}
