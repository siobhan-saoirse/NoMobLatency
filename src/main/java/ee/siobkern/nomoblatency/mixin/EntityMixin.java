package ee.siobkern.nomoblatency.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB; 
import net.minecraft.world.phys.shapes.VoxelShape;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Overwrite
    public boolean canSimulateMovement() 
    {
        Entity self = (Entity)(Object)this;
        
        Level level = self.level();

        AABB bb = self.getBoundingBox();
        
        AABB slice = new AABB(
                bb.minX + 0.1, bb.minY, bb.minZ + 0.1,
                bb.maxX - 0.1, bb.minY + 0.1, bb.maxZ - 0.1
        );

        double highestY = bb.minY;

        Iterable<VoxelShape> collisions = level.getBlockCollisions(self, slice);

        for (VoxelShape shape : collisions) {
            List<AABB> boxes = shape.toAabbs();
            for (AABB box : boxes) {
                if (box.maxY > highestY) {
                    highestY = box.maxY;
                }
            }
        }

        if (highestY > bb.minY) {
            if (highestY - bb.minY < 0.5) {
                self.setPos(self.getX(), highestY, self.getZ());
            }
        }
        return true;
    }

    @Overwrite
    public boolean isEffectiveAi() 
    {
        Entity self = (Entity)(Object)this;
        
        Level level = self.level();

        AABB bb = self.getBoundingBox();
        
        AABB slice = new AABB(
                bb.minX + 0.1, bb.minY, bb.minZ + 0.1,
                bb.maxX - 0.1, bb.minY + 0.1, bb.maxZ - 0.1
        );

        double highestY = bb.minY;

        Iterable<VoxelShape> collisions = level.getBlockCollisions(self, slice);

        for (VoxelShape shape : collisions) {
            List<AABB> boxes = shape.toAabbs();
            for (AABB box : boxes) {
                if (box.maxY > highestY) {
                    highestY = box.maxY;
                }
            }
        }

        if (highestY > bb.minY) {
            if (highestY - bb.minY < 0.5) {
                self.setPos(self.getX(), highestY, self.getZ());
            }
        }
        return true;
    }
}