package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.JobTitlesEnum;
import humanResources.StaffEmployee;

import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource {
    @Override
    public void load(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".txt");

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            group.clear();

            group.setName(bufferedReader.readLine());

            int count = Integer.parseInt(bufferedReader.readLine());
            group.addAll(Arrays.asList(loadEmployees(count, bufferedReader)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".txt");

        try {
            PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path));
            printWriter.println(group);
            printWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".txt");

        try {
            Files.delete(path);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(EmployeeGroup group) {
        Path path = Paths.get(getPath() + group.getName() + ".txt");

        try {
            Files.createFile(path);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Employee[] loadEmployees(int count, BufferedReader bufferedReader) {
        try {
            Employee[] loadEmployees = new Employee[count];
            String[] str;

            for (int i = 0; i < count; i++) {
                str = bufferedReader.readLine().split(" ");

                loadEmployees[i] = new StaffEmployee(str[1], str[2], JobTitlesEnum.valueOf(str[3]), Integer.parseInt(str[4]));
            }
            return loadEmployees;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
