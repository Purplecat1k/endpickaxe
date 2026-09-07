/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
 *  net.fabricmc.fabric.api.loot.v3.LootTableEvents
 *  net.minecraft.class_117$class_118
 *  net.minecraft.class_141
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_219
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2333
 *  net.minecraft.class_2338
 *  net.minecraft.class_2769
 *  net.minecraft.class_2960
 *  net.minecraft.class_44
 *  net.minecraft.class_55
 *  net.minecraft.class_5658
 *  net.minecraft.class_77
 *  net.minecraft.class_79$class_80
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.endpickaxe;

import com.endpickaxe.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.class_117;
import net.minecraft.class_141;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_219;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2333;
import net.minecraft.class_2338;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_44;
import net.minecraft.class_55;
import net.minecraft.class_5658;
import net.minecraft.class_77;
import net.minecraft.class_79;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndPickaxeMod
implements ModInitializer {
    public static final String MOD_ID = "endpickaxe";
    public static final Logger LOGGER = LoggerFactory.getLogger((String)"endpickaxe");

    public void onInitialize() {
        ModItems.register();
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, be) -> {
            if (state.method_27852(class_2246.field_10398) && !player.method_31549().field_7477) {
                return player.method_6047().method_31574(ModItems.END_PICKAXE);
            }
            return true;
        });
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, be) -> {
            if (world.method_8608() || !state.method_27852(class_2246.field_10398)) {
                return;
            }
            if (player.method_31549().field_7477) {
                EndPickaxeMod.clearPortal(world, pos);
                return;
            }
            if (player.method_6047().method_31574(ModItems.END_PICKAXE)) {
                class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)class_2246.field_10398));
                if (state.method_28498((class_2769)class_2333.field_10958) && Boolean.TRUE.equals(state.method_11654((class_2769)class_2333.field_10958))) {
                    class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)new class_1799((class_1935)class_1802.field_8449));
                }
                EndPickaxeMod.clearPortal(world, pos);
            }
        });
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.method_29177().equals((Object)class_2960.method_60656((String)"chests/end_city_treasure"))) {
                tableBuilder.method_336(class_55.method_347().method_352((class_5658)class_44.method_32448((float)1.0f)).method_356(class_219.method_932((float)0.02f)).method_351((class_79.class_80)class_77.method_411((class_1935)ModItems.END_PICKAXE).method_438((class_117.class_118)class_141.method_621((class_5658)class_44.method_32448((float)1.0f)))));
            }
        });
        LOGGER.info("End Pickaxe progressive loaded");
    }

    private static void clearPortal(class_1937 world, class_2338 origin) {
        for (int x = -6; x <= 6; ++x) {
            for (int y = -3; y <= 3; ++y) {
                for (int z = -6; z <= 6; ++z) {
                    class_2338 p = origin.method_10069(x, y, z);
                    if (!world.method_8320(p).method_27852(class_2246.field_10027)) continue;
                    world.method_8652(p, class_2246.field_10124.method_9564(), 3);
                }
            }
        }
    }

    public static class_2960 id(String path) {
        return class_2960.method_60655((String)MOD_ID, (String)path);
    }
}

