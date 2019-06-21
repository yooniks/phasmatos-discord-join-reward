package xyz.yooniks.deward.user;

import net.dv8tion.jda.core.entities.Member;
import org.bukkit.plugin.Plugin;

public class UserManager {

  private final Plugin plugin;

  public UserManager(Plugin plugin) {
    this.plugin = plugin;
  }

  public boolean getAwardStatus(Member member) {
    return this.plugin.getConfig().getBoolean("users." + member.getUser().getId() + ".award");
  }

  public void setAwardStatus(Member member, boolean b) {
    this.plugin.getConfig().set("users." + member.getUser().getId() + ".award", b);
    this.plugin.saveConfig();
  }

  public boolean createUser(Member member) {
    final boolean exists = this.plugin.getConfig()
        .contains("users." + member.getUser().getId());
    if (!exists) {
      this.plugin.getConfig().set("users." + member.getUser().getId() + ".award", true);
      this.plugin.getConfig()
          .set("users." + member.getUser().getId() + ".name", member.getUser().getName());
      this.plugin.saveConfig();
    }
    return exists;
  }

}
