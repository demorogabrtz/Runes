package net.runes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.runes.api.RuneItems;
import net.runes.crafting.*;

public class RunesMod implements ModInitializer {
    public static final String ID = "runes";

    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, RuneCrafting.ID, RuneCrafting.SOUND);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ID, RuneCraftingRecipe.NAME), RuneCraftingRecipe.TYPE);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ID, RuneCraftingRecipe.NAME), RuneCraftingRecipe.SERIALIZER);
        Registry.register(Registries.BLOCK, new Identifier(ID, RuneCraftingBlock.NAME), RuneCraftingBlock.INSTANCE);
        Registry.register(Registries.ITEM, new Identifier(ID, RuneCraftingBlock.NAME), RuneCraftingBlock.ITEM);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.add(RuneCraftingBlock.ITEM);
        });
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(ID, RuneCraftingRecipe.NAME), RuneCraftingScreenHandler.HANDLER_TYPE);

        for(var entry: RuneItems.entries) {
            Registry.register(Registries.ITEM, entry.id(), entry.item());
        }
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            for(var entry: RuneItems.entries) {
                content.add(entry.item());
            }
        });

        Criteria.register(RuneCraftingCriteria.INSTANCE);
    }
}