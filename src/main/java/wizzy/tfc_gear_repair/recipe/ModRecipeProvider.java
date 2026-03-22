package wizzy.tfc_gear_repair.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import java.util.function.Consumer;

import static wizzy.tfc_gear_repair.Tfc_gear_repair.MODID;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String[] metals = {"copper", "bismuth_bronze", "black_bronze", "bronze", "wrought_iron", "steel", "black_steel", "blue_steel", "red_steel"};

        String[] parts = {"helmet", "chestplate", "greaves", "boots"};

        for (String metal : metals) {
            for (String part : parts) {
                String armorItemId = "tfc:metal/" + part + "/" + metal;
                String sheetItemId = "tfc:metal/sheet/" + metal;

                ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(MODID, "welding_repair_" + metal + "_" + part);

                consumer.accept(new ArmorWeldingRecipeBuilder(recipeId, sheetItemId, armorItemId, 2));
            }
        }
    }
}
