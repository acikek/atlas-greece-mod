package com.acikek.atlasgreece.item;

import com.acikek.atlasgreece.AtlasGreece;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MoussakaItem extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(10.0f)
            .alwaysEdible()
            .meat()
            .build();

    public static final Settings SETTINGS = new FabricItemSettings().food(FOOD_COMPONENT);

    public static final Item INSTANCE = new MoussakaItem();

    public MoussakaItem() {
        super(SETTINGS);
    }

    public static void register() {
        Registry.register(Registries.ITEM, AtlasGreece.id("moussaka"), INSTANCE);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->
            entries.addAfter(Items.COOKED_RABBIT, INSTANCE)
        );
    }
}
