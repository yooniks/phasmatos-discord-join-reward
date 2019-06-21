package xyz.yooniks.deward.discord.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {

  CommandBase getInfo();

  void execute(MessageReceivedEvent event, String... args);

}
