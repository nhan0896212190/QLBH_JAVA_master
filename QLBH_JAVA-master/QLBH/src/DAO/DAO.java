/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author Duy
 */
public interface DAO<T, K> {

    public List<T> get();

    public T get(K k);

    public void save(T t);

    public void delete(T t);

    public void update(T t);
}
