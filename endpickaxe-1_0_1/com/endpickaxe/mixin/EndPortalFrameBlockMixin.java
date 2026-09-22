package com.endpickaxe.mixin;

import com.endpickaxe.item.ModItems;
import java.util.Set;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes the End Portal Frame effectively unbreakable to anything except
 * the End Pickaxe (in survival mode). Mining speed on the frame then
 * scales with the pickaxe's Efficiency level, same as vanilla tools do
 * on their intended blocks.
 */
@Mixin(AbstractBlock.class)
public class EndPortalFrameBlockMixin {

    @Inject(method = "getDestroyProgress", at = @At("HEAD"), cancellable = true)
    private void endpickaxe$progress(BlockState state, PlayerEntity player, BlockView world, BlockPos pos,
                                      CallbackInfoReturnable<Float> cir) {
        if (!state.isOf(Blocks.END_PORTAL_FRAME)) {
            return;
        }

        PlayerAbilities abilities = player.getAbilities();
        if (abilities.creativeMode) {
            // Let creative mode use the normal (instant) break behaviour.
            return;
        }

        ItemStack heldStack = player.getMainHandStack();
        if (!heldStack.isOf(ModItems.END_PICKAXE)) {
            // Anything other than the End Pickaxe simply cannot break it.
            cir.setReturnValue(0.0f);
            return;
        }

        int efficiencyLevel = 0;
        ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(heldStack);
        Identifier efficiencyId = Identifier.ofVanilla("efficiency");
        Set<RegistryEntry<Enchantment>> enchantmentEntries = enchantments.getEnchantments();
        for (RegistryEntry<Enchantment> entry : enchantmentEntries) {
            if (entry.matchesId(efficiencyId)) {
                efficiencyLevel = enchantments.getLevel(entry);
                break;
            }
        }

        float progress = switch (efficiencyLevel) {
            case 1 -> 0.15f;
            case 2 -> 0.25f;
            case 3 -> 0.4f;
            case 4 -> 0.6f;
            case 5 -> 1.0f;
            default -> 0.1f;
        };
        cir.setReturnValue(progress);
    }
}
