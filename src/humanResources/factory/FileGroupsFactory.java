package humanResources.factory;

import humanResources.EmployeeGroup;
import humanResources.io.GroupsManagerFileSource;
import humanResources.io.Source;

public abstract class FileGroupsFactory extends EmployeeFactory{
    protected GroupsManagerFileSource source;
    protected String path;


    public FileGroupsFactory(GroupsManagerFileSource source, String path) {
        this.source = source;
        this.path = path;
    }

    public Source<EmployeeGroup> getSource() {
        return source;
    }

    abstract void changePath(String newPath);
}
