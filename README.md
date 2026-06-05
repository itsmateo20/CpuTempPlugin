# CpuTempPlugin

A lightweight Minecraft server plugin and PlaceholderAPI (PAPI) expansion that reads and displays the host server's CPU temperature.

> **Important Note:** This plugin reads temperature data directly from the Linux file system (`/sys/class/thermal/thermal_zone0/temp`). It is intended for Linux-based hosting environments (like Ubuntu, Debian, Pterodactyl nodes, etc.). It will return `Unsupported OS` if run on Windows or environments without access to this thermal zone.

---

## 🚀 Features

* **Direct Hardware Reading:** Fetches the actual system temperature without heavy external dependencies.
* **PlaceholderAPI Integration:** Seamlessly expose your CPU stats to scoreboards, tablists, chat, or action bars.
* **MiniMessage Formatting:** Native support for modern color formatting (`<green>`, `<yellow>`, `<red>`) based on how hot your CPU is running.

---

## 📊 Placeholders

The expansion uses the identifier `srv`. You can use the following placeholders in any plugin that supports PlaceholderAPI:

| Placeholder | Output Example | Description |
| :--- | :--- | :--- |
| `%srv_cpu_temp%` | `43.5°C` | Returns the raw, uncolored temperature string. |
| `%srv_cpu_temp_mini%` | `<green>43.5°C</green>` | Returns the temperature wrapped in MiniMessage color tags based on thresholds. |

### 🌡️ MiniMessage Color Thresholds

* **Cool:** `< 55.0°C` 🟢 Green
* **Warm:** `55.0°C - 74.9°C` 🟡 Yellow
* **Hot:** `≥ 75.0°C` 🔴 Red

---

## 🛠️ Requirements

* **Server Software:** Paper, Purpur, or Spigot (designed for `1.20+`)
* **Java Version:** Java 17 or higher
* **Dependencies:** [PlaceholderAPI](https://modrinth.com/project/lKEzGugV/)
* **OS:** Linux (specifically with access to `/sys/class/thermal/thermal_zone0/temp`)

---

## 📦 Installation

1. Download or compile the `cputemp-1.0.jar`.
2. Place the JAR file into your server's `plugins/` folder.
3. Ensure **PlaceholderAPI** is also installed in your `plugins/` folder.
4. Restart your server.
5. Verification: You should see `[CpuTempPlugin] CpuTemp enabled` in your server console log.
6. `/papi parse me %srv_cpu_temp%` should show your current CPU temperature in the chat.

---

## 💻 Compilation

If you want to build the project yourself, clone the repository and run Maven:

```bash
mvn clean package
```
