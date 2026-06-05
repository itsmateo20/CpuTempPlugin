package org.itsmateo20.cputemp;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.io.BufferedReader;
import java.io.FileReader;

public class CpuTempExpansion extends PlaceholderExpansion {

    private final CpuTempPlugin plugin;

    public CpuTempExpansion(CpuTempPlugin plugin) {
        this.plugin = plugin;
    }

    // CRITICAL: Tells PAPI not to unregister this expansion when /papi reload is
    // used
    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "srv";
    }

    @Override
    public String getAuthor() {
        return "itsmateo20";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        boolean isRaw = params.equalsIgnoreCase("cpu_temp");
        boolean isMini = params.equalsIgnoreCase("cpu_temp_mini");

        if (!isRaw && !isMini) {
            return null;
        }

        java.io.File tempFile = new java.io.File("/sys/class/thermal/thermal_zone0/temp");

        if (!tempFile.exists()) {
            return "Unsupported OS";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
            String line = br.readLine();
            if (line == null)
                return "N/A";

            double temp = Double.parseDouble(line.trim()) / 1000.0;
            String formattedTemp = String.format("%.1f°C", temp);

            // If they want raw text without color tags
            if (isRaw) {
                return formattedTemp;
            }

            // If they want MiniMessage color tags (<green>, <yellow>, <red>)
            if (temp < 55.0) {
                return "<green>" + formattedTemp + "</green>";
            } else if (temp < 75.0) {
                return "<yellow>" + formattedTemp + "</yellow>";
            } else {
                return "<red>" + formattedTemp + "</red>";
            }

        } catch (Exception e) {
            return "Error";
        }
    }
}