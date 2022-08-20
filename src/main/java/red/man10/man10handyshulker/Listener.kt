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
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta
import red.man10.man10handyshulker.Man10HandyShulker.Companion.prefix


class Listener : Listener {
    @EventHandler
    fun onClick(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_AIR) return

        val p = e.player

        val itemInMainhand = p.inventory.itemInMainHand
        val itemInOffhand = p.inventory.itemInOffHand

        if (itemInMainhand.type != Material.SHULKER_BOX) return

        val item: ItemStack = itemInMainhand
        if (item.itemMeta is BlockStateMeta) {
            val im = item.itemMeta as BlockStateMeta
            if (im.blockState is ShulkerBox) {
                val shulker = im.blockState as ShulkerBox
                val inv = Bukkit.createInventory(null, 27, Component.text(prefix))
                inv.contents = shulker.inventory.contents
                p.openInventory(inv)
            }
        }

    }

    @EventHandler
    fun onClose(e: InventoryCloseEvent) {

    }
}