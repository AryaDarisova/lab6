package humanResources.factory;

import humanResources.*;
import humanResources.exceptions.AlreadyAddedException;
import humanResources.io.ControlledDepartment;
import humanResources.io.ControlledDepartmentManager;
import humanResources.io.ControlledProject;
import humanResources.io.ControlledProjectManager;

public class OrdinaryEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment(String name) {
        return new ControlledDepartment(name);
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return new ControlledDepartment(name, size);
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new ControlledDepartment(name, employees);
    }

    @Override
    public EmployeeGroup createDepartment(Department group) {
        return new ControlledDepartment(group);
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new ControlledProject(name);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new ControlledProject(name, employees);
    }

    @Override
    public EmployeeGroup createProject(Project project) {
        return new ControlledProject(project);
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        return new ControlledDepartmentManager(name);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        return new ControlledDepartmentManager(name, size);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] departments) {
        return new ControlledDepartmentManager(name, departments);
    }

    @Override
    public GroupsManager createProjectManager() {
        return new ControlledProjectManager();
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] group) {
        try {
            return new ControlledProjectManager(group);
        } catch (AlreadyAddedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
