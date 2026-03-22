package wizzy.tfc_gear_repair.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ArmorWeldingRecipeBuilder implements FinishedRecipe {
    private final ResourceLocation id;
    private final String leftItem;
    private final String rightItem;
    private final int tier;

    public ArmorWeldingRecipeBuilder(ResourceLocation id, String leftItem, String rightItem, int tier) {
        this.id = id;
        this.leftItem = leftItem;
        this.rightItem = rightItem;
        this.tier = tier;
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
        // This matches your custom JSON structure perfectly
        json.addProperty("tier", this.tier);

        JsonObject input1 = new JsonObject();
        input1.addProperty("item", this.leftItem);
        json.add("first_input", input1);

        JsonObject input2 = new JsonObject();
        input2.addProperty("item", this.rightItem);
        json.add("second_input", input2);

        JsonObject result = new JsonObject();
        result.addProperty("item", this.rightItem);
        json.add("result", result);
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        // Point this to the custom serializer we made earlier!
        return ModRecipeRegistry.ARMOR_WELDING_SERIALIZER.get();
    }

    @Override
    public JsonObject serializeAdvancement() {
        return null; // We can skip advancements for this
    }

    @Override
    public ResourceLocation getAdvancementId() {
        return null;
    }
}
