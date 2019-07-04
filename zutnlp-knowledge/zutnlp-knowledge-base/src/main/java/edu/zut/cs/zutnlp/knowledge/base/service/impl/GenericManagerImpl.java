package edu.zut.cs.zutnlp.knowledge.base.service.impl;


import edu.zut.cs.zutnlp.knowledge.base.dao.GenericDao;
import edu.zut.cs.zutnlp.knowledge.base.domain.BaseEntity;
import edu.zut.cs.zutnlp.knowledge.base.service.GenericManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Transactional
@Service
public class GenericManagerImpl<T extends BaseEntity, PK extends Serializable> implements GenericManager<T, PK> {

    protected GenericDao<T, PK> dao;

    public void delete(PK id) {
        this.dao.delete(id);
    }

    public List<T> findAll() {
        return this.dao.findAll();
    }

    public Page<T> findAll(Pageable page) {
        Page<T> result = this.dao.findAll(page);
        return result;
    }

    public T findById(PK id) {
        return this.dao.getOne(id);
    }

    public List<T> save(Iterable<T> entities) {
        return this.dao.save(entities);
    }

    public T save(T entity) {
        return this.dao.saveAndFlush(entity);
    }

}
