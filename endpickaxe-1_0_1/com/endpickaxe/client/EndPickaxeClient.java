package com.endpickaxe.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Client-side entry point. Currently empty — reserved for any future
 * client-only setup (custom rendering, keybinds, etc.).
 */
@Environment(EnvType.CLIENT)
public class EndPickaxeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    }
}
