package ua.caunt.bungeeforge.bridge.network.handshake.client;

import com.mojang.authlib.properties.Property;

import java.util.UUID;

public interface CHandshakePacketBridge {
    String bungee$getSpoofedAddress();
    UUID bungee$getSpoofedId();
    Property[] bungee$getSpoofedProperties();
}
