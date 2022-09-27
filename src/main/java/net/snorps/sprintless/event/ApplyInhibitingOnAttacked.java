package net.snorps.sprintless.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.snorps.sprintless.registry.SprintlessMobEffects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.pow;

@Mod.EventBusSubscriber
public class ApplyInhibitingOnAttacked {

    static final int baseDurationSeconds = 4;
    //static final int penaltyDurationSeconds = 4;
    static final int maximumDurationSeconds = 30;
    static final int penaltyAdditionalSeconds = 8;

    @SubscribeEvent
    public static void onAttack( LivingDamageEvent event ) {
        DamageSource damageSource = event.getSource();
        LivingEntity attacker = damageSource.getEntity() instanceof LivingEntity ? ( LivingEntity )damageSource.getEntity() : null;
        LivingEntity target = event.getEntityLiving();
        if (target instanceof Player player && attacker != null) {
            MobEffect inhibitingEffect = SprintlessMobEffects.INHIBITING.get();
            if (!target.hasEffect(inhibitingEffect)) { //if player does not have inhibiting already
                target.addEffect(new MobEffectInstance(SprintlessMobEffects.INHIBITING.get(), baseDurationSeconds * 20));
            } else { //if player has inhibiting already
                Collection<MobEffectInstance> activeEffects = target.getActiveEffects();
                Iterator iterator = activeEffects.iterator();
                for (MobEffectInstance effect : activeEffects) {
                    if (effect.getEffect() == inhibitingEffect) {
                        double duration = effect.getDuration()*0.6 + (penaltyAdditionalSeconds * 20);
                        if (duration > maximumDurationSeconds * 20) {
                            duration = maximumDurationSeconds * 20;
                        }
                        target.addEffect(new MobEffectInstance(SprintlessMobEffects.INHIBITING.get(),  (int)duration));
                    }
                }
            }
            target.setSprinting(false);
        }
    }
}