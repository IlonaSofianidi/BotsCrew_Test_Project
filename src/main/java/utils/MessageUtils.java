package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
    public static String extractDepartmentName(String lineToParse) {
        String message = null;
        if (lineToParse.matches(".*\\d+.*")) {
            Pattern p = Pattern.compile("([0-9]+)(.*)");
            Matcher m = p.matcher(lineToParse);
            while (m.find()) {
                message = (m.group(2));
            }
        }
        return message;
    }
}
