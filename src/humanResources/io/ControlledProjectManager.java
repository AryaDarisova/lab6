package humanResources.io;

import humanResources.EmployeeGroup;
import humanResources.Project;
import humanResources.ProjectManager;
import humanResources.exceptions.AlreadyAddedException;
import humanResources.factory.FileGroupsFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ControlledProjectManager extends ProjectManager {
    protected Source<EmployeeGroup> source;
    private FileGroupsFactory factory;

    public ControlledProjectManager() {
        super();
    }

    public ControlledProjectManager(EmployeeGroup[] group) throws AlreadyAddedException {
        super(group);
    }

    protected Source<EmployeeGroup> getSource() {
        return source;
    }

    protected void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }

    @Override
    public boolean add(EmployeeGroup groupable) {
        if (super.add(factory.createProject((Project) groupable))) {
            source.create(groupable);
            return true;
        }
        return false;
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
            arrayList.add(factory.createProject((Project) element));
        }

        return super.addAll(arrayList);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        ArrayList<EmployeeGroup> arrayList = new ArrayList<>(c.size());
        for (EmployeeGroup element : c) {
            source.create(element);
            arrayList.add(factory.createProject((Project) element));
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
        for (EmployeeGroup element: this) {
            if (!c.contains(element)) {
                source.delete(element);
            }
        }
        return super.retainAll(c);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        EmployeeGroup add = factory.createProject((Project) element);
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
        for (EmployeeGroup element: this) {
            if (((ControlledProject) element).isChanged()) {
                source.store(element);
            }
        }
    }

    private void load() {
        for (EmployeeGroup element: this)
            source.load(element);
    }
}
