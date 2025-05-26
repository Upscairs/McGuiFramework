package dev.upscairs.mcGuiFramework.functionality;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import dev.upscairs.mcGuiFramework.wrappers.InteractableGui;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class GuiInteractionHandler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        //Prevents errors on console
        if (event.getClickedInventory() == null) {
            return;
        }

        //React to clicks when InteractbleGui
        if (event.getView().getTopInventory().getHolder() instanceof InteractableGui gui) {

            event.setCancelled(true);

            int raw = event.getRawSlot();
            ItemStack clicked = event.getCurrentItem() == null
                    ? new ItemStack(Material.AIR)
                    : event.getCurrentItem().clone();

            InventoryGui next = gui.handleInvClick(raw, clicked);


            if (!(next instanceof PreventCloseGui)) {
                //Null closes inventory
                if (next == null) {
                    event.getWhoClicked().closeInventory();
                } else {
                    event.getWhoClicked().openInventory(next.getInventory());
                }
            }

        }

    }


    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        //Cancel interactions in guis
        if (event.getView().getTopInventory() instanceof InventoryGui) {
            event.setCancelled(true);
        }
    }

}