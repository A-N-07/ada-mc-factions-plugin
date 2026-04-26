# ada-factions
 
A lightweight factions plugin for small Minecraft servers. Built for simplicity — works out of the box without any extra plugins, but also fully compatible with LuckyPerms.
 
## Features
 
- Create and manage factions and regions
- Block protection — only faction members can break or place blocks in their faction's region
- Lobby spawn system with exact spawn location and zero spread
- Players choose their faction once on first join and are teleported to their faction's region
- Works without LuckyPerms — ops automatically get all permissions
## Requirements
 
- Paper 1.21+
- Java 21+
## Installation
 
1. Download the latest `ada-factions.jar` from the [releases page](https://github.com/A-N-07/ada-mc-factions-plugin/releases)
2. Place it in your server's `plugins/` folder
3. Restart your server
## Commands
 
### Region
| Command | Description |
|---|---|
| `/region add <name> <x1> <x2> <y1> <y2> <z1> <z2>` | Create a new region |
| `/region remove <name>` | Remove a region |
| `/region editname <oldname> <newname>` | Rename a region |
| `/region list` | List all regions |
| `/region setfaction <regionname> <factionname>` | Assign a faction to a region |
 
### Faction
| Command | Description |
|---|---|
| `/faction add <name>` | Create a new faction |
| `/faction remove <name>` | Remove a faction |
| `/faction editname <oldname> <newname>` | Rename a faction |
| `/faction list` | List all factions |
 
### Lobby
| Command | Description |
|---|---|
| `/lobby setlobby` | Set the lobby spawn to your current location |
| `/lobby unsetlobby` | Unset the lobby spawn |
 
### Player
| Command | Description |
|---|---|
| `/player setfaction <name>` | Choose your faction (can only be done once) |
| `/player list` | List all players and their factions |
 
## Permissions
 
| Permission | Description | Default |
|---|---|---|
| `adafactions.op` | Access to all admin commands | op |
| `adafactions.mod` | Access to moderator commands | op |

## How it works

Regions are the foundation of the plugin. The moment a region is created, it is fully protected — nobody can break or place blocks inside it by default.

Factions and regions are created independently, but conceptually a faction belongs to a region. To bring them together, an admin assigns a faction to a region using the `/region setfaction` command. This is what gives a faction its territory.

Players tie themselves to a faction using `/player setfaction`, which can only be done once. From that point on, a player is a member of that faction and can interact with blocks inside their faction's region.

So the chain is: **region → faction → player**. A player can break blocks in a region only if their faction is assigned to that region.

While it is possible to create factions without assigning them to a region, the plugin is designed with the assumption that every faction has a region. Not doing so may cause unexpected behavior.
 
## Setup Guide
 
1. Create a lobby region: `/region add spawn <x1> <x2> <y1> <y2> <z1> <z2>`
2. Set the lobby spawn: `/lobby setlobby`
3. Create your factions: `/faction add <name>`
4. Create faction regions: `/region add <name> <coords>`
5. Link factions to regions: `/region setfaction <regionname> <factionname>`
6. New players will spawn at lobby and choose their faction with `/player setfaction <name>`
7. Players will then spawn in the middle of their faction region a the highest place, excellent oppurtunity for a good bit of trolling.
## Data Storage
 
All data is stored locally in JSON files inside the `plugins/ada-factions/` folder:
 
- `players.json` — player data
- `factions.json` — faction data
- `regions.json` — region data
- `lobby.json` — lobby spawn location

## Troubleshooting
Editing these files only take effect after restarting the server.
Make sure to always leave atleast: [] inside the files if you want to delete everything.
Although editing these files is possible it can result in weird behaviour if you don't know what you are doing and will possibly break the plugin. If everything is beyond repair throw the whole folder of the plugin away. Restarting the server will make a new working folder. You'd have to start over, but the plugin will work again. 
