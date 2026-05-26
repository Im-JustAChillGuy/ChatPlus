package com.JustAChillGuy.chatplus.client.mixin;

import net.minecraft.client.gui.components.ChatComponent;;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    @ModifyArg(
            method = "addMessage(Lnet/minecraft/text/Text;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/ChatHud;logChatMessage(Lnet/minecraft/text/Text;)V"
            ),
            index = 0
    )
    private Text addTimestamp(Text message) {

        String time = LocalTime.now().format(FORMATTER);

        return Text.literal("[" + time + "] ")
                .append(message);
    }
}
