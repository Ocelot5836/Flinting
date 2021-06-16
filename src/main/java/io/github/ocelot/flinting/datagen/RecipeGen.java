package io.github.ocelot.flinting.datagen;

import io.github.ocelot.flinting.common.ModRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author Ocelot
 */
public class RecipeGen extends RecipeProvider
{
    public RecipeGen(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(ModRegistry.FLINT_SWORD.get())
                .pattern(" F ")
                .pattern("WFW")
                .pattern(" S ")
                .define('F', Items.FLINT)
                .define('W', ModRegistry.WOOD_STRING.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wood_string", has(ModRegistry.WOOD_STRING.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModRegistry.FLINT_SHOVEL.get())
                .pattern("F")
                .pattern("W")
                .pattern("S")
                .define('F', Items.FLINT)
                .define('W', ModRegistry.WOOD_STRING.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wood_string", has(ModRegistry.WOOD_STRING.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModRegistry.FLINT_PICKAXE.get())
                .pattern("FWF")
                .pattern(" S ")
                .pattern(" S ")
                .define('F', Items.FLINT)
                .define('W', ModRegistry.WOOD_STRING.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wood_string", has(ModRegistry.WOOD_STRING.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModRegistry.FLINT_AXE.get())
                .pattern("FW")
                .pattern(" S")
                .define('F', Items.FLINT)
                .define('W', ModRegistry.WOOD_STRING.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wood_string", has(ModRegistry.WOOD_STRING.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModRegistry.FLINT_HOE.get())
                .pattern("FW")
                .pattern(" S")
                .pattern(" S")
                .define('F', Items.FLINT)
                .define('W', ModRegistry.WOOD_STRING.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_wood_string", has(ModRegistry.WOOD_STRING.get()))
                .save(consumer);
        FlintStripRecipeBuilder.flintStrip(ModRegistry.WOOD_STRING.get())
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("tick", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY))
                .save(consumer);
    }
}