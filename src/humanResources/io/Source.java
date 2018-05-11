package humanResources.io;

public interface Source<T> {

    void load(T t);
    void store(T t);
    boolean delete(T t);
    boolean create(T t);

    /*
    - public void load(T t) - восстановление состояния объекта из некоторого источника
    - public void store(T t) – запись состояния объекта в некоторый источник
    - delete(T t) – удаляющий информацию о состоянии объекта из некоторого
    источника
    - create(T t) – создающий новый источник, хранящий состояние объекта
     */
}
