package dev.upscairs.mcGuiFramework.functionality;

import dev.upscairs.mcGuiFramework.base.ItemDisplayGui;
import dev.upscairs.mcGuiFramework.wrappers.InteractableGui;

/**
 *
 * Return this in handleInvClick() to prevent the gui from closing.
 *
 */
public class PreventCloseGui extends InteractableGui {
    public PreventCloseGui() {
        super(new ItemDisplayGui());
    }
}
