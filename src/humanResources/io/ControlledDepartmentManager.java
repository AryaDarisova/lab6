package humanResources.io;

import humanResources.Department;
import humanResources.DepartmentsManager;
import humanResources.EmployeeGroup;
import humanResources.exceptions.NegativeSizeException;
import humanResources.factory.FileGroupsFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ControlledDepartmentManager extends DepartmentsManager {
    protected Source<EmployeeGroup> source;
    private FileGroupsFactory factory;

    public ControlledDepartmentManager(String name) {
        super(name);
    }

    public ControlledDepartmentManager(String name, int size) throws NegativeSizeException {
        super(name, size);
    }

    public ControlledDepartmentManager(String name, Department[] departments) {
        super(name, departments);
    }

    protected Source<EmployeeGroup> getSource() {
        return source;
    }

    protected void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }

    @Override
    public boolean add(EmployeeGroup groupable) {
        EmployeeGroup element = factory.createDepartment((Department) groupable);
        source.create(element);
        return super.add(element);
    }

    @Override
    public boolean remove(Object o) {
        source.delete((EmployeeGroup) o);
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        ArrayList<EmployeeGroup> arrayList = new ArrayList<>(c.size());
        for (EmployeeGroup element : c) {
            source.create(element);
            arrayList.add(factory.createDepartment((Department) element));
        }
        return super.addAll(arrayList);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        ArrayList<EmployeeGroup> arrayList = new ArrayList<>(c.size());
        for (EmployeeGroup element : c) {
            source.create(element);
            arrayList.add(factory.createDepartment((Department) element));
        }
        return super.addAll(index, arrayList);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            source.delete((EmployeeGroup) o);
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (EmployeeGroup element : departments) {
            if (!c.contains(element)) {
                source.delete(element);
            }
        }
        return super.retainAll(c);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        EmployeeGroup add = factory.createDepartment((Department) element);
        source.create(add);
        super.add(index, add);
    }

    @Override
    public EmployeeGroup remove(int index) {
        source.delete(super.get(index));
        return super.remove(index);
    }

    @Override
    public boolean remove(String groupName) {
        source.delete(super.getEmployeeGroup(groupName));
        return super.remove(groupName);
    }

    private void store() {
        for (EmployeeGroup element: departments) {
            if (((ControlledDepartment) element).isChanged()) {
                source.store(element);
            }
        }
    }

    private void load() {
        for (EmployeeGroup element: departments)
            source.load(element);
    }
}
