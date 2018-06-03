package Main;
import java.util.Scanner;
import java.io.*;

public class BibCreator {

        public static void main (String[]args) {

            final int nbOfFiles = 10;
            Scanner sc = new Scanner(System.in);
            Scanner[] input = new Scanner[10];
            System.out.println("Welcome to BibCreator!\n");

            //Task 3
            //Reading all Latex.bib files making sure that they are all found
            for (int i = 0; i < nbOfFiles; i++) {
                try {
                    input[i] = new Scanner(new FileInputStream("Latex" + (i + 1) + ".bib"));
                } catch (FileNotFoundException e) {
                    System.out.println("Could not open input file Latex" + i + ".bib for reading. Please check if file" +
                            "exists! Program will terminate after closing any opened files.");
                    System.exit(0);
                }
                input[i].close();
            }
            //Creating 30 files
            PrintWriter[] pwIEEE = new PrintWriter[nbOfFiles];
            PrintWriter[] pwACM = new PrintWriter[nbOfFiles];
            PrintWriter[] pwNJ = new PrintWriter[nbOfFiles];

            //Task 3
            for (int i = 0; i < nbOfFiles; i++) {
                try {
                    pwIEEE[i] = new PrintWriter(new FileOutputStream("IEEE" + (i + 1) + ".json", true));
                    pwACM[i] = new PrintWriter(new FileOutputStream("ACM" + (i + 1) + ".json", true));
                    pwNJ[i] = new PrintWriter(new FileOutputStream("NJ" + (i + 1) + ".json", true));
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    pwACM[i].close();
                    pwIEEE[i].close();
                    pwNJ[i].close();
                    System.exit(0);
                }
                pwACM[i].close();
                pwIEEE[i].close();
                pwNJ[i].close();
            }
            //Task 5
            //Read every .bib file and find any invalid files. If any files
            //are invalid, they will be deleted.
            fileVerification verify = new fileVerification();
            for (int i = 1; i <= nbOfFiles; i++){
                verify.processFilesForValidation(i);
            }
            System.out.println("A total of " + verify.getInvalidFiles() + " files were invalid, and could not be processed. All" +
                    " other " + (nbOfFiles-verify.getInvalidFiles()) + " \"Valid\" files have been created.");

            //Task 7
            for(int i = 0; i < 2; i++){
                try{
                    System.out.print("\nPlease enter the name of one of the files you need to review:");
                    String readFile = sc.nextLine();
                    File f = new File(readFile);
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line = null;

                    //In order to read a line from a file, we must make
                    //sure that the file has not ended.
                    while((line=br.readLine())!=null){
                        System.out.println(line);
                    }
                    br.close();
                    fr.close();
                    break; //breaks out of for loop.

                } catch(FileNotFoundException e){
                    System.out.println("Could not open input file. File does not exist; possibly it could not be created!\n");
                    //First chance to print a file.
                    if (i == 0) {
                        System.out.println("However, you will be given another chance to enter another file name.");
                    }
                    //Second chance, exit if file not found.
                    if (i == 1){
                        System.out.println("Sorry! I am unable to display your desired files! Program will exit now.");
                        sc.close();
                        System.exit(0);
                    }
                } catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
