package forum;

import contracts.Forum;

public class ConsoleForumMain {

	public static void main(String[] args) {
		Forum forum = new ExtendedForum();
		forum.run();
	}

}
