package humanResources.io;

import humanResources.EmployeeGroup;

public interface FileSource extends Source<EmployeeGroup> {

    void setPath(String path);
    String getPath();

    /*
    - public void setPath(String path) – изменяющий путь к файлу, в который
    записывается состояние объекта
    - public String getPath() – возвращающий путь к файлу, в который записывается
    состояние объекта
     */
}
