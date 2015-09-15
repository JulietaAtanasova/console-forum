package contracts;

public interface Post {
	int getId();

	void setId(int id);

	String getBody();

	void setBody(String body);

	User getAuthor();

	void setAuthor(User author);
}
