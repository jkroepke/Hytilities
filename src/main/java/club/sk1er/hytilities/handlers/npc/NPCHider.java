package club.sk1er.hytilities.handlers.npc;

import club.sk1er.hytilities.config.HytilitiesConfig;
import club.sk1er.hytilities.util.LobbyChecker;
import club.sk1er.mods.core.util.MinecraftUtils;
import com.google.common.collect.Collections2;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;

public class NPCHider {

    @SubscribeEvent
    public void onEntityRender(RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (!HytilitiesConfig.hytilitiesNpcHider || !MinecraftUtils.isHypixel() || !LobbyChecker.playerIsInLobby()) {
            return;
        }

        // hypixel marks npc uuids as version 2
        if (event.entity.getUniqueID().version() == 2) {
            event.setCanceled(true);
        }
    }

    @SuppressWarnings("unused")
    public static Collection<NetworkPlayerInfo> hideTabNpcs(Collection<NetworkPlayerInfo> playerInfoCollection) {
        if (!MinecraftUtils.isHypixel() || !HytilitiesConfig.hytilitiesHideNpcsInTab) {
            return playerInfoCollection;
        } else {
            return Collections2.filter(playerInfoCollection, player -> player != null && player.getGameProfile().getId().version() != 2);
        }
    }
}