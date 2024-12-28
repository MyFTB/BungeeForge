# BungeeForge

BungeeForge is a Forge mod that implements BungeeCord (legacy) protocol.
At first it was developed to support Velocity legacy forwarding but it can also work for other Bungee proxies. 

## Disclaimer

This Repository is a fork of the original [BungeeForge](https://github.com/caunt/BungeeForge) repository. 
It also uses Code from the [Proxy-Compatible-Forge](https://github.com/adde0109/Proxy-Compatible-Forge) repository. See `WrappableArgumentNodeStubMixin.java` for more information.

This repository is not affiliated with the original BungeeForge repository. It is a fork that is maintained by the [MyFTB Server Network](https://myftb.de/) Team. Feel free to check out our Minecraft Servers.

## Supported Version of this Fork:

- NeoForge 1.21.1

Every other version is and will not be supported by this fork. If you have issues with other versions, please refer to the original repository.

## Changes of this Fork:

- Added support for NeoForge 1.21.1
- Added Fix for Argument Errors in NeoForge 1.21.1
- Disables users to join if they are not coming from a proxy

## Usage:
- Download the mod into mods/ directory on your server
- Setup Velocity (velocity.toml) option `player-info-forwarding-mode` to `legacy`
- Configure your Forge server (server.properties) option `online-mode` to `false`
- Connect through Velocity to your Forge server
- You should see skins and IP forwarding works

### WARN: Minecraft 1.13 +
This modification requires both Proxy and Forge to be compatible. Unfortunately, currently, Forge for Minecraft 1.13 (and newer) is not compatible with proxies due to the lack of a "reset" packet to gracefully reset client registries between server switches. To implement this functionality, there is an available plugin called [Ambassador](https://github.com/adde0109/Ambassador) which was thankfully developed by adde0109. **Install both [BungeeForge](https://github.com/caunt/BungeeForge/releases) and [Ambassador](https://github.com/adde0109/Ambassador/releases) on 1.13+ setups**. For Minecraft 1.12.2 and lower versions, BungeeForge works by itself.

## WARN: Velocity + Modpacks
The Environment Variable `velocity.max-known-packs` should be set to a high value to prevent the server from disconnecting the player due to the number of mods in the modpack. Try to set it to about 64 + the number of mods in the modpack.

**A too high value can lead to security issues, so be careful!**