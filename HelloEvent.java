package Events;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter{
	String[] responses = {"Hiya", "hiya there", "what's poppin", "'sup ", "Greetings", "What's good"};
	LinkedList<String> greetings = new LinkedList<String>(Arrays.asList(
				"HELLO", "HI", "YO", "WHAT'S UP", "HELLO THERE", "WUS GOOD?"
			));
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] message = event.getMessage().getContentRaw().split(" ");
		String toRespond = responses[(int)Math.round(Math.random() * (responses.length - 1))] + " ";
		String name = event.getMember().getUser().getName();
		
		if(greetings.contains(message[0].toUpperCase()) && !event.getMember().getUser().isBot()) {

			event.getChannel().sendMessage(toRespond + name).queue();
		}
		
	}

}
