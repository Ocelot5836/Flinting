package com.syszee.example.common.registry;

import com.syszee.example.ModMain;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModMain.MOD_ID);

    // public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example", () -> new ExampleBlock(AbstractBlock.Properties.from(Blocks.AIR)));
    // public static final RegistryObject<TileEntityType<ExampleTileEntity>> EXAMPLE_TILE_ENTITY = TILE_ENTITIES.register("example", () -> TileEntityType.Builder.create(ExampleTileEntity::new, EXAMPLE_BLOCK.get()).build(null));

    /* Registry Methods */

    /**
     * Registers the specified block with a bound {@link BlockItem} under the specified id.
     *
     * @param name  The id of the block
     * @param block The block to register
     * @return The registry reference
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block)
    {
        return register(name, block, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    /**
     * Registers the specified block with a bound {@link BlockItem} under the specified id.
     *
     * @param name           The id of the block
     * @param block          The block to register
     * @param itemProperties The properties of the block item to register
     * @return The registry reference
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties)
    {
        return register(name, block, object -> new BlockItem(object.get(), itemProperties));
    }

    /**
     * Registers the specified block with a bound item under the specified id.
     *
     * @param name  The id of the block
     * @param block The block to register
     * @param item  The item to register or null for no item
     * @return The registry reference
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Function<RegistryObject<T>, Item> item)
    {
        RegistryObject<T> object = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> item.apply(object));
        return object;
    }
}