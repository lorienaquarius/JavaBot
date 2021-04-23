package Events;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JsutEvent extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		List<String> message = new LinkedList<String>(Arrays.asList(e.getMessage().getContentRaw()));
		Member poorSoul = e.getGuild().getMember(e.getAuthor());
		
		for(String i : message) {
			if(i.equalsIgnoreCase("jsut")) {
				e.getChannel().sendMessage("lmao "+ poorSoul.getAsMention() + " can't spell").queue();
			}
		}
		
	}
}
