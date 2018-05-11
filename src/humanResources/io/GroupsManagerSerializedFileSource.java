package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource{

    @Override
    public void load(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".obj");

        try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(path))) {
            EmployeeGroup element = (EmployeeGroup) stream.readObject();
            stream.close();
            group.clear();
            group.setName(element.getName());
            group.addAll(Arrays.asList((Employee[]) element.toArray()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".obj");

        try (ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(path))){
            stream.writeObject(group);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".obj");

        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".obj");

        try {
            Files.createFile(path);
            store(group);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
