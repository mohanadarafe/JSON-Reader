# JSON-Reader
JSON is a useful tool to read data in large quantity. In the program, the JSON reader reads thought multiple files and outputs files in a desired format. 

## Reading the files
The JSON reader reads 10 `Latex.bib` files that contain information on articles such as ISSN, Author & more. The objective is to read the files and determine if they are valid files. **A file is considered valid if they have no empty areas.** Furthermore, if an unvalid file is found, it should be deleted.

![TeX File](https://media.giphy.com/media/1zJYSzs2ikE3K5nqy4/giphy.gif)

## Output files
After reading through the 10 files, there should be 3 new files created **for each valid file**. In the purpose of this assignment, there were 3 unvalid files. The format of the three files created will be
- IEEE
- NJ
- ACM

In those files, the JSON reader will take all the information needed to create a file in each format to propoerly print out the correct formats.

![Output](https://media.giphy.com/media/g4M2N5rmoLpiif5zPv/giphy.gif)
