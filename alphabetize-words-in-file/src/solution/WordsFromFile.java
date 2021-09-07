package solution;

import resources.classes.TextIO;

import java.util.ArrayList;

public class WordsFromFile {

    public static void main(String[]Args){
        TextIO.readFile("src\\resources\\files\\words_from_file_helper_text");
        String currentWord;
        ArrayList<String> wordList = new ArrayList<>();

        currentWord = readNextWord();
        while(currentWord!=null){
            currentWord = currentWord.toLowerCase();

            if(wordList.size()==0){
                wordList.add(currentWord);
            }else{
                for(int i = 0; i<wordList.size();i++){
                    if(currentWord.compareTo(wordList.get(i))==0){
                        break;
                    }else if (currentWord.compareTo(wordList.get(i))<0){
                        wordList.add(currentWord);
                        break;
                    }else if (currentWord.compareTo(wordList.get(wordList.size()-1))>0){
                        wordList.add(currentWord);
                        break;
                    }else if(currentWord.compareTo(wordList.get(i))>0 && currentWord.compareTo(wordList.get(i+1))<0){
                        wordList.add(i+1, currentWord);
                        break;
                    }
                }
            }
            currentWord = readNextWord();
        }

        for(String word : wordList){
            System.out.println(word);
        }
    }


    //the following method was provided in the textbook
    /**
     * Read the next word from TextIO, if there is one. First, skip past
     * any non-letters in the input. If an end-of-file is encountered before
     * a word is found, return null. Otherwise, read and return the word.
     * A word is defined as a sequence of letters. Also, a word can include
     * an apostrophe if the apostrophe is surrounded by letters on each side.
     * @return the next word from TextIO, or null if an end-of-file is
     * encountered
     */
    private static String readNextWord() {
        char ch = TextIO.peek(); // Look at next character in input.
        while (ch != TextIO.EOF && ! Character.isLetter(ch)) {
// Skip past non-letters.
            TextIO.getAnyChar(); // Read the character.
            ch = TextIO.peek(); // Look at the next character.
        }
        if (ch == TextIO.EOF) // Encountered end-of-file
            return null;
// At this point, we know the next character is a letter, so read a word.
        String word = ""; // This will be the word that is read.
        while (true) {
            word += TextIO.getAnyChar(); // Append the letter onto word.
            ch = TextIO.peek(); // Look at next character.
            if ( ch == '\'' ) {
// The next character is an apostrophe. Read it, and
// if the following character is a letter, add both the
// apostrophe and the letter onto the word and continue
// reading the word. If the character after the apostrophe
// is not a letter, the word is done, so break out of the loop.
                TextIO.getAnyChar(); // Read the apostrophe.
                ch = TextIO.peek(); // Look at char that follows apostrophe.
                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek(); // Look at next char.
                }
                else
                    break;
            }
            if ( ! Character.isLetter(ch) ) {
// If the next character is not a letter, the word is
// finished, so break out of the loop.
                break;
            }
// If we haven’t broken out of the loop, next char is a letter.
        }
        return word; // Return the word that has been read.
    }
}
