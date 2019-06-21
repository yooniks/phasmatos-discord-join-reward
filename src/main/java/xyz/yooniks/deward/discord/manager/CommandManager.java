package xyz.yooniks.deward.discord.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import xyz.yooniks.deward.discord.DiscordBot;
import xyz.yooniks.deward.discord.command.Command;
import xyz.yooniks.deward.discord.command.impl.AwardCommand;

public class CommandManager {

  private final List<Command> commandList = new ArrayList<>();

  public void loadCommands(DiscordBot bot) {
    this.commandList.add(new AwardCommand(bot.getUserManager()));
  }

  public Optional<Command> getCommand(String arg) {
    return this.commandList
        .stream()
        .filter(command -> command.getInfo().getName().equalsIgnoreCase(arg)
            || command.getInfo().getAliases().contains(arg.toLowerCase()))
        .findFirst();
  }

}