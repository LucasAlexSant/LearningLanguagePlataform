package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entities.User;

public class UserDAO {
	private SessionFactory sessionFactory;

	public UserDAO() {
		// Carrega as configurações do Hibernate a partir do arquivo hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();
		// Cria a fábrica de sessões com base nas configurações
		this.sessionFactory = configuration.buildSessionFactory();
	}

	public void registerUser(User user) {

		// Verifica se o e-mail já está cadastrado
		if (existEmail(user.getEmail())) {
			System.out.println("Error: This email is already registered.");
			return;
		}

		// Abre uma nova sessão com o banco de dados
		Session session = sessionFactory.openSession();
		// Inicia uma nova transação
		Transaction transaction = session.beginTransaction();
		// Salva o usuário no banco de dados
		session.save(user);
		// Confirma a transação
		transaction.commit();
		System.out.println("Registration success");

	}

	public boolean existEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			// Cria uma consulta para verificar se o e-mail já está cadastrado
			Query<Long> query = session.createQuery("SELECT COUNT(*) FROM User WHERE email = :email", Long.class);
			query.setParameter("email", email);
			// Executa a consulta e verifica se o resultado é maior que zero
			Long result = query.uniqueResult();
			return result != null && result > 0;
		} catch (Exception e) {

			return false;
		}
	}

	public void removeUser(User user) {

		try (Session session = sessionFactory.openSession()) {
	        // Inicia uma nova transação
	        Transaction transaction = session.beginTransaction();
	        
	        // Cria uma consulta HQL para encontrar o usuário pelo e-mail
	        Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
	        query.setParameter("email", user.getEmail());
	        
	        // Executa a consulta e retorna o resultado único
	        User userToDelete = query.uniqueResult();
	        
	        // Se o usuário existir, deleta-o do banco de dados
	        if (userToDelete != null) {
	            session.delete(userToDelete);
	            // Confirma a transação
	            transaction.commit();
	            System.out.println("User deletion success");
	        } else {
	            // Se o usuário não for encontrado, exibe uma mensagem de erro
	            System.out.println("Error: User not found.");
	        }
	    } catch (Exception e) {
	        // Se ocorrer uma exceção, imprime o stack trace e exibe uma mensagem de erro
	        e.printStackTrace();
	        System.out.println("Error deleting user: " + e.getMessage());
	    }
	}
}