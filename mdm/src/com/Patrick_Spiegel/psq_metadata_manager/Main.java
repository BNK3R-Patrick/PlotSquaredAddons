package com.Patrick_Spiegel.psq_metadata_manager;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.logging.Level;

public class Main extends JavaPlugin
{
    private static Main s_instance = null;

    public static Main getInstance()
    {
        return s_instance;
    }

    private PlotAPI m_plotApi = new PlotAPI();

    @Override
    public void onEnable()
    {
        s_instance = this;

        // Do PlotSquared related stuff
        PluginManager manager = Bukkit.getPluginManager();

        final Plugin plotsquared = manager.getPlugin("PlotSquared");
        if (plotsquared != null && !plotsquared.isEnabled())
        {
            manager.disablePlugin(Main.getInstance());
            Bukkit.getLogger().log(Level.INFO, "PlotSquared Plugin disabled, disabling Plot Squared Metadata " +
                    "Manager");
            return;
        }

        new ResetMetaData();

    }

    public PlotAPI getPlotApi()
    {
        return m_plotApi;
    }
}
