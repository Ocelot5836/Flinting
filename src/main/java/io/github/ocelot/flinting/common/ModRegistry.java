package io.github.ocelot.flinting.common;

import io.github.ocelot.flinting.ModMain;
import io.github.ocelot.flinting.common.recipe.FlintStripRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Ocelot
 */
public class ModRegistry
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModMain.MOD_ID);

    private static final Tier FLINT = new Tier()
    {
        @Override
        public int getUses()
        {
            return 49;
        }

        @Override
        public float getSpeed()
        {
            return 1.0F;
        }

        @Override
        public float getAttackDamageBonus()
        {
            return -1.0F;
        }

        @Override
        public int getLevel()
        {
            return 0;
        }

        @Override
        public int getEnchantmentValue()
        {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient()
        {
            return Ingredient.of(Items.FLINT);
        }
    };

    public static final Tag.Named<Block> REQUIRES_FLINT = BlockTags.createOptional(new ResourceLocation(ModMain.MOD_ID, "requires_tool"));

    public static final RegistryObject<Item> FLINT_SWORD = ITEMS.register("flint_sword", () -> new SwordItem(FLINT, 3, -2.4F, new Item.Properties().tab(ModMain.TAB)));
    public static final RegistryObject<Item> FLINT_SHOVEL = ITEMS.register("flint_shovel", () -> new ShovelItem(FLINT, 1.5F, -3.0F, new Item.Properties().tab(ModMain.TAB)));
    public static final RegistryObject<Item> FLINT_PICKAXE = ITEMS.register("flint_pickaxe", () -> new PickaxeItem(FLINT, 1, -2.8F, new Item.Properties().tab(ModMain.TAB)));
    public static final RegistryObject<Item> FLINT_AXE = ITEMS.register("flint_axe", () -> new AxeItem(FLINT, 6.0F, -3.2F, new Item.Properties().tab(ModMain.TAB)));
    public static final RegistryObject<Item> FLINT_HOE = ITEMS.register("flint_hoe", () -> new HoeItem(FLINT, 0, -3.0F, new Item.Properties().tab(ModMain.TAB)));
    public static final RegistryObject<Item> WOOD_STRING = ITEMS.register("wood_string", () -> new Item(new Item.Properties().tab(ModMain.TAB)));

    public static final RegistryObject<RecipeSerializer<FlintStripRecipe>> FLINT_STRIP = RECIPE_SERIALIZERS.register("flint_strip", FlintStripRecipe.Serializer::new);

    /**
     * Registers all deferred registers onto the mod bus.
     *
     * @param modBus The mod bus instance
     */
    public static void register(IEventBus modBus)
    {
        ITEMS.register(modBus);
        RECIPE_SERIALIZERS.register(modBus);
    }
}