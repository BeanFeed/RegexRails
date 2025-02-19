# RegexRails

A Minecraft Paper plugin that enables smart minecart management through configurable signs and detector rails. Control minecart flow based on their display names using powerful comparison modes and modification options.

## Features

- Three operation modes: Compare, Set, and Append
- Multiple comparison options: equals, contains, regex
- Real-time minecart detection and modification
- Redstone-controlled name modifications
- Simple sign-based configuration

## Installation

1. Place the plugin jar file in your Spigot (Or any compatible) server's plugin directory
2. Restart the server or run `/reload confirm`

## Usage Guide

### Compare Mode

Configure your sign with:

```
Line 1: Compare
Line 2: equals/contains/regex
Line 3: [comparison value]
```
##### Example:

```
Line 1: Compare
Line 2: equals
Line 3: Coal Cart
```

The detector rail will activate when a minecart named exactly "Coal Cart" passes over it.

### Set Mode

Configure your sign with:
```
Line 1: Set
Line 2: New Name
Line 3: [empty]
```
##### Example:
```
Line 1: Set
Line 2: Iron Ore Cart
Line 3:
```
Any minecart passing over the detector rail while powered by redstone will have its display name changed to "Iron Ore Cart".

### Append Mode

Configure your sign with:
```
Line 1: Append
Line 2: Text to append
Line 3: [empty]
```
##### Example:
```
Line 1: Append
Line 2: [Full]
Line 3:
```
Minecarts passing over the detector rail while powered by redstone will have "[Full]" added to their current display name.