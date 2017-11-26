package com.Patrick_Spiegel.psq_metadata_manager;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.commands.CommandCategory;
import com.intellectualcrafters.plot.commands.RequiredType;
import com.intellectualcrafters.plot.commands.SubCommand;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.general.commands.CommandDeclaration;
import org.bukkit.Bukkit;

import java.util.HashSet;


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
        usage = "/plot dm [all|trust|member|denied]"

)
public class ResetMetaData extends SubCommand
{

    @Override
    public boolean onCommand(PlotPlayer plotPlayer, String[] strings)
    {

        Main main = Main.getInstance();

        PlotAPI api = main.getPlotApi();

        Plot plot = api.getPlot(Bukkit.getServer().getPlayer(plotPlayer.getName()));
        if (plot.isOwner(plotPlayer.getUUID()) || plotPlayer.hasPermission("soulcraft.plotsquared.deletemetadata" +
                ".admin"))
        {
            if (any(strings, (String x) -> x.equalsIgnoreCase("all")) || !any(strings))
            {
                plot.setMembers(new HashSet<>());
                plot.setDenied(new HashSet<>());
                plot.setTrusted(new HashSet<>());

                plotPlayer.sendMessage("§8[§6P2§8] §3Es wurden alle Spieler von deinem Grundstück entfernt");
            }
            else
            {
                if (any(strings, (String x) -> x.equalsIgnoreCase("trust")))
                {
                    plot.setTrusted(new HashSet<>());
                    plotPlayer.sendMessage("§8[§6P2§8] §3Es wurden alle Spieler der Kategorie 'Trust' vom deinem Grundstück entfernt");
                }
                if (any(strings, (String x) -> x.equalsIgnoreCase("member")))
                {
                    plot.setMembers(new HashSet<>());
                    plotPlayer.sendMessage("§8[§6P2§8] §3Es wurde alle Spieler der Kategorie 'Member' von deinem Grundstück entfernt");
                }
                if (any(strings, (String x) -> x.equalsIgnoreCase("denied")))
                {
                    plot.setDenied(new HashSet<>());
                    plotPlayer.sendMessage("§8[§6P2§8] §3Es wurden alle Spieler der Kategorie 'Denied' von deinem " +
                            "Grundstück entfernt");
                }
            }
        }
        else
        {
            plotPlayer.sendMessage("§8[§6P2§8] §3Du musst dieses Grundstück besitzen, um Metadaten entfernen zu " +
                    "können");
        }


        return true;
    }



    private boolean any(String[] strings, StringLambda lambda)
    {
        for (int i = 0; i < strings.length; i++)
        {
            if (lambda.run(strings[i]))
            {
                return true;
            }
        }
        return false;
    }

    private boolean any(String[] strings)
    {
        return strings.length > 0;
    }
}
