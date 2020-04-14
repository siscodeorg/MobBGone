package siscode.mobbgone.inventories

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import siscode.mobbgone.MobBGone

class BlocksInventory : InventoryHolder
{
    override fun getInventory(): Inventory {
        val inventorySize : Int
        val size = MobBGone.cfg.blocksPerEntry.size;
        inventorySize = if(size % 9 == 0) size
        else size + (9 - size % 9)
        val inv : Inventory = Bukkit.createInventory(this, inventorySize, "Registered Blocks")
        for(item in MobBGone.cfg.blocksPerEntry)
        {
            if(item.value.isEmpty()) continue
            val itemStack = ItemStack(item.value.first()!!,item.value.size)
            itemStack.lore = item.value.map { "\t- ${it?.name}" }
            val meta = itemStack.itemMeta
            meta.setDisplayName(item.key)
            itemStack.itemMeta = meta
            inv.addItem(itemStack)
        }
        return inv
    }
}