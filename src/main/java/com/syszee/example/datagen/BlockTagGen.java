package com.syszee.example.datagen;

import com.syszee.example.ModMain;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGen extends BlockTagsProvider
{
    public BlockTagGen(DataGenerator generatorIn, ExistingFileHelper existingFileHelper)
    {
        super(generatorIn, ModMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
    }
}
