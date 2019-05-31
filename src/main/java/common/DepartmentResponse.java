package common;

import dao.daoImpl.DepartmentDaoImpl;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class DepartmentResponse {
    private String speechText;
    private static final String[] helpCommands = {"/help", "1.Who is head of department {department_name}",
            "2.Show {department_name} statistic", "3.Show the average salary for {department_name}",
            "4.Show count of employees for {department_name}", "/bye"};

    public DepartmentResponse(String speechText) {
        this.speechText = speechText;

    }

    public static DepartmentResponse getWelcomeResponse() {
        DepartmentResponse helpResponse = getHelpResponse();
        return new DepartmentResponse("Welcome to University Chatbot!" + helpResponse.getSpeechText());
    }

    public static DepartmentResponse getHelpResponse() {
        StringBuilder builder = new StringBuilder();
        builder.append("\r\n");
        builder.append("Below is the list of available commands").append("\r\n");
        for (String s : helpCommands) {
            builder.append(s).append("\r\n");
        }
        builder.append("Just write the command number and name of the department");
        return new DepartmentResponse(builder.toString());
    }

    public static DepartmentResponse getHeadOfDepartmentResponse(String departmentName) {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.openSessionTransaction();

        List<String> headOfTheDepartmentName = departmentDao.getHeadOfTheDepartmentName(departmentName);
        departmentDao.getCurrentSession().close();

        StringBuilder builder = new StringBuilder();
        if (!headOfTheDepartmentName.isEmpty()) {
            for (String name : headOfTheDepartmentName) {
                String msg = String.format("Head of the department %s is %s %n", departmentName, name);
                builder.append(msg);
            }
        } else {
            builder.append("Sorry i did'n get that");
        }
        return new DepartmentResponse(builder.toString());

    }

    public static DepartmentResponse getDepartmentStatictic(String departmentName) {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.openSessionTransaction();

        Optional<Long> assistants = departmentDao.getAssistanceGroupedByDepartmentName(departmentName);
        Optional<Long> associateProfessors = departmentDao.getAssociateProfessorsGroupedByDepartmentName(departmentName);
        Optional<Long> professors = departmentDao.getProfessorsGroupedByDepartmentName(departmentName);
        departmentDao.getCurrentSession().close();

        StringBuilder builder = new StringBuilder();
        builder.append("\r\n");

        if (!assistants.isPresent()) {
            builder.append("Assistants not found :( ").append("\r\n");
        } else {
            builder.append("Assistant: ").append(assistants.get()).append("\r\n");
        }

        if (!associateProfessors.isPresent()) {
            builder.append("Associate professors not found :( ").append("\r\n");
        } else {
            builder.append("Associate professors: ").append(associateProfessors.get()).append("\r\n");
        }

        if (!professors.isPresent()) {
            builder.append("Professors not found :( ").append("\r\n");
        } else {
            builder.append("Professors: ").append(professors.get()).append("\r\n");

        }
        return new DepartmentResponse(builder.toString());
    }

    public static DepartmentResponse getDepartmentSalary(String departmentName) {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.openSessionTransaction();
        Optional<Double> salary = departmentDao.getAverageSalaryForDepartmentName(departmentName);
        departmentDao.getCurrentSession().close();

        StringBuilder builder = new StringBuilder();
        if (!salary.isPresent()) {
            builder.append("Something went wrong. Cannot count the average salary of the department ")
                    .append(departmentName).append("\r\n" + "Please,check if the department name is correct");
        } else {
            String msg = String.format("The average salary of department %s is %.2f", departmentName, salary.get());
            builder.append(msg);
        }
        return new DepartmentResponse(builder.toString());
    }

    public static DepartmentResponse getEmployeesCountForDepartment(String departmentName) {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.openSessionTransaction();
        Optional<Long> employees = departmentDao.getCountOfEmployeesForDepartmentName(departmentName);
        departmentDao.getCurrentSession().close();
        StringBuilder builder = new StringBuilder();
        if (!employees.isPresent()) {
            builder.append("I didn`t get that. Can not find employees count for the department ").append(departmentName);
        } else {
            String msg = String.format("Employees count for the department %s is %d", departmentName, employees.get());
            builder.append(msg);
        }
        return new DepartmentResponse(builder.toString());

    }
}
