package Commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import general.*;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanishCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		Member banish = null;
		LinkedList<String> message = new LinkedList(Arrays.asList(e.getMessage().getContentRaw().split(" ")));

		String first = message.remove(0);
		if (first.equalsIgnoreCase("$banish") && message.size() >= 1) {
			//List<Member> toBanish = e.getGuild().getMembersByName(message[1], false);
			try {
				banish = e.getMessage().getMentionedMembers().get(0);
				System.out.println(banish);
			} catch (IndexOutOfBoundsException d) {
				banishHelp(e.getChannel());
			}
			BanishPerson(banish, message, e);

		}else if(first.equalsIgnoreCase("$banish")) {
			banishHelp(e.getChannel());
		}
	}
	public static void banishHelp(TextChannel channel) {
		channel.sendMessage("Use command like so: \n$banish [@User]").queue();
	}
	public void BanishPerson(Member member, LinkedList<String> message, GuildMessageReceivedEvent e) {

		String person = message.remove(0);
		List<Role> banishRoles = e.getGuild().getRolesByName("Shadow Realm", true);
		Role banishRole = banishRoles.get(0);



		try {
			LinkedList<Role> roles = new LinkedList<Role>(member.getRoles());

			if (!roles.contains(banishRole)) {
				ListenerThreadHandler.storage.peopleRoles.put(member, roles);
			}

			for (Role i : roles) {
				e.getGuild().removeRoleFromMember(member, i).queue();
			}

			e.getGuild().addRoleToMember(member, banishRole).queue();

			e.getChannel().sendMessage("Banished " + member.getNickname() + " to the shadow realm").queue();
		} catch (IndexOutOfBoundsException f) {
			e.getChannel().sendMessage("Error: User not found. Be sure to enter the real name of the person you wish to banish, not a nickname.").queue();
		} catch (HierarchyException a) {
			e.getChannel().sendMessage("Can't banish someone of such high rank!").queue();
		} catch (NullPointerException t) {
			e.getChannel().sendMessage("User is not in the server").queue();
		}
	}

}
