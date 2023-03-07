import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите полный путь до файла:");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        BufferedReader input = null;
        Map<Character, Integer> freq = new HashMap<>();
        try {
            FileReader file = new FileReader(path);
            input = new BufferedReader(file);
            int current = input.read();
            while (current != -1){
                char symbol = (char) current;
                if (((symbol>='A') && (symbol<='Z')) || ((symbol>='a')&&(symbol<='z'))){
                    if (!freq.containsKey(symbol)) {
                        freq.put(symbol, 1);
                    }
                    else {
                        int count = freq.get(symbol);
                        freq.put(symbol, count + 1);
                    }
                }
                current = input.read();
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("Файла не существуует!");
        }
        catch(IOException ex){
            System.out.println("Ошибка ввода-вывода!");
        }
        try {
            System.out.println("Введите имя файла для записи:");
            Scanner out = new Scanner(System.in);
            String pathwrite = out.nextLine();
            FileWriter f = new FileWriter(pathwrite);
            for (int i = 0; i < 52; i++){
                if (i%2==0){
                    if (freq.containsKey((char)('A'+ i/2))){
                        f.write((char)('A'+ i/2) + " - " + freq.get((char)('A'+ i/2)) + '\n');
                    }
                    else {
                        f.write((char)('A'+ i/2) + " - " + 0 + '\n');
                    }
                }
                else {
                    if (freq.containsKey((char)('a'+ i/2))) {
                        f.write((char) ('a' + i / 2) + " - " + freq.get((char) ('a' + i / 2)) + '\n');
                    }
                    else {
                        f.write((char)('a'+ i/2) + " - " + 0 + '\n');
                    }
                }

            }
            f.close();
        }
        catch(IOException ex) {
            System.out.println("Ошибка записи в файл!");
        }
    }
}