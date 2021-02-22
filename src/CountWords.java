// 13706836
// richard@psychicplumbing.com

/*
Count words (CountWords.java). Write a program that reads all words in a file (specified as the first argument) and
prints out, for each word, the line numbers on which the word occurred. This output should also be written to a second
file (specified as the second argument). If the number of provided arguments is not 2 or the specified arguments can’t
be opened as files, your program should report this, and not print anything else out.

By the arguments, we refer to the strings in the array String[] args that is passed to the main method.

Some guidelines:

You should use a FileReader wrapped in a BufferedReader to read from the first file.

You should use a FileWriter to write to the second file.

Number the lines of the file starting from 1.

For our purposes here, a word is a consecutive sequence of letters that appear in a line (of a file), such that in the
line where the sequence appears, there is no letter before the sequence, and no letter after the sequence. A letter is
one of the characters a, b, c, …, z, or one of the characters A, B, C, …, Z.

Words should be considered in a case-insensitive way: two words that differ only in terms of capitalization should be
considered the same word.

You should use an instance of HashMap<String,Set<Integer>> to keep track of each word that has been encountered, along
with the line numbers where the word has appeared.

Place all of your code in a file called CountWords.java.

Your program should print out the words in sorted order and in lowercase. Please follow the example.

Example input (placed into a file and specified as the first argument):

  Joey ate an apple; then,
  Joey ate two apples.
  Apples were eaten by Joey---in total, 3.
  Hooray for the apple-eater!!!
Example output (to be printed to the screen and written to the file specified as the second argument):

  an: line(s) 1
  apple: line(s) 1 4
  apples: line(s) 2 3
  ate: line(s) 1 2
  by: line(s) 3
  eaten: line(s) 3
  eater: line(s) 4
  for: line(s) 4
  hooray: line(s) 4
  in: line(s) 3
  joey: line(s) 1 2 3
  the: line(s) 4
  then: line(s) 1
  total: line(s) 3
  two: line(s) 2
  were: line(s) 3
 */

import java.io.*;
import java.util.*;

public class CountWords {
    // This log holds all the words in the input text and the paragraphs they appear in
    static HashMap<String, LinkedHashSet<Integer>> log = new HashMap<String, LinkedHashSet<Integer>>();

    public static void addToLog( String word, int line ) {
        // Check to see if the word if not yet in the log
        if ( log.get( word ) == null ) {
            // We use a linked hash set as it maintains the order that elements were added
            LinkedHashSet<Integer> lineSet = new LinkedHashSet<Integer>();
            lineSet.add( line );
            log.put( word, lineSet );
        } else {
            // If the word is in the log already, add the line number to the existing set
            log.get( word ).add( line );
        }
    }

    public static void main( String[] args ) {

        // Need to check that args has only two arguments and that they work
        // Throw an IO error?
        String inputFile = args[0];
        String outputFile = args[1];

        String s;
        try ( BufferedReader br = new BufferedReader( new FileReader( inputFile ))) {
            int line = 1;
            // Read the input text line by line
            while (( s = br.readLine()) != null ) {
                String word = "";
                s = s.toLowerCase(Locale.ROOT);
                // Loop through the line of input text making words
                for (char c : s.toCharArray()) {
                    if ( Character.compare( c, 'a') >= 0 && Character.compare( c, 'z') <= 0) {
                        word += c;
                    } else if ( word != "" ){
                        // When we reach the end of the word we add it to the log
                        addToLog( word, line );
                        word = "";
                    }
                }
//                System.out.println( "" + line + ": " + s );
                line++;
            }
        }
        catch ( IOException exc ) {
            System.out.println( "I/O Error: " + exc );
        }

//        System.out.println( log.toString() );

        // Need to read from the log in order
        // Create an ordered list of keys
        ArrayList<String> orderedLogKeys = new ArrayList<String>();
        for ( String key : log.keySet() ) {
            orderedLogKeys.add( key );
        }
        Collections.sort(orderedLogKeys);
//        System.out.println( orderedLogKeys );

        // Open a new output file
        try ( FileWriter fw = new FileWriter( outputFile )) {
            // Loop over the ordered log keys
            for (String key : orderedLogKeys) {
                // Create each line of output text
                String outputLine = "";
                outputLine += key + ": line(s)";
                for ( int Line : log.get(key) ) {
                    outputLine += " ";
                    outputLine += Line;
                }
                // Send each line of output text to console and to the output file
                System.out.println(outputLine);
                fw.write(outputLine + "\n");
            }
        }
        catch ( IOException exc )
        {
            System.out.println( "I/O error: " + exc );
        }
    }
}
