package io_exercise.charactercounter;

import java.io.FileReader;
import java.io.IOException;

public class CharacterCounterService {
    public static int countCharacter(String filename, char target) {
        int count = 0;
        try (FileReader reader = new FileReader(filename)) {
            int c;
            while ((c = reader.read()) != -1) {
                if (c == target) count++;
            }
        }
        catch(IOException io){
            io.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(countCharacter("sample_file.txt", 'i'));
    }
}
