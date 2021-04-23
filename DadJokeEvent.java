package Events;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DadJokeEvent extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		List<String> message = new LinkedList<String>(Arrays.asList(e.getMessage().getContentRaw().split(" ")));
		int index;
		String name = "";
		String response;
		
		for(String i : message) {
			
			if((i.toLowerCase().equals("i'm") || i.toLowerCase().equals("im")) && !e.getAuthor().isBot()) {
				
				index = message.indexOf(i);
				
				for(int a = index + 1; a < message.size(); a++) {
					name += message.get(a);
				}
				
				e.getChannel().sendMessage("Hi " + name + ", I'm JsutBot!").queue();
				break;
				
			}
		}
	}
}
