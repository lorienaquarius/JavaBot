package Events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinEvent extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent e){

        e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(639850946666561597l)).queue();

    }
}
