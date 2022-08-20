package red.man10.man10handyshulker

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.ShulkerBox
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.BlockStateMeta
import red.man10.man10handyshulker.Man10HandyShulker.Companion.prefix


class Listener : Listener {
    @EventHandler
    fun onClick(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_AIR) return

        val p = e.player

        val itemInMainhand = p.inventory.itemInMainHand

        if (itemInMainhand.type != Material.SHULKER_BOX) return

        if (itemInMainhand.itemMeta is BlockStateMeta) {
            val meta = itemInMainhand.itemMeta as BlockStateMeta
            if (meta.blockState is ShulkerBox) {
                val shulker = meta.blockState as ShulkerBox
                val inv = Bukkit.createInventory(null, 27, Component.text(prefix))
                inv.contents = shulker.inventory.contents
                p.openInventory(inv)
            }
        }

    }

    @EventHandler
    fun onClose(e: InventoryCloseEvent) {
        val p = e.player

        if (e.view.title() != Component.text(prefix)) return

        p.sendMessage("Same title.")

        val inv = e.inventory

        val itemInMainhand = p.inventory.itemInMainHand
        val meta = itemInMainhand.itemMeta as BlockStateMeta
        val shulker = meta.blockState as ShulkerBox

        shulker.inventory.contents = inv.contents
        meta.blockState = shulker
        itemInMainhand.itemMeta = meta

        p.sendMessage("All done. Nice.( ﾟ◡ﾟ)")
    }
}