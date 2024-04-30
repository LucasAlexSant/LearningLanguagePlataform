package application;

import entities.User;
import services.UserDAO;

public class Program {
	public static void main(String[] args) {

		{
			UserDAO userDAO = new UserDAO();

			// Criar alguns usuários para teste
			User user1 = new User();
			user1.setNome("João Silva");
			user1.setEmail("joao@example.com");
			user1.setSenha("senha123");
			user1.setIdiomaDeInteresse("Inglês");

			User user2 = new User();
			user2.setNome("Maria Santos");
			user2.setEmail("maria@example.com");
			user2.setSenha("senha456");
			user2.setIdiomaDeInteresse("Espanhol");

			// Cadastrar os usuários
			userDAO.registerUser(user1);
			userDAO.registerUser(user2);
		}

	}
}
