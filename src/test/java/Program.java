
import entities.User;
import services.UserLogin;

public class Program {
	public static void main(String[] args) {

		
		UserLogin userLogin = new UserLogin();
		
		User user1 = userLogin.login("maria@example.com", "password456");
		if (user1 != null) {
            System.out.println("Login realizado com sucesso para o usuário: " + user1.getName());
        } else {
            System.out.println("Falha no login. Credenciais incorretas.");
        }
		

	}
}
