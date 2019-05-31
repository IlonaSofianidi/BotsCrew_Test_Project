import common.DepartmentResponse;
import lombok.extern.log4j.Log4j;
import utils.MessageUtils;

import java.util.Scanner;

@Log4j
public class SimpleChatBot {
    private boolean botIsActive = false;

    public static void main(String[] args) {
        SimpleChatBot simpleChatBot = new SimpleChatBot();
        simpleChatBot.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        botIsActive = true;
        sayHello();
        while (botIsActive) {
            StringBuilder userRequest = new StringBuilder();

            String request =scanner.nextLine();
            if (request.contains("1")) {
                String departmentName = MessageUtils.extractDepartmentName(request);
                DepartmentResponse headOfDepartment = DepartmentResponse.getHeadOfDepartmentResponse(departmentName.trim());
                log.info(headOfDepartment.getSpeechText());
            }
            else if (request.contains("2")) {
                String departmentName = MessageUtils.extractDepartmentName(request);
                log.error(departmentName.trim());
                DepartmentResponse statistics = DepartmentResponse.getDepartmentStatictic(departmentName.trim());
                log.info(statistics.getSpeechText());
            }
           else if (request.contains("3")) {
                String departmentName = MessageUtils.extractDepartmentName(request);
                DepartmentResponse salary = DepartmentResponse.getDepartmentSalary(departmentName.trim());
                log.info(salary.getSpeechText());
            }
           else if (request.contains("4")) {
                String departmentName = MessageUtils.extractDepartmentName(request);
                DepartmentResponse employees = DepartmentResponse.getEmployeesCountForDepartment(departmentName.trim());
                log.info(employees.getSpeechText());
            }
            else if (request.equalsIgnoreCase("/help")) {
                DepartmentResponse helpResponse = DepartmentResponse.getHelpResponse();
                log.info(helpResponse.getSpeechText());
            }
           else if (request.equalsIgnoreCase("/bye")) {
                botIsActive = false;
                log.info("Bye! Have fun.");
            }else {
                log.info("I'm sorry.I can not get it. Write /help to see the list of available commands...");
            }
        }
    }

    private void sayHello() {
        DepartmentResponse welcomeResponse = DepartmentResponse.getWelcomeResponse();
        log.info(welcomeResponse.getSpeechText());
    }
}
