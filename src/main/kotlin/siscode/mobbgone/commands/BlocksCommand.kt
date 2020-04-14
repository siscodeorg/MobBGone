package siscode.mobbgone.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import siscode.mobbgone.MobBGone
import siscode.mobbgone.inventories.BlocksInventory
import java.util.logging.Level

class BlocksCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(MobBGone.cfg.spawnproofBlocks.isEmpty()) return false
        if(args.isNotEmpty()) return false
        if(sender is Player)
        {
            val p : Player = sender as Player
            p.openInventory(BlocksInventory().inventory)
            return true
        }
        if(sender is ConsoleCommandSender)
        {
            MobBGone.logger.log(Level.INFO,"List of all registered blocks")
            MobBGone.cfg.spawnproofBlocks.forEach { MobBGone.logger.info("\t- ${it?.name}")}
            return true
        }
        return false
    }

}