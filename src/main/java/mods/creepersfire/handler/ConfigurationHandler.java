package mods.creepersfire.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.creepersfire.reference.ConfigSettings;
import mods.creepersfire.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{

    public static Configuration configuration;


    public static void init(File configFile) {

        //create configuration object from the given file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {

        ConfigSettings.hasFuse = configuration.getInt("hasFuse", Configuration.CATEGORY_GENERAL, ConfigSettings.defaultHasFuse, 0, 1, "Set 1 for has fuse and 0 if not");
        ConfigSettings.fuseTime = configuration.getInt("fuseTime", Configuration.CATEGORY_GENERAL, ConfigSettings.defaultFuseTime, 0 , 12000, "FuseTime in ticks");
        ConfigSettings.names = configuration.getStringList("playerNames", Configuration.CATEGORY_GENERAL, new String[0], "Use this to set players to explode on fire");
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}