package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entities.User;

public class UserLogin {

	private SessionFactory sessionFactory;

	 public UserLogin() {
	        // Carrega as configurações do Hibernate a partir do arquivo hibernate.cfg.xml
	        Configuration configuration = new Configuration().configure();
	        // Cria a fábrica de sessões com base nas configurações
	        this.sessionFactory = configuration.buildSessionFactory();
	    }
	
		public User login(String email, String password) {
			try (Session session = sessionFactory.openSession()) {
	            // Consulta para encontrar o usuário com o e-mail fornecido
	            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
	            query.setParameter("email", email);
	            User user = query.uniqueResult();
	            
	            // Verifica se o usuário foi encontrado e se a senha está correta
	            if (user != null && user.getpassword().equals(password)) {
	                return user; // Retorna o usuário autenticado
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null; // Retorna null se as credenciais estiverem incorretas
	    }
		
	
	
}
