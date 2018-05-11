package humanResources.factory;

import humanResources.*;

public abstract class EmployeeFactory {
    public abstract EmployeeGroup createDepartment(String name);

    public abstract EmployeeGroup createDepartment(String name, int size);

    public abstract EmployeeGroup createDepartment(String name, Employee[] employees);

    public abstract EmployeeGroup createDepartment(Department group);

    public abstract EmployeeGroup createProject(String name);

    public abstract EmployeeGroup createProject(String name, Employee[] employees);

    public abstract EmployeeGroup createProject(Project project);

    public abstract GroupsManager createDepartmentManager(String name);

    public abstract GroupsManager createDepartmentManager(String name, int size);

    public abstract GroupsManager createDepartmentManager(String name, Department[] departments);

    public abstract GroupsManager createProjectManager();

    public abstract GroupsManager createProjectManager(EmployeeGroup[] group);

    public static EmployeeFactory getEmployeeFactory(GroupsFactoryTypesEnumeration type, String path) {
        switch (type) {
            case ORDINARY_GROUPS_FACTORY:
                return new OrdinaryEmployeeFactory();
            case TEXT_FILE_BASED_GROUPS_FACTORY:
                return new TextFileBasedEmployeeFactory(path);
            case BINARY_FILE_BASED_GROUPS_FACTORY:
                return new BinaryFileBasedEmployeeFactory(path);
            case SERIALIZED_FILE_BASED_GROUPS_FACTORY:
                return new SerializedFileBasedEmployeeFactory(path);
            case SOCKET_BASED_GROUPS_FACTORY:
                return null;
        }
        return null;
    }
}
