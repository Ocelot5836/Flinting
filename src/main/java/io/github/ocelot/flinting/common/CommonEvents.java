package io.github.ocelot.flinting.common;

import io.github.ocelot.flinting.ModMain;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Ocelot
 */
@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class CommonEvents
{
    @SubscribeEvent
    public static void onEvent(PlayerEvent.HarvestCheck event)
    {
        Player player = event.getPlayer();
        BlockState state = event.getTargetBlock();
        ItemStack stack = player.getMainHandItem();
        ToolType tool = state.getHarvestTool();

        if (event.canHarvest() && event.getTargetBlock().is(ModRegistry.REQUIRES_FLINT) && tool != null && stack.getHarvestLevel(tool, player, state) < 0)
        {
            event.setCanHarvest(false);
        }
    }
}
