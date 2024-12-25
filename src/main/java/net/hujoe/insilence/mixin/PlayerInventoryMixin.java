package net.hujoe.insilence.mixin;

import net.hujoe.insilence.item.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Inject(method = "removeOne", at = @At("HEAD"), cancellable = true)
    public void doNotMoveBlocker(ItemStack stack, CallbackInfo ci) {
        if (stack.isOf(ModItems.BLOCKER)) {
            ci.cancel();
        }
    }

    @Inject(method = "removeStack(I)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    public void stillDoNotMoveBlocker(int slot, CallbackInfoReturnable<ItemStack> cir){
        PlayerInventory inv = (PlayerInventory) (Object) this;
        if (inv.getStack(slot).isOf(ModItems.BLOCKER)){
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }

    @Inject(method = "removeStack(II)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    public void neverMoveBlocker(int slot, int amount, CallbackInfoReturnable<ItemStack> cir){
        PlayerInventory inv = (PlayerInventory) (Object) this;
        if (inv.getStack(slot).isOf(ModItems.BLOCKER)){
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }

    @Inject(method = "swapSlotWithHotbar", at = @At("HEAD"), cancellable = true)
    public void iAmRunningOutOfMethodNamesThatDoTheSameThing(int slot, CallbackInfo ci){
        PlayerInventory inv = (PlayerInventory) (Object) this;
        if (inv.getStack(slot).isOf(ModItems.BLOCKER)){
            ci.cancel();
        }
    }

    @Inject(method = "swapSlotWithHotbar", at = @At("HEAD"), cancellable = true)
    public void theseAllDoTheSameThings(int slot, CallbackInfo ci){
        PlayerInventory inv = (PlayerInventory) (Object) this;
        if (inv.getStack(slot).isOf(ModItems.BLOCKER)){
            ci.cancel();
        }
    }
}
