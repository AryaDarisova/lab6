package humanResources.io;

import humanResources.*;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {
    @Override
    public void load(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".bin");

        try (DataInputStream stream = new DataInputStream(Files.newInputStream(path))) {
            group.setName(stream.readUTF());
            group.addAll(Arrays.asList(loadEmployees(stream)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".bin");

        try (DataOutputStream stream = new DataOutputStream(Files.newOutputStream(path))) {
            if (group instanceof Department)
                stream.writeUTF("Department");
            else stream.writeUTF("Project");

            stream.writeInt(group.size());

            for (Employee element : group) {
                if (element instanceof StaffEmployee) {
                    stream.writeUTF("StaffEmployee");
                    stream.writeUTF(element.getFirstName());
                    stream.writeUTF(element.getSecondName());
                    stream.writeUTF(element.getJobTitle().toString());
                    stream.writeInt(element.getSalary());
                    stream.writeInt(element.getBonus());
                    stream.writeInt(((StaffEmployee) element).getTravels().length);
                    for (BusinessTravel travel: ((StaffEmployee) element).getTravels()) {
                        stream.writeUTF(travel.getDestination());
                        stream.writeUTF(travel.getStartTrip().toString());
                        stream.writeUTF(travel.getEndTrip().toString());
                        stream.writeUTF(travel.getDescription());
                    }
                } else {
                    stream.writeUTF("PartTimeEmployee");
                    stream.writeUTF(element.getFirstName());
                    stream.writeUTF(element.getSecondName());
                    stream.writeUTF(element.getJobTitle().toString());
                    stream.writeInt(element.getSalary());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".bin");

        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean create(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".bin");

        try {
            Files.createFile(path);
            store(group);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private Employee[] loadEmployees(DataInputStream stream) {
        try {
            int count = stream.readInt();
            Employee[] element = new Employee[count];

            for (int i = 0; i < count; i++) {
                if (stream.readUTF().equals("StaffEmployee")) {
                    String firstName = stream.readUTF();
                    String secondName = stream.readUTF();
                    JobTitlesEnum jobTitle = JobTitlesEnum.valueOf(stream.readUTF());
                    int salary = stream.readInt();

                    element[i] = new StaffEmployee(firstName, secondName, jobTitle, salary);
                } else {
                    String firstName = stream.readUTF();
                    String secondName = stream.readUTF();
                    JobTitlesEnum jobTitle = JobTitlesEnum.valueOf(stream.readUTF());
                    int salary = stream.readInt();

                    element[i] = new PartTimeEmployee(firstName, secondName, jobTitle, salary);
                }
            }
            return element;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
