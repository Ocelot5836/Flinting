package com.syszee.example.common.registry;

import com.syszee.example.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModMain.MOD_ID);

    // public static final RegistryObject<SoundEvent> EXAMPLE = register("example");

    /* Registry Methods */

    /**
     * Registers a new sound event under the specified id.
     *
     * @param id The id of the sound event
     */
    private static RegistryObject<SoundEvent> register(String id)
    {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(ModMain.MOD_ID, id)));
    }
}