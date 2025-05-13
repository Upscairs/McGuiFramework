package dev.upscairs.mcGuiFramework.wrappers;

import dev.upscairs.mcGuiFramework.base.InventoryGui;
import dev.upscairs.mcGuiFramework.utility.ListableGuiObject;
import dev.upscairs.mcGuiFramework.utility.InvGuiUtils;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 *
 * A gui that can be used to display a list of items in pages.
 * If you want to extend this class and want to use handleInvClick, make sure to use setHolder(YourInstance) after super() call.
 * You need to implement InventoryHolder in your class and call super.handleInvClick(slot) in your overridden method after
 * your own logic has been executed.
 *
 * @param <E> The type of the list of items to be displayed.
 */
public class PageGui<E extends List<? extends ListableGuiObject>> extends InteractableGui implements InventoryHolder {

    private int page;
    private int maxPage;
    private E listedObjects;

    private boolean showPageInTitle = false;
    private String rawTitle = "";

    public PageGui(InteractableGui interactableGui, E listedObjects, int page) {
        super(interactableGui);
        this.listedObjects = listedObjects;

        maxPage = (listedObjects.size() - 1) / 45;

        setHolder(this);

        setPage(page);

    }

    /**
     *
     * Sets, which page number should be displayed.
     *
     * @param page Page number, 0-based, gets set in bounds if out of bounds
     */
    public void setPage(int page) {

        this.page = page;

        if(page > maxPage) {
            this.page = maxPage;
        }
        if(page < 0) {
            this.page = 0;
        }

        updatePageInTitle();
        placeItems();
    }

    /**
     *
     * Returns currently displayed page number.
     *
     * @return Page number, 0-based
     */
    public int getPage() {
        return page;
    }

    private void flushItems() {
        for(int i = 0; i < getInventory().getSize(); i++) {
            setItem(i, new ItemStack(Material.AIR));
        }
    }

    /**
     *
     * Places items into gui.
     *
     */
    public void placeItems() {

        flushItems();

        if(page > 0) {
            setItem(45, generateScrollLeftItem());
        }
        if(page < maxPage) {
            setItem(53, generateScrollRightItem());
        }

        for(int i = 45*page; i < 45*(page+1) && i < listedObjects.size(); i++) {
            setItem(i%45, listedObjects.get(i).getRenderItem());
        }

        super.placeItems();

    }

    private ItemStack generateScrollLeftItem() {
        ItemStack stack = InvGuiUtils.generateCustomUrlHeadStack("http://textures.minecraft.net/texture/e35e42fc7060c223acc965f7c5996f272644af40a4723a372f5903f8e9f188e7");
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(InvGuiUtils.generateDefaultHeaderComponent("Previous Page", "#B1B1B1"));
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack generateScrollRightItem() {
        ItemStack stack = InvGuiUtils.generateCustomUrlHeadStack("http://textures.minecraft.net/texture/aee0f82fb33f6cfa5169b9f5eafe4dc1c73618c9783b131adada411d8f605505");
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(InvGuiUtils.generateDefaultHeaderComponent("Next Page", "#B1B1B1"));
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public InventoryGui handleInvClick(int slot) {

        if(slot == 45) {
            setPage(page - 1);
            return this;
        }
        else if(slot == 53) {
            setPage(page + 1);
            return this;
        }
        else {
            return super.handleInvClick(slot);
        }

    }

    public E getListedObjects() {
        return listedObjects;
    }

    public void setListedObjects(E listedObjects) {
        this.listedObjects = listedObjects;
        setPage(page);
    }

    /**
     *
     * Appends the page number to the title if showPageInTitle is true.
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {

        if(!showPageInTitle) {
            super.setTitle(title);
        }
        else {
            this.rawTitle = title;
            super.setTitle(rawTitle + " (" + (page+1) + "/" + (maxPage+1) + ")");
        }

    }

    public void updatePageInTitle() {
        setTitle(rawTitle);
    }

    /**
     *
     * Appends page number to title if set to true
     *
     * @param showPageInTitle
     */
    public void showPageInTitle(boolean showPageInTitle) {
        this.showPageInTitle = showPageInTitle;
        updatePageInTitle();
    }

    /**
     *
     * Returns, if the page number is displayed in title.
     *
     * @return
     */
    public boolean isPageInTitleShown() {
        return showPageInTitle;
    }

}
