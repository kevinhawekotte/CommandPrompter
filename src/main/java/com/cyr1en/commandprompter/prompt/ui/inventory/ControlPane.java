package com.cyr1en.commandprompter.prompt.ui.inventory;

import com.cyr1en.commandprompter.CommandPrompter;
import com.cyr1en.commandprompter.prompt.PromptContext;
import com.cyr1en.commandprompter.util.Util;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.function.Consumer;

public class ControlPane extends StaticPane {
    private static final int DEFAULT_PREV_LOC = 2;
    private static final int DEFAULT_NEXT_LOC = 6;
    private static final int DEFAULT_CANCEL_LOC = 4;

    private final CommandPrompter plugin;
    private final PaginatedPane paginatedPane;
    private final ChestGui gui;
    private final PromptContext ctx;

    private int prevLoc;
    private int nextLoc;
    private int cancelLoc;

    public ControlPane(CommandPrompter plugin, PaginatedPane pane, ChestGui gui, PromptContext ctx, int numCols) {
        super(0, numCols - 1, 9, 1);
        this.plugin = plugin;
        prevLoc = plugin.getPromptConfig().previousColumn() - 1;
        nextLoc = plugin.getPromptConfig().nextColumn() - 1;
        cancelLoc = plugin.getPromptConfig().cancelColumn() - 1;
        this.paginatedPane = pane;
        this.ctx = ctx;
        this.gui = gui;
        verifyLocs();
        setupButtons();
    }

    private void verifyLocs() {
        if (prevLoc == nextLoc || prevLoc == cancelLoc || nextLoc == cancelLoc) {
            this.prevLoc = DEFAULT_PREV_LOC;
            this.nextLoc = DEFAULT_NEXT_LOC;
            this.cancelLoc = DEFAULT_CANCEL_LOC;
        }
    }

    private void setupButtons() {
        var pages = paginatedPane.getPages() - 1;

        var prevMatString = plugin.getPromptConfig().previousItem();
        var prevIS = new ItemStack(Util.getCheckedMaterial(prevMatString, Material.FEATHER));
        addItem(plugin.getPromptConfig().previousText(), prevIS, prevLoc,
                c -> {
                    c.setCancelled(true);
                    var next = Math.max((paginatedPane.getPage() - 1), 0);
                    paginatedPane.setPage(next);
                    gui.update();
                });

        var nextMatString = plugin.getPromptConfig().nextItem();
        var nextIS = new ItemStack(Util.getCheckedMaterial(nextMatString, Material.FEATHER));
        addItem(plugin.getPromptConfig().nextText(), nextIS, nextLoc,
                c -> {
                    c.setCancelled(true);
                    var next = Math.min((paginatedPane.getPage() + 1), pages);
                    paginatedPane.setPage(next);
                    gui.update();
                });

        var cancelMatString = plugin.getPromptConfig().cancelItem();
        var cancelIS = new ItemStack(Util.getCheckedMaterial(cancelMatString, Material.FEATHER));
        addItem(plugin.getPromptConfig().cancelText(), cancelIS, cancelLoc,
                c -> {
                    c.setCancelled(true);
                    plugin.getPromptManager().cancel(ctx.getSender());
                    ((Player) ctx.getSender()).closeInventory();
                });
    }

    private void addItem(String name, ItemStack itemStack, int x, Consumer<InventoryClickEvent> consumer) {
        var itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(Util.color(name));
        itemStack.setItemMeta(itemMeta);
        addItem(new GuiItem(itemStack, consumer), x, 0);
    }
}
