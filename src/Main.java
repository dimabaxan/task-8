
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        try {
            validateInput(data);
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            validateBirthDate(birthDate);
            validatePhoneNumber(phoneNumber);
            validateGender(gender);

            writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            System.out.println("Данные успешно записаны.");

        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateInput(String[] data) throws IllegalArgumentException {
        if (data.length < 6) {
            throw new IllegalArgumentException("Введено меньше данных, чем требуется.");
        } else if (data.length > 6) {
            throw new IllegalArgumentException("Введено больше данных, чем требуется.");
        }
    }

    private static void validateBirthDate(String birthDate) throws IllegalArgumentException {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается формат dd.mm.yyyy");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен быть целым беззнаковым числом без форматирования.");
        }
    }

    private static void validateGender(String gender) throws IllegalArgumentException {
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Пол должен быть символом латиницей 'f' или 'm'.");
        }
    }

    private static void writeToFile(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws IOException {
        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(String.format("%s %s %s %s %s %s", lastName, firstName, middleName, birthDate, phoneNumber, gender));
            writer.newLine();
        }
    }
}