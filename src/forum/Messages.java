package forum;
public class Messages {
	public static final String INVALID_COMMAND = "Invalid command!";
	public static final String USER_ALREADY_REGISTRED = "User with the same mail or username already exists";
	public static final String INVALID_LOGIN_DETAILS = "Wrong username/password or username not registered";
	public static final String ALREADY_LOGGED_IN = "Already logged in. In order to switch to another user - logout first";
	public static final String REG_ADMIN_NOT_ALLOWED = "Cannot register administrator";
	public static final String NO_PERMISSION = "You do not have enough permission to perform the desired operation";
	public static final String NOT_LOGGED = "Operation not permitted. You have to login first";
	public static final String NO_QUESTION_OPENED = "Operation not permitted. You have to open a question first";
	public static final String NO_QUESTIONS = "No questions";
	public static final String NO_QUESTION = "No such question";
	public static final String NO_ANSWER = "No such answer";
	public static final String NO_USER = "No such user";

	public static final String REGISTER_SUCCESS = "User %s with Id: %s successfully registered";
	public static final String LOGIN_SUCCESS = "User %s successfully logged in";
	public static final String LOGOUT_SUCCESS = "Logout success";
	public static final String POST_QUESTION_SUCCESS = "Question with Id: %s successfully posted";
	public static final String POST_ANSWER_SUCCESS = "Answer with Id: %s successfully posted";
	public static final String OPEN_QUESTION_SUCCESS = "Question %s opened";
	public static final String ROLE_CHANGE_SUCCESS = "Role successfully changed";
	public static final String BEST_ANSWER_SUCCESS = "Answer with Id: %s successfully made best answer";

	public static final String GUEST_WELCOME_MESSAGE = "Hey stranger, care to login/register?";
	public static final String USER_WELCOME_MESSAGE = "Welcome, %s!";
	public static final String GENERAL_HEADER_MESSAGE = "Hot questions: %s, Active users: %s";
}
