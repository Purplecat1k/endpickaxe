/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1810
 *  net.minecraft.class_1832
 *  net.minecraft.class_1834
 *  net.minecraft.class_2246
 *  net.minecraft.class_2680
 */
package com.endpickaxe.item;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1810;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_2246;
import net.minecraft.class_2680;

public class EndPickaxeItem
extends class_1810 {
    public EndPickaxeItem(class_1792.class_1793 properties) {
        super((class_1832)class_1834.field_22033, properties);
    }

    public float method_58404(class_1799 stack, class_2680 state) {
        if (state.method_27852(class_2246.field_10398)) {
            return 8.0f;
        }
        return super.method_58404(stack, state);
    }

    public boolean method_58405(class_1799 stack, class_2680 state) {
        if (state.method_27852(class_2246.field_10398)) {
            return true;
        }
        return super.method_58405(stack, state);
    }
}

