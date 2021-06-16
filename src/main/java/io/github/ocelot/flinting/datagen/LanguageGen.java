package io.github.ocelot.flinting.datagen;

import io.github.ocelot.flinting.ModMain;
import io.github.ocelot.flinting.common.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * @author Ocelot
 */
public class LanguageGen extends LanguageProvider
{
    public LanguageGen(DataGenerator gen)
    {
        super(gen, ModMain.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        this.add("itemGroup." + ModMain.MOD_ID, "Flinting");
        this.addItem(ModRegistry.FLINT_SWORD, "Flint Sword");
        this.addItem(ModRegistry.FLINT_SHOVEL, "Flint Shovel");
        this.addItem(ModRegistry.FLINT_PICKAXE, "Flint Pickaxe");
        this.addItem(ModRegistry.FLINT_AXE, "Flint Hatchet");
        this.addItem(ModRegistry.FLINT_HOE, "Flint Hoe");
        this.addItem(ModRegistry.WOOD_STRING, "Wood String");
    }
}
