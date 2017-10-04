Author: Brian Piatigorski (bap58)
COSC 150 with Professor Barrett Koster
Project 2: Connect Four
Worked with Nico Cuevas

ConnectFour is a package for a game of Connect Four in java.
Two players take turns placing tokens on the board with the object
of placing four in a row horizontally, diagonally, or vertically.
Tokens are colored red for the first player to place a piece and black
for the second. Each player, on his or her turn, must place a piece
in a column by clicking on the lowest available space in their selected
column. Clicking outside the board or on a space with available spaces
beneath it will not result in any pieces being played.

The game has 3 buttons and 2 text fields on the right side of the window.
The reset button will clear the board and start a new game.
The save button will save the current game as-is, writing to a file called
saveFileC4 included in the package.
The load button will load the game from the save file.
The text fields, one in red and one in black, are for players to write their
names in (the black is actually light gray for visibility).

The code was modified from a TicTacToe program written in COSC 150 with
Professor Koster, and uses a modified game, board, player, square on the board,
and scoring system as was developed in class.