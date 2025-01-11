package net.hujoe.insilence.mixin;

import net.hujoe.insilence.item.ModItems;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootableContainerBlockEntity.class)
public class LootableContainerBlockEntityMixin {

    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    public void doNotMoveBlocker(int slot, ItemStack stack, CallbackInfo ci){
        if (stack.isOf(ModItems.BLOCKER)){
            ci.cancel();
        }
    }


}
