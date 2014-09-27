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
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}