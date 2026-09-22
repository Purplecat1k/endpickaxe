package com.endpickaxe.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;

/**
 * The End Pickaxe. Built on a netherite tool base, but its whole point is
 * being the one tool that can actually mine an End Portal Frame.
 */
public class EndPickaxeItem extends PickaxeItem {

    public EndPickaxeItem(Item.Settings settings) {
        super(ToolMaterials.NETHERITE, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.END_PORTAL_FRAME)) {
            return 8.0f;
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }

    @Override
    public boolean isSuitableFor(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.END_PORTAL_FRAME)) {
            return true;
        }
        return super.isSuitableFor(stack, state);
    }
}
