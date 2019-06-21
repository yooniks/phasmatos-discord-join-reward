package xyz.yooniks.deward.discord;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.deward.config.Config;
import xyz.yooniks.deward.discord.listener.GuildMemberJoinListener;
import xyz.yooniks.deward.discord.listener.MessageReceivedListener;
import xyz.yooniks.deward.discord.manager.CommandManager;
import xyz.yooniks.deward.user.UserManager;

public class DiscordBot {

  private final JDA jda;
  private final CommandManager commandManager;
  private final UserManager userManager;

  public DiscordBot(Plugin plugin, String token) {
    this.commandManager = new CommandManager();
    try {
      this.jda = this.createUser(token);
    } catch (LoginException ex) {
      throw new IllegalArgumentException("DiscordBot could not be created!", ex);
    }
    this.userManager = new UserManager(plugin);
  }

  public void init() {
    this.commandManager.loadCommands(this);
    this.jda.addEventListener(
        new MessageReceivedListener(this.commandManager),
        new GuildMemberJoinListener(this.userManager));
  }

  private JDA createUser(String token) throws LoginException {
    final JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT)
        .setToken(token)
        .setGame(Game.listening(Config.BOT$GAME$NAME))
        .setAutoReconnect(true);
    return jdaBuilder.build();
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public CommandManager getCommandManager() {
    return this.commandManager;
  }

  public JDA getJda() {
    return this.jda;
  }

}
