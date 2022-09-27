package net.snorps.sprintless.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.snorps.sprintless.Sprintless;
import net.snorps.sprintless.effect.InhibitingMobEffect;

public class SprintlessMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "sprintless");

    public static final RegistryObject<MobEffect> INHIBITING = MOB_EFFECTS.register("inhibiting", InhibitingMobEffect::new);
}