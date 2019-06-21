package xyz.yooniks.deward.discord.listener;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.yooniks.deward.config.Config;
import xyz.yooniks.deward.discord.command.Command;
import xyz.yooniks.deward.discord.manager.CommandManager;

public class MessageReceivedListener extends ListenerAdapter {

  private final CommandManager commandManager;

  public MessageReceivedListener(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    try {
      final String[] args = event.getMessage().getContentDisplay().split(" ");
      if (args[0].startsWith(Config.BOT$PREFIX)) {
        final Optional<Command> command = this.commandManager
            .getCommand(args[0].split(
                Config.BOT$PREFIX)[1]);
        command.ifPresent(cmd -> cmd.execute(event, args));

        event.getMessage().delete().queueAfter(250, TimeUnit.MILLISECONDS);
      }
    } catch (Exception ignored) {
    }
  }
}
