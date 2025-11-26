package ee.siobkern.nomoblatency.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.world.entity.EntityType$Builder")
public abstract class EntityTypeBuilderMixin<T> {
    @Shadow private int updateInterval;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(CallbackInfo ci) {
        this.updateInterval = 1;
    }
}