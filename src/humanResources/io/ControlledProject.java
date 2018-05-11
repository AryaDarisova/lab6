package humanResources.io;

import humanResources.Employee;
import humanResources.Project;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged = false;

    public ControlledProject(String name) {
        super(name);
    }

    public ControlledProject(String name, Employee[] employees) {
        super(name, employees);
    }

    public ControlledProject(Project project) {
        super(project.getName(), (Employee[]) project.toArray());
    }

    protected boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        isChanged = true;
    }

    @Override
    public boolean add(Employee employee) {
        if (super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        if (super.addAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        if (super.addAll(index, c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (super.removeAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (super.retainAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        isChanged = true;
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        if (super.remove(firstName, secondName)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public Employee set(int index, Employee element) {
        Employee returnValue = super.set(index, element);
        isChanged = true;
        return returnValue;
    }

    @Override
    public void add(int index, Employee element) {
        super.add(index, element);
        isChanged = true;
    }

    @Override
    public Employee remove(int index) {
        Employee returnValue = super.remove(index);
        isChanged = true;
        return returnValue;
    }

    @Override
    public void bonusForBusinessTravellers() {
        super.bonusForBusinessTravellers();
        isChanged = true;
    }
}
