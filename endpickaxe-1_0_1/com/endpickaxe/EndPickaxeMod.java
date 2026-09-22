package com.endpickaxe;

import com.endpickaxe.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod entry point.
 *
 * Adds an "End Pickaxe" that is the only tool able to break End Portal
 * Frames. Breaking a frame with it drops the frame block itself (and an
 * Ender Eye, if the frame had one socketed), and clears any nearby End
 * Portal blocks so the portal doesn't stay floating without its frame.
 *
 * The pickaxe itself has a small (~2%) chance to appear as extra loot in
 * End City treasure chests.
 */
public class EndPickaxeMod implements ModInitializer {

    public static final String MOD_ID = "endpickaxe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();

        // Prevent breaking End Portal Frames with anything other than the
        // End Pickaxe, unless the player is in creative mode.
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (state.isOf(Blocks.END_PORTAL_FRAME) && !player.getAbilities().creativeMode) {
                return player.getMainHandStack().isOf(ModItems.END_PICKAXE);
            }
            return true;
        });

        // Handle drops + portal cleanup once a frame is actually broken.
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient() || !state.isOf(Blocks.END_PORTAL_FRAME)) {
                return;
            }

            if (player.getAbilities().creativeMode) {
                // Creative players can just remove the frame outright;
                // still clean up any leftover portal blocks around it.
                clearPortal(world, pos);
                return;
            }

            if (player.getMainHandStack().isOf(ModItems.END_PICKAXE)) {
                Block.dropStack(world, pos, new ItemStack(Blocks.END_PORTAL_FRAME));

                // If the frame had an eye socketed, drop that too.
                if (state.contains(EndPortalFrameBlock.EYE)
                        && Boolean.TRUE.equals(state.get(EndPortalFrameBlock.EYE))) {
                    Block.dropStack(world, pos, new ItemStack(Items.ENDER_EYE));
                }

                clearPortal(world, pos);
            }
        });

        // Add the End Pickaxe as a rare bonus item in End City treasure chests.
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.getValue().equals(Identifier.ofVanilla("chests/end_city_treasure"))) {
                tableBuilder.pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.02f))
                        .with(ItemEntry.builder(ModItems.END_PICKAXE)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))));
            }
        });

        LOGGER.info("End Pickaxe progressive loaded");
    }

    /**
     * Clears every End Portal block within a 13x7x13 box centered on
     * {@code origin}, so a broken frame doesn't leave a floating portal.
     */
    private static void clearPortal(World world, BlockPos origin) {
        for (int x = -6; x <= 6; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -6; z <= 6; z++) {
                    BlockPos pos = origin.add(x, y, z);
                    if (world.getBlockState(pos).isOf(Blocks.END_PORTAL)) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                    }
                }
            }
        }
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
