package Events;

import Commands.BanishCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ForbiddenEvent extends ListenerAdapter {
    List forbidden = new LinkedList(Arrays.asList(
            "UWU", "OWO", "UwU", "OwO", "uwu", "owo"

    ));

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        LinkedList<String> message = new LinkedList<String>(Arrays.asList(e.getMessage().getContentRaw().split(" ")));

        if(forbidden.removeAll(message)){
            BanishCommand m_banish = new BanishCommand();
            m_banish.BanishPerson(e.getMember(), message, e);

        }
    }
}
