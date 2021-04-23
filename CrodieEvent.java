package Events;

import java.util.Arrays;
import java.util.LinkedList;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CrodieEvent extends ListenerAdapter{
	public LinkedList<String> gangSigns = new LinkedList<String>(Arrays.asList("crodie", "krodie", "cronoid", "kronoid", "cronatid", "kronatid", "crods", "krods", "crodster", "krodster"));
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		
		for(String i : message) {
			if(gangSigns.contains(i.toLowerCase())) {
				e.getChannel().sendMessage(e.getMember().getUser().getName() + " you're not gangster. chill.").queue();
				break;
			}
		}
	}
}
