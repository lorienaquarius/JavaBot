package general;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Storage {
	
	public static HashMap<Member, LinkedList<Role>> peopleRoles = new HashMap<Member, LinkedList<Role>>();
	
}
