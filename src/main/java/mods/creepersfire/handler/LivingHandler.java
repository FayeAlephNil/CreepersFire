package mods.creepersfire.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.creepersfire.api.interfaces.ICreeper;
import mods.creepersfire.reference.ConfigSettings;
import mods.creepersfire.reference.Reference;
import mods.creepersfire.utility.LogHelper;
import mods.creepersfire.utility.ReflectionUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Ref;

public class LivingHandler
{
    static boolean hasTicked = false;

    @SubscribeEvent
    public void onHurt(LivingHurtEvent event)
    {
        if (event.source.equals(DamageSource.onFire) || event.source.equals(DamageSource.inFire))
        {
            if (event.entity instanceof EntityCreeper && !(event.entity instanceof ICreeper))
            {
                LogHelper.info("Creeper Hurt by fire");
                EntityCreeper creeper = (EntityCreeper) event.entity;

                if (ConfigSettings.hasFuse == 1) {
                    ReflectionUtil.changeFieldFromObj(creeper, "fuseTime", ConfigSettings.fuseTime);
                    creeper.func_146079_cb();
                } else if (ConfigSettings.hasFuse == 0)
                {
                    ReflectionUtil.runMethodFromObj(creeper, "func_146077_cc");
                } else
                {
                    LogHelper.error("Wrong value for hasFuse");
                }
            } else if (event.entity instanceof ICreeper)
            {
                LogHelper.info("ICreeper Hurt by fire");
                ICreeper creeper = (ICreeper) event.entity;
                if (creeper.useConfigForFuse())
                {
                    creeper.setFuseTime(ConfigSettings.fuseTime);
                }
                if (ConfigSettings.hasFuse == 1)
                {
                    creeper.doFuse();
                } else if (ConfigSettings.hasFuse == 0)
                {
                    creeper.doExplode();
                } else
                {
                    LogHelper.error("Wrong value for hasFuse");
                }
            } else if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                for (String name : ConfigSettings.names) {
                    if (player.getDisplayName().equals(name)) {
                        func_146077_cc(player);
                    }
                }
            }
        }
    }

    private void func_146077_cc(EntityLivingBase living)
    {
        if (!living.worldObj.isRemote)
        {
            boolean flag = living.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            living.worldObj.createExplosion(living, living.posX, living.posY, living.posZ, (float)3, flag);

            living.setHealth(0F);
        }
    }
}
