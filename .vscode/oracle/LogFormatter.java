import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFormatter {

    private static final Pattern LOG_START_PATTERN = Pattern.compile("^(\\d+)\\s+(INFO|ERROR|DEBUG|WARN)\\s+(.*)");

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder currentLog = new StringBuilder();
        String currentTimestamp = null;
        String currentLevel = null;

        while ((line = reader.readLine()) != null) {
            Matcher matcher = LOG_START_PATTERN.matcher(line);
            if (matcher.matches()) {
                // Print previous log
                if (currentTimestamp != null) {
                    System.out.printf("[%s] [%s] %s%n", currentTimestamp, currentLevel, currentLog.toString().trim());
                }

                // Start new log
                currentTimestamp = matcher.group(1);
                currentLevel = matcher.group(2);
                currentLog = new StringBuilder(matcher.group(3));
            } else {
                // Continuation line
                currentLog.append(" ").append(line.trim());
            }
        }

        // Print last log
        if (currentTimestamp != null) {
            System.out.printf("[%s] [%s] %s%n", currentTimestamp, currentLevel, currentLog.toString().trim());
        }
    }
}
