package ee.siobkern.nomoblatency.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Overwrite
    public boolean canSimulateMovement() 
    {
        return true;
    }

    @Overwrite
    public boolean isEffectiveAi() 
    {
        return true;
    }
}