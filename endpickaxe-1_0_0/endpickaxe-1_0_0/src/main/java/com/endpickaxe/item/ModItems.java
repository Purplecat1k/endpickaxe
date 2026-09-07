/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1810
 *  net.minecraft.class_1832
 *  net.minecraft.class_1834
 *  net.minecraft.class_1935
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_7706
 *  net.minecraft.class_7923
 */
package com.endpickaxe.item;

import com.endpickaxe.EndPickaxeMod;
import com.endpickaxe.item.EndPickaxeItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.class_1792;
import net.minecraft.class_1810;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_1935;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7706;
import net.minecraft.class_7923;

public class ModItems {
    public static final class_1792 END_PICKAXE = new EndPickaxeItem(new class_1792.class_1793().method_7895(2500).method_57348(class_1810.method_57346((class_1832)class_1834.field_22033, (float)1.0f, (float)-2.8f)).method_24359());

    public static void register() {
        class_2378.method_10230((class_2378)class_7923.field_41178, (class_2960)EndPickaxeMod.id("end_pickaxe"), (Object)END_PICKAXE);
        ItemGroupEvents.modifyEntriesEvent((class_5321)class_7706.field_41060).register(entries -> entries.method_45421((class_1935)END_PICKAXE));
    }
}

