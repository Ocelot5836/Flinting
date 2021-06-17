package io.github.ocelot.flinting.datagen;

import io.github.ocelot.flinting.ModMain;
import io.github.ocelot.flinting.common.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
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
        this.tag(ModRegistry.REQUIRES_FLINT).addTag(BlockTags.LOGS);
    }
}
