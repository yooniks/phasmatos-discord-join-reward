package xyz.yooniks.deward;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.deward.config.Config;
import xyz.yooniks.deward.discord.DiscordBot;

public final class DewardPlugin extends JavaPlugin {

  public static DewardPlugin getInstance() {
    return getPlugin(DewardPlugin.class);
  }

  @Override
  public void onEnable() {
    this.saveDefaultConfig();
    new DiscordBot(this, Config.BOT$TOKEN).init();
  }

}
