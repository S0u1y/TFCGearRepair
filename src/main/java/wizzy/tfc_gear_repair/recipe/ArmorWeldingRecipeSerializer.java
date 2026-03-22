package wizzy.tfc_gear_repair.recipe;

import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.util.GsonHelper;

import org.slf4j.Logger;

public class ArmorWeldingRecipeSerializer implements RecipeSerializer<ArmorWeldingRecipe> {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public ArmorWeldingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input1 = Ingredient.fromJson(json.get("first_input"));
        Ingredient input2 = Ingredient.fromJson(json.get("second_input"));
        ItemStackProvider result = ItemStackProvider.fromJson(GsonHelper.getAsJsonObject(json, "result"));
        int tier = GsonHelper.getAsInt(json, "tier", 1); // Default tier to 1 if not specified

        return new ArmorWeldingRecipe(recipeId, input1, input2, tier, result, false);
    }

    @Override
    public ArmorWeldingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        // Read from server to client sync
        Ingredient input1 = Ingredient.fromNetwork(buffer);
        Ingredient input2 = Ingredient.fromNetwork(buffer);
        int tier = buffer.readVarInt();
        ItemStackProvider result = ItemStackProvider.fromNetwork(buffer);


        return new ArmorWeldingRecipe(recipeId, input1, input2, tier, result, false);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ArmorWeldingRecipe recipe) {
        recipe.getIngredients().get(0).toNetwork(buffer);
        recipe.getIngredients().get(1).toNetwork(buffer);
        buffer.writeVarInt(recipe.getTier());
        buffer.writeItemStack(recipe.getResultItem(RegistryAccess.EMPTY), false);
    }
}