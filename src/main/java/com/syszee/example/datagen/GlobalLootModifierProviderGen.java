package com.syszee.example.datagen;

import com.syszee.example.ModMain;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class GlobalLootModifierProviderGen extends GlobalLootModifierProvider
{
    public GlobalLootModifierProviderGen(DataGenerator gen)
    {
        super(gen, ModMain.MOD_ID);
    }

    @Override
    protected void start()
    {
    }
}
