package com.endpickaxe.item;

import com.endpickaxe.EndPickaxeMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item END_PICKAXE = new EndPickaxeItem(
            new Item.Settings()
                    .maxDamage(2500)
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1.0f, -2.8f))
                    .fireproof()
    );

    public static void register() {
        Registry.register(Registries.ITEM, EndPickaxeMod.id("end_pickaxe"), END_PICKAXE);

        // Add the pickaxe to the vanilla "Tools & Utilities" creative tab.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(END_PICKAXE));
    }
}
