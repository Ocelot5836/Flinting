package io.github.ocelot.flinting;

import io.github.ocelot.flinting.common.ModRegistry;
import io.github.ocelot.flinting.datagen.ItemModelGen;
import io.github.ocelot.flinting.datagen.LanguageGen;
import io.github.ocelot.flinting.datagen.RecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author Ocelot
 */
@Mod(ModMain.MOD_ID)
public class ModMain
{
    public static final String MOD_ID = "flinting";
    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID)
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModRegistry.FLINT_SWORD.get());
        }
    };

    public ModMain()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.register(bus);
        bus.addListener(this::dataSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void dataSetup(GatherDataEvent event)
    {
        DataGenerator dataGenerator = event.getGenerator();
        if (event.includeServer())
        {
            dataGenerator.addProvider(new RecipeGen(dataGenerator));
        }
        if (event.includeClient())
        {
            dataGenerator.addProvider(new ItemModelGen(dataGenerator));
            dataGenerator.addProvider(new LanguageGen(dataGenerator));
        }
    }
}
