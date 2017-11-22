package com.Patrick_Spiegel.psq_metadata_manager;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.commands.CommandCategory;
import com.intellectualcrafters.plot.commands.MainCommand;
import com.intellectualcrafters.plot.commands.RequiredType;
import com.intellectualcrafters.plot.commands.SubCommand;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.general.commands.CommandDeclaration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.HashSet;
import java.util.logging.Level;

@CommandDeclaration(
        command = "deletemetadata",
        aliases = {
                "dm",
                "dmd",
                "deletemeta"
        },
        requiredType = RequiredType.PLAYER,
        category = CommandCategory.DEBUG,
        confirmation = true,
        description = "Entfernt alle Spieler von deinem Grundstücke",
        permission = "soulcraft.plotsquared.deletemetadata",
        usage = "/plot dm"

)
public class ResetMetaData extends SubCommand
{

    @Override
    public boolean onCommand(PlotPlayer plotPlayer, String[] strings)
    {
        PlotAPI api = new PlotAPI();

        Plot plot = api.getPlot(Bukkit.getServer().getPlayer(plotPlayer.getName()));
        plot.setMembers(new HashSet<>());
        plot.setDenied(new HashSet<>());
        plot.setTrusted(new HashSet<>());

        plotPlayer.sendMessage("Es wurden alle Spieler von deinem Plot entfernt");

        return  true;
    }
}
