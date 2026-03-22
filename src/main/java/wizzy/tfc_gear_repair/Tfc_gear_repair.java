package wizzy.tfc_gear_repair;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wizzy.tfc_gear_repair.recipe.ModRecipeRegistry;

@Mod(Tfc_gear_repair.MODID)
public class Tfc_gear_repair {

    public static final String MODID = "tfc_gear_repair";

    @SuppressWarnings("Deprecated")
    public Tfc_gear_repair() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        ModRecipeRegistry.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
