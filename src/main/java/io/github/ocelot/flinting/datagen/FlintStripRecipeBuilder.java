package io.github.ocelot.flinting.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.ocelot.flinting.common.ModRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FlintStripRecipeBuilder
{
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients;
    private final Advancement.Builder advancement;
    private String group;

    public FlintStripRecipeBuilder(ItemLike arg, int i)
    {
        this.result = arg.asItem();
        this.count = i;
        this.ingredients = new ArrayList<>();
        this.advancement = Advancement.Builder.advancement();
    }

    public static FlintStripRecipeBuilder flintStrip(ItemLike item)
    {
        return new FlintStripRecipeBuilder(item, 1);
    }

    public static FlintStripRecipeBuilder flintStrip(ItemLike item, int count)
    {
        return new FlintStripRecipeBuilder(item, count);
    }

    public FlintStripRecipeBuilder requires(Tag<Item> tag)
    {
        return this.requires(Ingredient.of(tag));
    }

    public FlintStripRecipeBuilder requires(ItemLike item)
    {
        return this.requires(item, 1);
    }

    public FlintStripRecipeBuilder requires(ItemLike item, int count)
    {
        for (int j = 0; j < count; ++j)
        {
            this.requires(Ingredient.of(item));
        }

        return this;
    }

    public FlintStripRecipeBuilder requires(Ingredient arg)
    {
        return this.requires(arg, 1);
    }

    public FlintStripRecipeBuilder requires(Ingredient ingredient, int count)
    {
        for (int j = 0; j < count; ++j)
        {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public FlintStripRecipeBuilder unlockedBy(String string, CriterionTriggerInstance arg)
    {
        this.advancement.addCriterion(string, arg);
        return this;
    }

    public FlintStripRecipeBuilder group(String string)
    {
        this.group = string;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer)
    {
        this.save(consumer, Registry.ITEM.getKey(this.result));
    }

    public void save(Consumer<FinishedRecipe> consumer, String string)
    {
        ResourceLocation lv = Registry.ITEM.getKey(this.result);
        if ((new ResourceLocation(string)).equals(lv))
        {
            throw new IllegalStateException("Flint Strip Recipe " + string + " should remove its 'save' argument");
        }
        else
        {
            this.save(consumer, new ResourceLocation(string));
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation arg)
    {
        this.ensureValid(arg);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(arg)).rewards(AdvancementRewards.Builder.recipe(arg)).requirements(RequirementsStrategy.OR);
        consumer.accept(new FlintStripRecipeBuilder.Result(arg, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancement, new ResourceLocation(arg.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + arg.getPath())));
    }

    private void ensureValid(ResourceLocation arg)
    {
        if (this.advancement.getCriteria().isEmpty())
        {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation arg, Item arg2, int i, String string, List<Ingredient> list, Advancement.Builder arg3, ResourceLocation arg4)
        {
            this.id = arg;
            this.result = arg2;
            this.count = i;
            this.group = string;
            this.ingredients = list;
            this.advancement = arg3;
            this.advancementId = arg4;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject)
        {
            if (!this.group.isEmpty())
            {
                jsonObject.addProperty("group", this.group);
            }

            JsonArray jsonArray = new JsonArray();

            for (Ingredient lv : this.ingredients)
            {
                jsonArray.add(lv.toJson());
            }

            jsonObject.add("ingredients", jsonArray);
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1)
            {
                jsonObject2.addProperty("count", this.count);
            }

            jsonObject.add("result", jsonObject2);
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRegistry.FLINT_STRIP.get();
        }

        @Override
        public ResourceLocation getId()
        {
            return this.id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement()
        {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId()
        {
            return this.advancementId;
        }
    }
}
