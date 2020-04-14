package siscode.mobbgone.handlers

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import siscode.mobbgone.inventories.BlocksInventory

class InventoryHandler : Listener {
    @EventHandler
    fun onPickup(event : InventoryPickupItemEvent)
    {
        if(event.inventory.holder is BlocksInventory) event.isCancelled = true
    }

    @EventHandler
    fun onClick(event : InventoryClickEvent)
    {
        if(event.inventory.holder is BlocksInventory) event.isCancelled = true
    }

    @EventHandler
    fun onDrag(event: InventoryDragEvent)
    {
        if(event.inventory.holder is BlocksInventory) event.isCancelled = true
    }

    @EventHandler
    fun onMove(event : InventoryMoveItemEvent)
    {
        if(event.source.holder is BlocksInventory) event.isCancelled = true
    }
}