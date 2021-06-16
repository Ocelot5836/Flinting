package com.syszee.example.datagen;

import com.syszee.example.ModMain;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagGen extends FluidTagsProvider
{
    public FluidTagGen(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ModMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
    }
}
