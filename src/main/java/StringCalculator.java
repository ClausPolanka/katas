import java.rmi.RemoteException;

public class StringCalculator {
    private static final char COMMA = ',';
    private static final char SEMICOLON = ';';
    private static final int DELIMITER_POSITION = 2;
    private static final char NEW_LINE = '\n';
    private String negatives = "";

    public int add(String numbers) {
        int result = evaluate(fixDelimiter(numbers));
        reportErrors(numbers);
        return result;
    }

    private String fixDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            char customDelimiter = numbers.charAt(DELIMITER_POSITION);
            numbers = numbers.replace(customDelimiter, COMMA).substring(DELIMITER_POSITION);
        }
        numbers = numbers.replace(NEW_LINE, COMMA);
        return numbers;
    }

    private int evaluate(String numbers) {
        int commaPos = numbers.indexOf(COMMA);
        if (commaPos < 0) {
            return toInt(numbers);
        }
        return toInt(numbers.substring(0, commaPos)) + evaluate(numbers.substring(commaPos + 1));
    }

    private int toInt(String numbers) {
        int result = numbers.isEmpty() ? 0 : Integer.parseInt(numbers);
        if (result < 0)
            negatives += " " + numbers;
        return result;
    }

    private void reportErrors(String numbers) {
        if (negatives.length() > 0) {
            throw new RuntimeException("Negatives not allowed:" + negatives);
        }
    }
}
