package ua.caunt.bungeeforge.mixin.network.handshake.client;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ua.caunt.bungeeforge.bridge.network.handshake.client.CHandshakePacketBridge;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Mixin(value = net.minecraft.network.handshake.client.CHandshakePacket.class)
public class CHandshakePacket implements CHandshakePacketBridge {
    private UUID spoofedId;
    private Property[] spoofedProperties;

    private static final Gson gson = new Gson();

    @Redirect(method = "read", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;readUtf(I)Ljava/lang/String;"))
    private String bungee$readUtf(PacketBuffer buf, int length) {
        String data = buf.readUtf(Short.MAX_VALUE);
        String[] chunks = data.split("\0");

        if (chunks.length <= 2)
            return data;

        Property[] properties = gson.fromJson(chunks[3], Property[].class);

        spoofedId = UUIDTypeAdapter.fromString(chunks[2]);
        spoofedProperties = Arrays.stream(properties)
                .filter(packet -> !isFmlMarker(packet))
                .toArray(Property[]::new);

        return Arrays.stream(properties)
                .filter(packet -> isFmlMarker(packet))
                .findFirst()
                .map(property -> chunks[1] + "\0" + property.getValue().split("\u0001")[1] + "\0")
                .orElseGet(() -> chunks[1]);
    }

    private static boolean isFmlMarker(Property property)
    {
        return Objects.equals(property.getName(), "extraData") && property.getValue().startsWith("\u0001FML");
    }

    @Override
    public UUID bungee$getSpoofedId() {
        return spoofedId;
    }

    @Override
    public Property[] bungee$getSpoofedProperties() {
        return spoofedProperties;
    }
}