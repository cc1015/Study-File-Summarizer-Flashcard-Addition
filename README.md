This program is identical to the Study File Summarizer program with an added flashcard feature. The Study File Summarizer works as follows:

This program traverses a given root directory and reads the .md files in the directory. It extracts only the headers and double-bracketed phrases from the .md files and writes the extracted text into a single destination file. The extracted information can be sorted by file creation time, modification time, or alphabetically by file name on the destination file.

The program takes in three command-line arguments:

1. A relative or absolute path to a folder (directory) of markdown files containing the notes you want to summarize. The directory may contain both markdown files as well as other folders. And these other folders might contain more markdown files and/or other folders.
2. Ordering Flag - A flag to indicate how the summary document should be organized
  i. filename - organize the content in the output summary file in order based on the alphabetically sorted source file names.
  ii. created - organize the content in the output summary file in order based on the create-date time stamp of the source file.
  iii. modified - organize the content in the output summary file in order based on the the last modified time stamp of the source file.
3. An output path (relative or absolute) and filename of where to write the study guide your program generates. Based on the input file processing order dictated by command-line argument #2 above, the output file will contain:
  i. all headings in the order they appear in the file (properly nested).
    a. Except for the very first line of the study guide file, all headings should be preceded with a blank line.
  ii. all important phrases identified with the [[]] properly nested under the heading in which it appears in the original input file.
    a. In the output file, do not output the brackets themselves.
    b. Each bracketed phrase should be output as a single bullet point (-) in the output file.
    c. Bracketed phrases or sentences may span multiple lines of the input file.
    d. A single line of the input file may contain multiple bracketed important phrases; each should be output as a separate bullet point in the study guide.

TO CREATE THE FLASHCARDS:

The additional feature of this project is a flashcard feature. In the .md files, there can now be question and answer blocks: 

Example: `[[What is the chemical symbol for hydrogen?:::The symbol is H.]]`.
  - The question comes before the `:::` and the answer comes after.  There may or may not be white space characters on either side of the `:::`.  However, there will be no whitespace between the three colons.
  - Important blocks can have questions and answers.
  - The Q&A blocks *will not* become part of the study guide outline.
- As the study guide is generated, the question and answer blocks are saved into a separate file that has the same name as the study guide but with extension `.sr` instead of `.md`.
  - It is a text-based file.
  - It is stored in the same location as the study guide .md file.
 
TO USE THE FLASHCARD FEATURE:

1. The user inputs a `.sr` file path that contains a set of questions and their associated metadata.
2. The user indicates how many questions they would like to be presented in this study session. If the total number of questions in the question bank is smaller than the total number of questions the user wants to practice, they will be quizzed on all the questions in the file. 
3. The program selects a set of questions from the question bank file for this study session.  The program will first show all questions labeled as **hard** in random order.  Then, questions currently labeled as easy will be shown in random order. In other words, the hard questions will randomly be shown first followed by a random selection of easy questions.  
4. Once the questions are selected, each question is shown to the user.  The user will be able to mark each question as hard or easy, meaning that it was hard to think of the answer (or the user couldn’t actually think of it) or thinking of the answer was easy.  The model should be updated to reflect choices made by the user. 
5. At the end of the study session, the app should show the user some stats including:
    1. the total number of questions answered for that session, 
    2. the total number of questions that changed from easy to hard, 
    3. the total number of questions that changed from hard to easy, 
    4. the updated total number of hard questions in the question bank, and
    5. the updated total number of easy questions in the question bank.
