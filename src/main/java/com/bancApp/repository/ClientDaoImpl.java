package com.bancApp.repository;

import com.bancApp.model.ClientEntity;
import com.bancApp.model.AccountEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientDaoImpl implements DAO<ClientEntity, Long> {
    private final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(ClientEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public ClientEntity findById(Long id) {
        return getSession().get(ClientEntity.class, id);
    }

    @Override
    public List<ClientEntity> findAll() {
        CriteriaQuery<ClientEntity> criteria = getSession().getCriteriaBuilder()
                                                           .createQuery(ClientEntity.class);
        criteria.select(criteria.from(ClientEntity.class));
        return getSession().createQuery(criteria)
                           .getResultList();
    }

    @Override
    public void update(ClientEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public void delete(ClientEntity entity) {
        getSession().remove(entity);
    }

    public List<AccountEntity> findComptesByClientId(Long id) {
        CriteriaQuery<AccountEntity> criteria = getSession().getCriteriaBuilder()
                                                           .createQuery(AccountEntity.class);
        Root<AccountEntity> compteEntity = criteria.from(AccountEntity.class);
        criteria.select(compteEntity)
                .where(getSession().getCriteriaBuilder()
                                   .equal(compteEntity.get("compteClientEntity")
                                                      .get("idClient"), id));
        return getSession().createQuery(criteria)
                           .getResultList();
    }

    public ClientEntity findByDni(String dni) {
        return getSession().createQuery("FROM ClientEntity WHERE DNI = :dni", ClientEntity.class)
                           .setParameter("dni", dni)
                           .uniqueResult();
    }

}
