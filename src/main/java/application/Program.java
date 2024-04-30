package application;

import entities.User;
import services.UserDAO;

public class Program {
	public static void main(String[] args) {

		{
			UserDAO userDAO = new UserDAO();

			// Criar alguns usuários para teste
			User user1 = new User();
			user1.setName("João Silva");
			user1.setEmail("joao@example.com");
			user1.setpassword("password123");
			user1.setinterestedLanguage("Inglês");

			User user2 = new User();
			user2.setName("Maria Santos");
			user2.setEmail("maria@example.com");
			user2.setpassword("password456");
			user2.setinterestedLanguage("Espanhol");

			// Cadastrar os usuários
			userDAO.registerUser(user1);
			userDAO.registerUser(user2);
		}

	}
}
