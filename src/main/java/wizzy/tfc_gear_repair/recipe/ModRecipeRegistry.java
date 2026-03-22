package wizzy.tfc_gear_repair.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static wizzy.tfc_gear_repair.Tfc_gear_repair.MODID;

public class ModRecipeRegistry {
    // Standard Forge Deferred Register for Recipe Serializers
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    // Register your custom serializer
    public static final RegistryObject<RecipeSerializer<ArmorWeldingRecipe>> ARMOR_WELDING_SERIALIZER =
            SERIALIZERS.register("armor_welding", ArmorWeldingRecipeSerializer::new);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
