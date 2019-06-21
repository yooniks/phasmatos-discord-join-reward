package xyz.yooniks.deward.discord.listener;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.yooniks.deward.user.UserManager;

public class GuildMemberJoinListener extends ListenerAdapter {

  private final UserManager userManager;

  public GuildMemberJoinListener(UserManager userManager) {
    this.userManager = userManager;
  }

  @Override
  public void onGuildMemberJoin(GuildMemberJoinEvent event) {
    this.userManager.createUser(event.getMember());
  }

}
