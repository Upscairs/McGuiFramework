package dev.upscairs.mcGuiFramework.functionality;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ClickHandler {
    /**
     * @param rawSlot     absolute slot number
     * @param clickedItem clicked item
     * @param gui         current gui
     * @return            next gui
     */
    InventoryGui handle(int rawSlot, ItemStack clickedItem, InventoryGui gui);
}
