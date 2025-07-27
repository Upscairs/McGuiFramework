package dev.upscairs.mcGuiFramework.gui_wrappers;

import dev.upscairs.mcGuiFramework.McGuiFramework;
import dev.upscairs.mcGuiFramework.base.InventoryGui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class GuiDecorator implements InventoryGui {

    private InventoryGui inventoryGui;

    public GuiDecorator(InventoryGui inventoryGui) {
        this.inventoryGui = inventoryGui;
        McGuiFramework.ensureInitialized();
    }

    @Override
    public void setupInventory() {
        inventoryGui.setupInventory();
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        inventoryGui.setItem(slot, item);
    }

    @Override
    public void setSize(int size) {
        inventoryGui.setSize(size);
    }

    @Override
    public void setTitle(String title) {
        inventoryGui.setTitle(title);
    }

    @Override
    public void setHolder(InventoryHolder holder) {
        inventoryGui.setHolder(holder);
    }

    @Override
    public Inventory getInventory() {
        return inventoryGui.getInventory();
    }
}
