package dev.upscairs.mcGuiFramework.utility;

import org.bukkit.inventory.ItemStack;

/**
 *
 * Class for wrapping ItemStacks to be placable in a ScrollableGui.
 *
 */
public class ListableItemStack implements ListableGuiObject {

    private final ItemStack itemStack;

    public ListableItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack get() {
        return itemStack;
    }

    @Override
    public ItemStack getRenderItem() {
        return itemStack;
    }
}
