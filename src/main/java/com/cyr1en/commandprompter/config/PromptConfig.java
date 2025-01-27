package com.cyr1en.commandprompter.config;

import com.cyr1en.commandprompter.config.annotations.field.ConfigNode;
import com.cyr1en.commandprompter.config.annotations.field.NodeComment;
import com.cyr1en.commandprompter.config.annotations.field.NodeDefault;
import com.cyr1en.commandprompter.config.annotations.field.NodeName;
import com.cyr1en.commandprompter.config.annotations.type.ConfigHeader;
import com.cyr1en.commandprompter.config.annotations.type.ConfigPath;
import com.cyr1en.commandprompter.config.annotations.type.Configuration;
import com.cyr1en.kiso.mc.configuration.base.Config;

@Configuration
@ConfigPath("prompt-config.yml")
@ConfigHeader({"Prompts", "Configuration"})
public record PromptConfig(
        Config rawConfig,

        @ConfigNode
        @NodeName("PlayerUI.Skull-Name-Format")
        @NodeDefault("&6%s")
        @NodeComment({
                "PlayerUI formatting", "",
                "Skull-Name-Format - The display name format",
                "                    for the player heads", "",
                "Size - the size of the UI (multiple of 9, between 18-54)", "",
                "Sorted - Should the player heads be sorted?",
                "Per-World - Only show player in the current world?"
        })
        String skullNameFormat,

        @ConfigNode
        @NodeName("PlayerUI.Size")
        @NodeDefault("54")
        int playerUISize,

        @ConfigNode
        @NodeName("PlayerUI.Previous.Item")
        @NodeDefault("Feather")
        String previousItem,

        @ConfigNode
        @NodeName("PlayerUI.Previous.Column")
        @NodeDefault("3")
        int previousColumn,

        @ConfigNode
        @NodeName("PlayerUI.Previous.Text")
        @NodeDefault("&7◀◀ Previous")
        String previousText,

        @ConfigNode
        @NodeName("PlayerUI.Next.Item")
        @NodeDefault("Feather")
        String nextItem,

        @ConfigNode
        @NodeName("PlayerUI.Next.Column")
        @NodeDefault("7")
        int nextColumn,

        @ConfigNode
        @NodeName("PlayerUI.Next.Text")
        @NodeDefault("Next ▶▶")
        String nextText,

        @ConfigNode
        @NodeName("PlayerUI.Cancel.Item")
        @NodeDefault("Barrier")
        String cancelItem,

        @ConfigNode
        @NodeName("PlayerUI.Cancel.Column")
        @NodeDefault("5")
        int cancelColumn,

        @ConfigNode
        @NodeName("PlayerUI.Cancel.Text")
        @NodeDefault("&7Cancel ✘")
        String cancelText,

        @ConfigNode
        @NodeName("PlayerUI.Sorted")
        @NodeDefault("false")
        boolean sorted,

        @ConfigNode
        @NodeName("PlayerUI.Per-World")
        @NodeDefault("false")
        boolean isPerWorld,

        @ConfigNode
        @NodeName("AnvilGUI.Enable-Title")
        @NodeDefault("true")
        @NodeComment({
                "AnvilUI formatting",
                "",
                "Enable-Title - Show the first line of the prompt",
                "(if with {br}) as title of Anvil GUI",
                "",
                "Item - The item to place on the Anvil GUI",
                "",
                "Enchanted - Do you want the item enchanted?",
                "",
                "Custom-Title - If title is enabled, and if custom",
                "title is not empty, CommandPrompter will use this instead"
        })
        boolean enableTitle,

        @ConfigNode
        @NodeName("AnvilGUI.Custom-Title")
        @NodeDefault("")
        String customTitle,

        @ConfigNode
        @NodeName("AnvilGUI.Item")
        @NodeDefault("Paper")
        String anvilItem,

        @ConfigNode
        @NodeName("AnvilGUI.Enchanted")
        @NodeDefault("false")
        boolean anvilEnchanted,

        @ConfigNode
        @NodeName("TextPrompt.Clickable-Cancel")
        @NodeDefault("true")
        @NodeComment({"Text Prompt Config"})
        boolean sendCancelText,

        @ConfigNode
        @NodeName("TextPrompt.Cancel-Message")
        @NodeDefault("&7[&c&l✘&7]")
        String textCancelMessage,

        @ConfigNode
        @NodeName("TextPrompt.Cancel-Hover-Message")
        @NodeDefault("&7Click here to cancel command completion")
        String textCancelHoverMessage
) {
}
