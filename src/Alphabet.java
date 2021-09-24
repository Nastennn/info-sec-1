import java.util.Arrays;

// Класс для работы с алфавитом.
public class Alphabet {

        // Алфавит задаем в массив.
        public static char[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();

        // Получаем позицию буквы в алфавите.
        public static int getAlphabetPosition(char letter) {
            // Проходим по буквам алфавита.
            for (int i = 0; i < alphabet.length; i++) {
                // Если была передана эта буква, возвращаем её номер.
                if (String.valueOf(letter).equalsIgnoreCase(String.valueOf(alphabet[i]))) {
                    return i;
                }
            }
            // Если буква не была найдена, выбрасываем исключение.
            throw new UnknownLetter(
                    String.format("Alphabet %s doesn't contain input letter %c.", Arrays.toString(alphabet), letter));
        }

        // Получаем букву на данной позиции.
        public static char getLetterOnPosition(int position) {
            return alphabet[position];
        }

}
