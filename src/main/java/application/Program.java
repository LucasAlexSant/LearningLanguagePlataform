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
			user1.setPassword("password123");
			user1.setInterestedLanguage("Inglês");

			User user2 = new User();
			user2.setName("Maria Santos");
			user2.setEmail("maria@example.com");
			user2.setPassword("password456");
			user2.setInterestedLanguage("Espanhol");
			
			User user3 = new User(null, "Lucas Santana", "Lucas@examlpe.com", "password123", "Inglês");
			User user4 = new User(null, "Heitor Prado", "heitor2@example.com","password123", "japanese");
			
			
			// Cadastrar os usuários
			userDAO.registerUser(user1);
			userDAO.registerUser(user2);
			userDAO.registerUser(user3);
			userDAO.removeUser(user3);

		}

	}
}
