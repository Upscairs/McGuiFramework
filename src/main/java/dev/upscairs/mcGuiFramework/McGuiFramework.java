package dev.upscairs.mcGuiFramework;

import dev.upscairs.mcGuiFramework.functionality.GuiInteractionHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McGuiFramework extends JavaPlugin {

    private static JavaPlugin plugin;
    private static boolean isRegistered = false;

    /**
     *
     * Initalizes the framework.
     *
     * @param plugin Instance of your plugin
     */
    public static void initalize(JavaPlugin plugin) {
        McGuiFramework.plugin = plugin;
        if(isRegistered) {
            return;
        }
        Bukkit.getPluginManager().registerEvents(new GuiInteractionHandler(), plugin);
        isRegistered = true;
    }

    /**
     *
     * Checks, if the framework is initalized correctly.
     * Deactivates the hosting plugin, if there is an error.
     *
     */
    public static void ensureInitialized() {
        if(!isRegistered) {
            JavaPlugin host = (plugin != null) ? plugin : JavaPlugin.getProvidingPlugin(McGuiFramework.class);

            host.getLogger().severe("McGuiFramework has not been initialized. Please call McGuiFramework.initalize(JavaPlugin) in your plugins onEnable() method.");
            Bukkit.getPluginManager().disablePlugin(host);
            throw new IllegalStateException("McGuiFramework not initalized.");
        }

    }


}
