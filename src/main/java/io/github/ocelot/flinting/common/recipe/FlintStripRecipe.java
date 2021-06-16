package io.github.ocelot.flinting.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.ocelot.flinting.common.ModRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author Ocelot
 */
public class FlintStripRecipe extends ShapelessRecipe
{
    public FlintStripRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients)
    {
        super(id, group, result, ingredients);
        this.getIngredients().add(0, Ingredient.of(Items.FLINT));
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container)
    {
        NonNullList<ItemStack> list = super.getRemainingItems(container);

        for (int i = 0; i < list.size(); ++i)
        {
            ItemStack stack = container.getItem(i);
            if (stack.getItem() == Items.FLINT)
            {
                list.set(i, new ItemStack(Items.FLINT));
                break;
            }
        }

        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRegistry.FLINT_STRIP.get();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FlintStripRecipe>
    {
        @Override
        public FlintStripRecipe fromJson(ResourceLocation id, JsonObject json)
        {
            String group = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> ingredients = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (ingredients.isEmpty())
            {
                throw new JsonParseException("No ingredients for flint strip recipe");
            }
            else if (ingredients.size() > 8)
            {
                throw new JsonParseException("Too many ingredients for flint strip recipe the max is 8");
            }
            else
            {
                ItemStack itemstack = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result"));
                return new FlintStripRecipe(id, group, itemstack, ingredients);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray json)
        {
            NonNullList<Ingredient> items = NonNullList.create();

            for (int i = 0; i < json.size(); ++i)
            {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty())
                {
                    items.add(ingredient);
                }
            }

            return items;
        }

        @Override
        public FlintStripRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf)
        {
            String group = buf.readUtf();
            int count = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(count, Ingredient.EMPTY);

            for (int j = 0; j < ingredients.size(); ++j)
                ingredients.set(j, Ingredient.fromNetwork(buf));

            ItemStack result = buf.readItem();
            return new FlintStripRecipe(id, group, result, ingredients);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FlintStripRecipe recipe)
        {
            buf.writeUtf(recipe.getGroup());
            buf.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients())
                ingredient.toNetwork(buf);
            buf.writeItem(recipe.getResultItem());
        }
    }
}
