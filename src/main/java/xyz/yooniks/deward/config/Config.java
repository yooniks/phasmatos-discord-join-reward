package xyz.yooniks.deward.config;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.yooniks.deward.DewardPlugin;

public class Config {

  public static String BOT$TOKEN;
  public static String BOT$PREFIX;
  public static String BOT$GAME$NAME;
  public static String BOT$AWARD$COMMAND;
  public static String BOT$AWARD$CHANNEL;

  static {
    FileConfiguration fileConfiguration = DewardPlugin.getInstance().getConfig();
    BOT$TOKEN = fileConfiguration.getString("bot.token");
    BOT$PREFIX = fileConfiguration.getString("bot.prefix");
    BOT$GAME$NAME = fileConfiguration.getString("bot.game");
    BOT$AWARD$COMMAND = fileConfiguration.getString("bot.award.command");
    BOT$AWARD$CHANNEL = fileConfiguration.getString("bot.award.channel");
  }
}
