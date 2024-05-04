
import services.UserLogin;

public class Program {
	public static void main(String[] args) {

		
		UserLogin userLogin = new UserLogin();
		
		userLogin.login("maria@example.com", "password456");
		
		

	}
}
