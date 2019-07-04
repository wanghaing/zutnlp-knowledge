package edu.zut.cs.zutnlp.knowledge.base.dao;


import edu.zut.cs.zutnlp.knowledge.base.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * Implemented with Spring-Data-JPA Repository
 * <p>
 * Extend this interface if you want type safe (no casting necessary) DAO's for
 * your domain objects.
 *
 * @param <T>
 *            a type variable, Entity
 *
 * @param <PK>
 *            the primary key for that type，Entity Id
 *
 */
@NoRepositoryBean
public interface GenericDao<T extends BaseEntity, PK extends Serializable>
        extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {
}
