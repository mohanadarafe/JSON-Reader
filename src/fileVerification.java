package Main;
import java.io.*;

public class fileVerification {

    public static int invalidFiles=0;

    public static int getInvalidFiles() {
        return invalidFiles;
    }

    public static void processFilesForValidation(int latexNumber) {

        //Initialise all our variables
        int ACMNumber = 1;
        String problem = null;
        String line;
        String author = null;
        String journal = null;
        String title = null;
        String year = null;
        String volume = null;
        String number = null;
        String pages = null;
        String keywords = null;
        String doi = null;
        String ISSN = null;
        String month = null;

        try {

            //Print Writers for each format and deleting invalid files.
            PrintWriter pwIEEE = new PrintWriter(new FileOutputStream("IEEE" + latexNumber + ".json"));
            PrintWriter pwACM = new PrintWriter(new FileOutputStream("ACM" + latexNumber + ".json"));
            PrintWriter pwNJ = new PrintWriter(new FileOutputStream("NJ" + latexNumber + ".json"));
            BufferedReader inFile = new BufferedReader(new FileReader("Latex" + latexNumber + ".bib"));
            File ACMFile = new File("ACM" + latexNumber + ".json");
            File IEEEFile = new File("IEEE" + latexNumber + ".json");
            File NJFile = new File("NJ" + latexNumber + ".json");

            /*
             * In this part, we are testing every single line to see
             * if there are any invalid fields. As soon as an invalid
             * field is found at a line x, we throw an FileInvalidException
             * and move on to the next Latex files. Otherwise, we store
             * all information needed in new files created in 3 formats.
             */
            while ((line = inFile.readLine()) != null) {

                if (line.equals("author={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "author";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("journal={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "journal";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("title={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "title";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("year={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "year";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("volume={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "volume";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("number={}, ")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "number";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("pages={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "pages";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("keywords={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "keywords";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("doi={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "doi";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("ISSN={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "ISSN";
                    invalidFiles++;
                    throw new FileInvalidException();
                }else if (line.equals("month={},")){
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "month";
                    invalidFiles++;
                    throw new FileInvalidException();
                    //If code arrives here with no exceptions thrown, article is valid!
                } else if(line.substring(0, line.indexOf('{')+1).equals("author={")){
                    author = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("title={")){
                    title = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("journal={")){
                    journal = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("year={")){
                    year = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("volume={")){
                    volume = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("number={")){
                    number = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("pages={")){
                    pages = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("keywords={")){
                    keywords = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("doi={")){
                    doi = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("ISSN={")){
                    ISSN = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                } else if(line.substring(0, line.indexOf('{')+1).equals("month={")){
                    month = line.substring(line.indexOf('{')+1, line.indexOf('}'));
                }
                if(line.equals("}")){
                    //IEEE Format
                    pwIEEE.print(author.replaceAll(" and ", ", "));
                    pwIEEE.print(". \"" + title + "\", ");
                    pwIEEE.print(journal + ", ");
                    pwIEEE.print("vol. " + volume + ", ");
                    pwIEEE.print("no. " + number + ", ");
                    pwIEEE.print("p. " + pages + ", ");
                    pwIEEE.print(month + " " + year + ".");
                    pwIEEE.println();
                    //ACM Format
                    if(author.contains("and")) {
                        author = author.substring(author.indexOf('{') + 1, author.indexOf("and") - 1);
                    } else{
                        author = author.substring(0, author.length()-1);
                    }
                    if(ACMNumber<10) {
                        pwACM.print("[" + ACMNumber + "]    " + author + " et al. ");
                    } else
                        pwACM.print("[" + ACMNumber + "]   " + author + " et al. ");
                    pwACM.print(year + ". ");
                    pwACM.print(title + ". ");
                    pwACM.print(journal + ". ");
                    pwACM.print(volume + ", " + number + " (" + year + "), " + pages + ". " + "DOI:https://doi.org/" + doi);
                    pwACM.println();
                    //NJ Format
                    pwNJ.print(author.replaceAll("and", "&") + ". ");
                    pwNJ.print(title + ". ");
                    pwNJ.print(journal+ ". ");
                    pwNJ.print(volume + ", " + pages + "(" + year + ").");
                    pwNJ.println();
                    //Setting every value back to null for new article.
                    author = null;
                    journal = null;
                    title = null;
                    year = null;
                    volume = null;
                    number = null;
                    pages = null;
                    keywords = null;
                    doi = null;
                    ISSN = null;
                    month = null;
                    ACMNumber++;
                }
            }
        pwNJ.close(); pwIEEE.close(); pwACM.close(); inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Latex" + latexNumber + ".bib not found! Program shall terminate now.");
            System.exit(0);
        } catch (FileInvalidException e) {
            System.out.println("Error: Detected Empty Filed!");
            System.out.println("============================");
            System.out.println("============================");
            System.out.println("\nProblem detected with file Latex." + latexNumber + "bib");
            System.out.println("File is Invalid: Field \"" + problem + "\" is Empty. Processing has stopped at this point. Other empty fields may be present as well!\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
