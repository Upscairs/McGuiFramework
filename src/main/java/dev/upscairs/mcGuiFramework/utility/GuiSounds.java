package dev.upscairs.mcGuiFramework.utility;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class GuiSounds {

    private String clickSound = "minecraft:ui.button.click";
    private String successSound = "minecraft:entity.player.levelup";
    private String failSound = "minecraft:entity.villager.no";

    private float clickPitch = 1;
    private float successPitch = 2;
    private float failPitch = 1;

    private boolean playSounds = true;

    public void playClickSound(HumanEntity entity) {
        if (!playSounds) return;
        if (!(entity instanceof Player player)) return;

        player.playSound(player.getLocation(), clickSound, 1, clickPitch);
    }

    public void playSuccessSound(HumanEntity entity) {
        if (!playSounds) return;
        if (!(entity instanceof Player player)) return;
        player.playSound(player.getLocation(), successSound, 1, successPitch);
    }

    public void playFailSound(HumanEntity entity) {
        if (!playSounds) return;
        if (!(entity instanceof Player player)) return;
        player.playSound(player.getLocation(), failSound, 1, failPitch);
    }

    public void setClickSound(String clickSound) {
        this.clickSound = clickSound;
    }

    public void setSuccessSound(String successSound) {
        this.successSound = successSound;
    }

    public void setFailSound(String failSound) {
        this.failSound = failSound;
    }

    public void setClickPitch(float clickPitch) {
        this.clickPitch = clickPitch;
    }

    public void setSuccessPitch(float successPitch) {
        this.successPitch = successPitch;
    }

    public void setFailPitch(float failPitch) {
        this.failPitch = failPitch;
    }
    
    public void playSounds(boolean playSounds) {
        this.playSounds = playSounds;
    }
}
