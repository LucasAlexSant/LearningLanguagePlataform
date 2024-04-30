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
            System.out.println("Erro: Este e-mail já está cadastrado.");
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
    
}