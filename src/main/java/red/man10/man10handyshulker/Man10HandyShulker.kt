package red.man10.man10handyshulker

import org.bukkit.plugin.java.JavaPlugin

class Man10HandyShulker : JavaPlugin() {
    override fun onEnable() {
        plugin = this

        server.pluginManager.registerEvents(Listener(), plugin)
    }

    override fun onDisable() {
    }

    companion object {
        lateinit var plugin: Man10HandyShulker

        const val prefix = "§e[&dM&fa&an&f10§5HandyShulker§e]§r"
    }
}