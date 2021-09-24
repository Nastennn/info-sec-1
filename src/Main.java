import java.util.*;

class Main {
    // Генерируем ключ из введенных слов. Ключевое слово должно повторятся до тех пор,
    // пока строка с ключом не сравняется со строкой исходного текста.

    static String generateKey(String str, String key) {
        int x = str.length();
        for (int i = 0; ; i++) {
            // Если длина ключа совпадает с длиной строки для шифрования, то заканчиваем генерацию ключа.
            if (key.length() == str.length())
                break;
            // Добавляем к ключу символ.
            key += (key.charAt(i));
        }
        return key;
    }

    //Кодируем текст. Используем таблицу символов ASCII.
    static String cipherText(String input, String key) {
        String cipher_text = "";
        for (int i = 0; i < input.length(); i++) {
            // Символ не должен быть пробелом, запятой или точкой.
            if (input.charAt(i) != ' ' && input.charAt(i) != ',' && input.charAt(i) != '.' && input.charAt(i) != '-') {


                // Вычисляем сдвиг символа.
                int x = (Alphabet.getAlphabetPosition(input.charAt(i)) + Alphabet.getAlphabetPosition(key.charAt(i))) % Alphabet.alphabet.length;
                // Добавляем полученный символ к кодируемой строке.
                cipher_text += Alphabet.getLetterOnPosition(x);
            } else {
                // Если символ является пробелом, запятой или точкой,
                // то добавляем этот символ к строке без кодировки.
                cipher_text += input.charAt(i);
            }

        }
        return cipher_text;
    }

    // Декодируем текст.
    static String originalText(String cipher_text, String key) {
        String orig_text = "";

        for (int i = 0; i < cipher_text.length() && i < key.length(); i++) {
            // Проверяем ялвяется ли символ пробелом, запятой или точкой.
            if (cipher_text.charAt(i) != ' ' && cipher_text.charAt(i) != ',' && cipher_text.charAt(i) != '.' && cipher_text.charAt(i) != '-') {
                // Получаем исходное значение символа.
                int x = (Alphabet.getAlphabetPosition(cipher_text.charAt(i)) - Alphabet.getAlphabetPosition(key.charAt(i)) + Alphabet.alphabet.length) % Alphabet.alphabet.length;
                // Добавляем символ к декодированной строке.
                orig_text += Alphabet.getLetterOnPosition(x);
            } else {
                // Если символ является пробелом, запятой или точкой, добавляем его к строке.
                orig_text += (cipher_text.charAt(i));
            }

        }
        return orig_text;
    }


    public static void main(String[] args) {
        // Читаем файл.
        String textToEncode = Reader.readText("src/test").toUpperCase();
        String cipher_text = textToEncode;
        System.out.println("Enter the key. Ctrl+D for ending the enter:");
        // Считываем ключи.
        Scanner scanner = new Scanner(System.in);
        // Определяем переменную для обозначения конца ввода.
        boolean enterIsOver = false;
        // Создаем ArrayList с ключами.
        ArrayList<String> keyArray = new ArrayList<>();
        // Пока ввод не окончен, добавляем ключ в ArrayList.
        while (!enterIsOver) {
            try {
                keyArray.add(scanner.nextLine().toUpperCase());
            } catch (NoSuchElementException e) {
                enterIsOver = true;
            }
        }
        // Шифруем текст с каждым ключом.
        for (String currentKey : keyArray) {
            // Генерируем ключ нужной длины.
            String key = generateKey(textToEncode, currentKey);
            // Шифруем текст.
            cipher_text = cipherText(cipher_text, key);

            System.out.println("Ciphertext : "
                    + cipher_text + "\n");

        }
        // Чтобы расшифровать текст, который был многократно зашифрован разными ключами,
        // будем расшифровывать его с ключами в обратном порядке.
        Collections.reverse(keyArray);

        for (String currentKey : keyArray) {
            // Генерируем ключ нужной длины.
            String key = generateKey(cipher_text, currentKey);
            // Расшифровываем текст.
            cipher_text = originalText(cipher_text, key);
            System.out.println("Original/Decrypted Text : "
                    + cipher_text);
        }

    }
}
