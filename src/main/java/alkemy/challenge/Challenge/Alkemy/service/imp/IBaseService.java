package alkemy.challenge.Challenge.Alkemy.service.imp;

import alkemy.challenge.Challenge.Alkemy.model.Base;

import java.util.List;

public interface IBaseService <E extends Base, ID > {

    public E create(E entity) throws Exception;
    public List<E> getAll() throws Exception;
    public E getById(ID id) throws Exception;
    public E update(E entity) throws Exception;
    public void deleteById(ID id) throws Exception;
}
