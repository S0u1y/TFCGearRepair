package wizzy.tfc_gear_repair.recipe;

import com.mojang.logging.LogUtils;
import net.dries007.tfc.common.blockentities.AnvilBlockEntity;
import net.dries007.tfc.common.recipes.WeldingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.slf4j.Logger;

public class ArmorWeldingRecipe extends WeldingRecipe {
    public ArmorWeldingRecipe(ResourceLocation id, Ingredient firstInput, Ingredient secondInput, int tier, ItemStackProvider output, boolean combineForgingBonus) {
        super(id, firstInput, secondInput, tier, output, combineForgingBonus);
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public ItemStack assemble(Inventory container, RegistryAccess registries) {
        ItemStack output = super.assemble(container, registries).copy();

        ItemStack damagedArmor = ItemStack.EMPTY;
        if (container instanceof AnvilBlockEntity.AnvilInventory anvilInventory) {
            var left = anvilInventory.getStackInSlot(0);
            var right = anvilInventory.getStackInSlot(1);

            if (left.is(output.getItem())){
                damagedArmor = left;
            }else{
                damagedArmor = right;
            }
        }


        // 3. Attempt the NBT transfer
        if (!damagedArmor.isEmpty() && damagedArmor.hasTag()) {
            // Repair logic example
            int newDamage = Math.max(0, damagedArmor.getDamageValue() - 500);
            damagedArmor.setDamageValue(newDamage);
        }

        return damagedArmor.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        // We will define this registry object in the next step
        return ModRecipeRegistry.ARMOR_WELDING_SERIALIZER.get();
    }

}
