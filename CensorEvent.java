package Events;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CensorEvent extends ListenerAdapter{
	
	LinkedList<String> swears = new LinkedList<String>(Arrays.asList(
		"fuck", "nigger", "nigga", "bitch", "cunt", "fucker", "fucking", "shit", "n1gga", "knee ger", "knee gerr", "fuckin"
	));
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		for(String i : message) {
			if(swears.contains(i.toLowerCase())) {
				List<Role> roleToAssign = e.getJDA().getRolesByName("Shadow Realm", true);
				List<Role> defaultRole = e.getMember().getRoles();
				String name = e.getMember().getUser().getName();
				String nameID = e.getMember().getUser().getId();
				Member toBanish = e.getMember();
				try {
					
					e.getChannel().sendMessage("Uh Oh! <@" + nameID + "> said a bad word!").queue();
//					e.getGuild().addRoleToMember(toBanish, roleToAssign.get(0)).queue();
//					e.getChannel().sendMessage("<@" + nameID + "> has been banished to the shadow realm!").queue();
//					TimeUnit.SECONDS.sleep(30);
//					e.getGuild().removeRoleFromMember(toBanish, roleToAssign.get(0)).queue();
//					e.getChannel().sendMessage("<@" + nameID + ">'s honour has been restored").queue();
					
					
					
				} catch(IndexOutOfBoundsException r) {
					System.out.println("Error: Role could not be found");
				}
				
				
				break;
				
			}
		}
		
	}
}
