package Commands;

import java.util.List;

import general.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RestoreCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		List<Role> recoverRoles = e.getGuild().getRolesByName("Shadow Realm", true);
		Role recoverRole = recoverRoles.get(0);
		
		
		if(message[0].equalsIgnoreCase("$Restore") && message.length > 1) {
			//List<Member> toRestore = e.getGuild().getMembersByName(message[1], false);
			Member restore = null; 
			try {
				restore = e.getMessage().getMentionedMembers().get(0);
			} catch(IndexOutOfBoundsException k) {
				restoreHelp(e.getChannel());
			}
			
			
			try {
				for(Role i : ListenerThreadHandler.storage.peopleRoles.get(restore)) {
					e.getGuild().addRoleToMember(restore, i).queue();
				}
				e.getGuild().removeRoleFromMember(restore, recoverRole).queue();
				e.getChannel().sendMessage(message[1] + "'s honour has been restored").queue();
			} catch(IndexOutOfBoundsException f) {
				e.getChannel().sendMessage("Error: User not found. Be sure to enter the real name of the person you wish to restore, not a nickname.").queue();
			} catch(HierarchyException a) {
				e.getChannel().sendMessage("Cannot restore someone so high!").queue();
			} catch(NullPointerException v) {
				e.getChannel().sendMessage("User was not banished").queue();
			}
			
		} else if(message[0].equalsIgnoreCase("$Restore")) {
			restoreHelp(e.getChannel());
		}
	}
	public static void restoreHelp(TextChannel channel) {
		channel.sendMessage("Use command like so: \n$restore [@User]").queue();
	}
}
