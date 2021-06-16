package com.syszee.example;

import com.syszee.example.common.registry.*;
import com.syszee.example.datagen.*;
import io.github.ocelot.sonar.Sonar;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public class ModMain
{
    public static final String MOD_ID = "example";

//    public static final SortedItemGroup TAB = new SortedItemGroup(MOD_ID);

    public ModMain()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Sonar.init(bus);
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.TILE_ENTITIES.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModLootTables.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(bus);
        ModSounds.SOUNDS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event)
    {
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
    }

    private void dataSetup(GatherDataEvent event)
    {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeServer())
        {
            BlockTagGen blockTagGen = new BlockTagGen(dataGenerator, existingFileHelper);
            dataGenerator.addProvider(new RecipeGen(dataGenerator));
            dataGenerator.addProvider(new LootTableGen(dataGenerator));
            dataGenerator.addProvider(new GlobalLootModifierProviderGen(dataGenerator));
            dataGenerator.addProvider(blockTagGen);
            dataGenerator.addProvider(new ItemTagGen(dataGenerator, blockTagGen, existingFileHelper));
            dataGenerator.addProvider(new FluidTagGen(dataGenerator, existingFileHelper));
            dataGenerator.addProvider(new EntityTypeTagGen(dataGenerator, existingFileHelper));
        }
        if (event.includeClient())
        {
            dataGenerator.addProvider(new ItemModelGen(dataGenerator));
            dataGenerator.addProvider(new LanguageGen(dataGenerator));
        }
    }
}
