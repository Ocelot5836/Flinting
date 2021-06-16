package com.syszee.example.common.registry;

import com.syszee.example.ModMain;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags
{
//    public static final ITag.INamedTag<Item> EXAMPLE = makeItemWrapperTag("example");

    /* Registry Methods */

    /**
     * Creates a new tag wrapper using the specified name.
     *
     * @param name The name of the tag
     * @return The tag wrapper
     */
    public static Tag.Named<Item> makeItemWrapperTag(String name)
    {
        return ItemTags.bind(ModMain.MOD_ID + ":" + name);
    }

    /**
     * Creates a new tag wrapper using the specified name.
     *
     * @param name The name of the tag
     * @return The tag wrapper
     */
    public static Tag.Named<Block> makeBlockWrapperTag(String name)
    {
        return BlockTags.bind(ModMain.MOD_ID + ":" + name);
    }

    /**
     * Creates a new tag wrapper using the specified name.
     *
     * @param name The name of the tag
     * @return The tag wrapper
     */
    public static Tag.Named<EntityType<?>> makeEntityWrapperTag(String name)
    {
        return EntityTypeTags.bind(ModMain.MOD_ID + ":" + name);
    }

    /**
     * Creates a new tag wrapper using the specified name.
     *
     * @param name The name of the tag
     * @return The tag wrapper
     */
    public static Tag.Named<Fluid> makeFluidWrapperTag(String name)
    {
        return FluidTags.bind(ModMain.MOD_ID + ":" + name);
    }
}
