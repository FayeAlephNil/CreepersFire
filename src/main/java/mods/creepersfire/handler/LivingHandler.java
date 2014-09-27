package mods.creepersfire.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.creepersfire.reference.ConfigSettings;
import mods.creepersfire.utility.LogHelper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class LivingHandler
{
    @SubscribeEvent
    public void onHurt(LivingHurtEvent event)
    {
        if (event.source.equals(DamageSource.onFire) || event.source.equals(DamageSource.inFire))
        {
            if (event.entity instanceof EntityCreeper)
            {
                LogHelper.info("Creeper Hurt by fire");
                EntityCreeper creeper = (EntityCreeper) event.entity;
                if (ConfigSettings.hasFuse == 1) {
                    creeper.func_146079_cb();
                } else if (ConfigSettings.hasFuse == 0)
                {
                    explosionForCreeper(creeper);
                } else
                {
                    LogHelper.error("Wrong value for hasFuse");
                }
            }
        }
    }

    private void explosionForCreeper(EntityCreeper creeper)
    {
        if (!creeper.worldObj.isRemote)
        {
            LogHelper.info("isRemote");
            boolean flag = creeper.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            if (creeper.getPowered())
            {
                LogHelper.info("Creeper is powered");
                creeper.worldObj.createExplosion(creeper, creeper.posX, creeper.posY, creeper.posZ, (float)(3 * 2), flag);
            }
            else
            {
                LogHelper.info("Creeper is not powered");
                creeper.worldObj.createExplosion(creeper, creeper.posX, creeper.posY, creeper.posZ, (float)3, flag);
            }

            creeper.setDead();
            LogHelper.info("Creeper Explosion done");
        }
    }
}
