package dev.upscairs.mcGuiFramework.wrappers;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import dev.upscairs.mcGuiFramework.utility.InvGuiUtils;
import dev.upscairs.mcGuiFramework.utility.ListableGuiObject;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 *
 * A gui that can be used to display a list of items and be scrolled up and down.
 * If you want to extend this class and want to use handleInvClick, make sure to use setHolder(YourInstance) after super() call.
 * You need to implement InventoryHolder in your class and call super.handleInvClick(slot) in your overridden method after
 * your own logic has been executed.
 *
 * @param <E> The type of the list of items to be displayed.
 */
public class ScrollGui<E extends List<? extends ListableGuiObject>> extends InteractableGui implements InventoryHolder {

    private int scrollOffset;
    private int maxScrollOffset;

    private E listedObjects;


    public ScrollGui(InteractableGui interactableGui, E listedObjects, int scrollOffset) {

        super(interactableGui);
        this.listedObjects = listedObjects;

        maxScrollOffset = Math.max((int)(Math.ceil(listedObjects.size() / 8) - 5), 0);

        setHolder(this);

        setScrollOffset(scrollOffset);

    }

    /**
     *
     * Sets how far the gui is scrolled down.
     *
     * @param offset
     */
    public void setScrollOffset(int offset) {

        if (offset < 0) offset = 0;
        if (offset > maxScrollOffset) offset = maxScrollOffset;

        this.scrollOffset = offset;

        placeItems();

    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    private void flushListedItems() {
        for (int i = 0; i < getInventory().getSize(); i++) {
            if(i % 9 == 8) {
                continue;
            }
            setItem(i, new ItemStack(Material.AIR));
        }
    }

    /**
     *
     * Places items into gui.
     *
     */
    @Override
    public void placeItems() {

        flushListedItems();
        if(scrollOffset > 0) {
            setItem(8, generateScrollUpItem());
        }

        if(scrollOffset < maxScrollOffset) {
            setItem(53, generateScrollDownItem());
        }


        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                int itemIndex = (i+scrollOffset)*8+j;

                if(itemIndex >= listedObjects.size()) break;

                setItem(i*9+j, listedObjects.get(itemIndex).getRenderItem());
            }
        }

    }

    private ItemStack generateScrollUpItem() {
        ItemStack stack = InvGuiUtils.generateCustomUrlHeadStack("http://textures.minecraft.net/texture/1ad6c81f899a785ecf26be1dc48eae2bcfe777a862390f5785e95bd83bd14d");
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(InvGuiUtils.generateDefaultHeaderComponent("Scroll Up", "#B1B1B1"));
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack generateScrollDownItem() {
        ItemStack stack = InvGuiUtils.generateCustomUrlHeadStack("http://textures.minecraft.net/texture/882faf9a584c4d676d730b23f8942bb997fa3dad46d4f65e288c39eb471ce7");
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(InvGuiUtils.generateDefaultHeaderComponent("Scroll Down", "#B1B1B1"));
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public InventoryGui handleInvClick(int slot, ItemStack clickedItem) {
        if(slot == 8) {
            setScrollOffset(scrollOffset - 1);
            return this;
        }
        else if(slot == 53) {
            setScrollOffset(scrollOffset + 1);
            return this;
        }
        else {
            return super.handleInvClick(slot, clickedItem);
        }
    }

    /**
     *
     * Returns the list of objects that are displayed in the gui.
     *
     * @return
     */
    public E getListedObjects() {
        return listedObjects;
    }

    /**
     *
     * Sets the list of objects that are displayed in the gui.
     *
     * @param listedObjects
     */
    public void setListedObjects(E listedObjects) {
        this.listedObjects = listedObjects;
        setScrollOffset(scrollOffset);
    }

}
