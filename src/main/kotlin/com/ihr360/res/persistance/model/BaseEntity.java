package com.ihr360.res.persistance.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Basic entity that is base class of all entity classes here
 * @param <T>
 */
@MappedSuperclass
public class BaseEntity<T> {
    @Id
    @GeneratedValue
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
