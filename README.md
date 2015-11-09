# Takuzu Solver

##Takuzu Puzzles

Inspired by /r/dailyprogrammer challenge #237. (https://www.reddit.com/r/dailyprogrammer/comments/3pwf17/20151023_challenge_237_hard_takuzu_solver/)

Takuzu puzzles are like binary sudoku puzzles. Instead of the numbers 1 through 9, every square in the puzzle is either red or blue. The rules for the puzzle are as follows:
 - Three tiles of the same colour can't be next to each other in the row or column.
 - A row or column must have an equal number of coloured tiles.
 - No two rows or columns are the same.

You can play a version of the game here: http://0hh1.com/

## My Solver

My program takes in a file containing the puzzle, with the numbers as either ones or twos, and . standing in for empty squares. This is a little different from the version posted in the Daily Programmer challenge. 

Essentially the program just brute-forces the solution. It checks 4 conditions over and over until the puzzle is solved, or it reaches 100 iterations. The conditions it checks are:
 1. Check for two in a row, then fill in the third with the opposite value.
 2. Check for holes that can only be filled by one value (meaning putting in the other value would result in three in a row).
 3. Check for rows and columns that have board size/2 squares of one colour, then fills in the remaining squares with the opposite colour.
 4. Check for complete rows/columns, and then finds rows/columns that are incomplete with 2 missing numbers, the rest matching the completed row/column. Those are then filled in with the opposite numbers from the complete row/column to avoid having a match.
