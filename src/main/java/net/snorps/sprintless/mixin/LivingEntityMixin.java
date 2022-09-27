package net.snorps.sprintless.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.snorps.sprintless.registry.SprintlessMobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(int a) {
        super(null,null);
    }

    @Shadow
    private static AttributeModifier SPEED_MODIFIER_SPRINTING;

    @Inject(
            method = "setSprinting",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void setSprinting(CallbackInfo ci)  {
        LivingEntity ent = (LivingEntity)(Object)(this);
        AttributeInstance attributeinstance = ent.getAttribute(Attributes.MOVEMENT_SPEED);
        if (ent.hasEffect(SprintlessMobEffects.INHIBITING.get())) {
            attributeinstance.removeModifier(SPEED_MODIFIER_SPRINTING);
            setSharedFlag(3, false);
            ci.cancel();
        }
    }
}