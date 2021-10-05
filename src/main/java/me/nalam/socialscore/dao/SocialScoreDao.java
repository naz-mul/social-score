package me.nalam.socialscore.dao;

import me.nalam.socialscore.entities.SocialScore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class SocialScoreDao implements Dao<SocialScore, String> {
    private Session currentSession;
    private Transaction currentTransaction;

    public SocialScoreDao() {
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Optional<SocialScore> get(String id) {
        return Optional.ofNullable((SocialScore) getCurrentSession().get(SocialScore.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SocialScore> getAll() {
        return (List<SocialScore>) getCurrentSession().createQuery("from SocialScore").list();
    }

    @Override
    public void save(SocialScore socialScore) {
        getCurrentSession().save(socialScore);
    }

    @Override
    public void update(SocialScore socialScore, String[] params) {
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    @Override
    public void delete(SocialScore socialScore) {
        getCurrentSession().delete(socialScore);
    }
}
