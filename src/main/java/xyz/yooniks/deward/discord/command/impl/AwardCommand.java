package xyz.yooniks.deward.discord.command.impl;

import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.yooniks.deward.DewardPlugin;
import xyz.yooniks.deward.config.Config;
import xyz.yooniks.deward.discord.command.Command;
import xyz.yooniks.deward.discord.command.CommandBase;
import xyz.yooniks.deward.user.UserManager;

public class AwardCommand implements Command {

  private final UserManager userManager;

  private final CommandBase commandBase = new CommandBase("nagroda");

  public AwardCommand(UserManager userManager) {
    this.userManager = userManager;
  }

  @Override
  public CommandBase getInfo() {
    return this.commandBase;
  }

  @Override
  public void execute(MessageReceivedEvent event, String... args) {

    EmbedBuilder offlinePlayer = new EmbedBuilder();
    offlinePlayer.setDescription(":interrobang: Blad: Ten gracz jest offline!");
    offlinePlayer
        .setFooter("\uD83C\uDF89 " + event.getAuthor().getName() + " uzyl tej komendy!", null);
    offlinePlayer.setColor(Color.red);

    EmbedBuilder awardedPlayer = new EmbedBuilder();
    awardedPlayer.setDescription(":tada: Nagroda zostanie nadana do 30 sekund!");
    awardedPlayer
        .setFooter("\uD83C\uDF89 " + event.getAuthor().getName() + " uzyl tej komendy!", null);
    awardedPlayer.setColor(Color.green);

    EmbedBuilder neededArgument = new EmbedBuilder();
    neededArgument.setDescription(":interrobang: Musisz podac swoj nick aby odebrac nagrode!");
    neededArgument
        .setFooter("\uD83C\uDF89 " + event.getAuthor().getName() + " uzyl tej komendy!", null);
    neededArgument.setColor(Color.red);

    EmbedBuilder awardedBefore = new EmbedBuilder();
    awardedBefore.setDescription(":interrobang: Juz odebrales swoja nagrode!");
    awardedBefore
        .setFooter("\uD83C\uDF89 " + event.getAuthor().getName() + " uzyl tej komendy!", null);
    awardedBefore.setColor(Color.red);

    EmbedBuilder wrongChannel = new EmbedBuilder();
    wrongChannel.setDescription(
        ":interrobang: Nagrode mozesz odebrac na kanale <#" + Config.BOT$AWARD$CHANNEL + ">");
    wrongChannel
        .setFooter("\uD83C\uDF89 " + event.getAuthor().getName() + " uzyl tej komendy!", null);
    wrongChannel.setColor(Color.red);

    if (!event.getChannel().getId().equals(Config.BOT$AWARD$CHANNEL)) {
      event.getChannel().sendMessage(wrongChannel.build()).queue();
    } else {
      if (args.length == 1) {
        event.getChannel().sendMessage(neededArgument.build()).queue();
        this.userManager.createUser(event.getMember());
      } else {
        Player player = Bukkit.getPlayer(args[1]);
        this.userManager.createUser(event.getMember());
        if (this.userManager.getAwardStatus(event.getMember())) {
          if (player == null) {
            event.getChannel().sendMessage(offlinePlayer.build()).queue();
          } else {
            event.getChannel().sendMessage(awardedPlayer.build()).queue();
            this.userManager.setAwardStatus(event.getMember(),
                !this.userManager.getAwardStatus(event.getMember()));
            synchronized (this) {
              Bukkit.getScheduler().runTask(DewardPlugin.getInstance(), () ->
                  Bukkit.getServer().dispatchCommand(
                      DewardPlugin.getInstance().getServer().getConsoleSender(),
                      Config.BOT$AWARD$COMMAND.replace("{NICK}", player.getName()))
              );
            }
          }
        } else {
          event.getChannel().sendMessage(awardedBefore.build()).queue();
        }
      }
    }
  }
}
