package net.hujoe.insilence.mixin;

import net.hujoe.insilence.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Inject(method = "insertItem", at = @At("HEAD"), cancellable = true)
    public void dude(ItemStack stack, int startIndex, int endIndex, boolean fromLast, CallbackInfoReturnable<Boolean> cir){
        if (stack.isOf(ModItems.BLOCKER)){
            cir.cancel();
        }
    }
}
