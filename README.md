# alphabetize-words-in-file

My solution for Chapter 7 Exercise 6 of “Introduction to Programming Using Java”.

Problem Description:
Write a program that will read a text file selected by the user, and will make an alphabetical
list of all the different words in that file. All words should be converted to lower case, and
duplicates should be eliminated from the list. The list should be written to an output file
selected by the user. As discussed in Subsection 2.4.4, you can use TextIO to read and
write files. Use a variable of type ArrayList<String> to store the words. It is not easy to
separate a file into words as you are reading it, especially if you want to allow apostrophes
in the middle of a word. You can use the following method in your program:
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
if ( ch == ’\’’ ) {
// The next character is an apostrophe. Read it, and
// if the following character is a letter, add both the
// apostrophe and the letter onto the word and continue
// reading the word. If the character after the apostrophe
// is not a letter, the word is done, so break out of the loop.
TextIO.getAnyChar(); // Read the apostrophe.
ch = TextIO.peek(); // Look at char that follows apostrophe.
if (Character.isLetter(ch)) {
word += "\’" + TextIO.getAnyChar();
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
Note that this method will return null when the file has been entirely read. You can use
this as a signal to stop processing the input file.


Notes: TextIO is an IO helper class created by the textbook's author to assist with IO before the book covers java's IO utilities.
