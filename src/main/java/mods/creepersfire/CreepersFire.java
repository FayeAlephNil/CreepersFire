package mods.creepersfire;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.creepersfire.handler.ConfigurationHandler;
import mods.creepersfire.handler.LivingHandler;
import mods.creepersfire.proxy.CommonProxy;
import mods.creepersfire.reference.Reference;
import mods.creepersfire.utility.LogHelper;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CreepersFire
{
    @Mod.Instance(Reference.MOD_ID)
    public static CreepersFire instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        proxy.registerKeyBindings();
        proxy.initRenderers();
        proxy.initSounds();

        LogHelper.info("Pre Initialization Complete");
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new LivingHandler());
        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post Initialization Complete");
    }
}

