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
        Entity self = (Entity)(Object)this;
        Level world = self.level();

        double highestY = 0.0;

        AABB contracted = self.getBoundingBox().contract(0.03125, 0.0, 0.03125);

        // Block collisions
        for (VoxelShape shape : world.getBlockCollisions(self, contracted)) {
            for (AABB box : shape.toAabbs()) {   // getBoundingBoxes() does not exist anymore
                highestY = Math.max(highestY, box.maxY);
            }
        }
        
        // Apply vertical offset
        if (highestY > 0.0) {
            double dy = highestY - self.getBoundingBox().minY;
            self.setPos(self.getX(), self.getY() + dy, self.getZ());
        }

        return true;
    }

    @Overwrite
    public boolean isEffectiveAi() 
    {
        Entity self = (Entity)(Object)this;
        Level world = self.level();

        double highestY = 0.0;

        AABB contracted = self.getBoundingBox().contract(0.03125, 0.0, 0.03125);

        // Block collisions
        for (VoxelShape shape : world.getBlockCollisions(self, contracted)) {
            for (AABB box : shape.toAabbs()) {   // getBoundingBoxes() does not exist anymore
                highestY = Math.max(highestY, box.maxY);
            }
        }
        
        // Apply vertical offset
        if (highestY > 0.0) {
            double dy = highestY - self.getBoundingBox().minY;
            self.setPos(self.getX(), self.getY() + dy, self.getZ());
        }

        return true;
    }
}