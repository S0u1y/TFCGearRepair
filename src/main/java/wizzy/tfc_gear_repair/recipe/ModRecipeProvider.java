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
        // 1. Define TFC's standard armor metals
        // Note: You might need to adjust the anvil tier based on the metal in a real scenario
        String[] metals = {"copper", "bismuth_bronze", "black_bronze", "bronze", "wrought_iron", "steel", "black_steel", "blue_steel", "red_steel"};

        // 2. Define TFC's armor types
        String[] parts = {"helmet", "chestplate", "greaves", "boots"};

        // 3. Loop through every combination!
        for (String metal : metals) {
            for (String part : parts) {
                // Construct the TFC item IDs (e.g., "tfc:metal/chestplate/bronze")
                String armorItemId = "tfc:metal/" + part + "/" + metal;
                String sheetItemId = "tfc:metal/sheet/" + metal;

                // Create a unique file name for the JSON (e.g., "your_mod_id:welding_repair_bronze_chestplate")
                ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(MODID, "welding_repair_" + metal + "_" + part);

                // Generate it! (Assuming a flat tier 2 for this example)
                consumer.accept(new ArmorWeldingRecipeBuilder(recipeId, sheetItemId, armorItemId, 2));
            }
        }
    }
}
