/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1656
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1890
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_4970
 *  net.minecraft.class_6880
 *  net.minecraft.class_9304
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.endpickaxe.mixin;

import com.endpickaxe.item.ModItems;
import java.util.Set;
import net.minecraft.class_1656;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_4970;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4970.class})
public class EndPortalFrameBlockMixin {
    @Inject(method={"getDestroyProgress"}, at={@At(value="HEAD")}, cancellable=true)
    private void endpickaxe$progress(class_2680 class_26802, class_1657 class_16572, class_1922 class_19222, class_2338 class_23382, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (!class_26802.method_27852(class_2246.field_10398)) {
            return;
        }
        class_1656 class_16562 = class_16572.method_31549();
        if (class_16562.field_7477) {
            return;
        }
        class_1799 class_17992 = class_16572.method_6047();
        if (class_17992.method_31574(ModItems.END_PICKAXE)) {
            int n = 0;
            class_9304 class_93042 = class_1890.method_57532((class_1799)class_17992);
            class_2960 class_29602 = class_2960.method_60656((String)"efficiency");
            Set set = class_93042.method_57534();
            for (class_6880 class_68802 : set) {
                if (!class_68802.method_40226(class_29602)) continue;
                n = class_93042.method_57536(class_68802);
                break;
            }
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(switch (n) {
                case 1 -> 0.15f;
                case 2 -> 0.25f;
                case 3 -> 0.4f;
                case 4 -> 0.6f;
                case 5 -> 1.0f;
                default -> 0.1f;
            }));
        } else {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(0.0f));
        }
    }
}

