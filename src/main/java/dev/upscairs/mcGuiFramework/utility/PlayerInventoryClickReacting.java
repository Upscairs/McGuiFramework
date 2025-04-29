package dev.upscairs.mcGuiFramework.utility;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import org.bukkit.inventory.ItemStack;

/**
 *
 * Interface for guis that react to clicks on the player's inventory.
 *
 */
public interface PlayerInventoryClickReacting extends InventoryGui {

    PlayerInventoryClickReacting onItemClick(ItemStack selectedItem);

}
