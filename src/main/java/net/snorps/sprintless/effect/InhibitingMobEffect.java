package net.snorps.sprintless.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.snorps.sprintless.registry.SprintlessMobEffects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InhibitingMobEffect extends MobEffect {
    public InhibitingMobEffect() {
        super(MobEffectCategory.HARMFUL, 3953285);
    }

    //private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void applyEffectTick(LivingEntity livingentity, int amplifier) {
        if (livingentity.getHealth() >= 20) {
            livingentity.removeEffect(SprintlessMobEffects.INHIBITING.get());
            //LOGGER.info("healeded");
        }
    }
}