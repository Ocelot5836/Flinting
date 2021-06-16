package com.syszee.example.common.registry;

import com.syszee.example.ModMain;
import com.syszee.example.common.loot.AdditionalLootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootTables
{
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, ModMain.MOD_ID);
    public static final RegistryObject<AdditionalLootModifier.Serializer> ADDITIONAL_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("additional_loot", AdditionalLootModifier.Serializer::new);
}
