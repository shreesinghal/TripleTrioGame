# Triple Triad OOD - Nicole Ni & Carter Herman

## Overview:

This implementation of the game Triple Triad for Fall 2024 of OOD. 
This is an implementation of the game, which will eventually be playable 
through a GUI. The entire project is made using Java and Spring. This project
is designed using a MVC architecture.

Triple Triad is a card based board game where the players battle to
control the board.

This model can be extended via extension of the model interface. Any
new rule sets can implement the BattleRule interface and can be defined.

This package requires at least Java 11 to run and JUnit 4 to test.

## Quick start: 

A game can be initiated by running the main method in the ThreeTrios.java file 

or 

A game can be initiated by running the jar file with `java -jar cs3500.jar`

For this initiation, two arguments must be provided. They can be `human`, 
`strategy1`, or `strategy2`. Two of these must be passed when running the jar file. 
Any arguments aside from the three stated will result in an IllegalArgumentException.


## Key Components:

- [Model](#model) 
- [Controller](#controller) (In Progress)
- [View](#view)
- [Strategy](#strategy)

### Model:

The Model is designed to be extended into possible future classes. It's rooted
in a Triple Triad Interface which is then followed by an abstract class that
contains the methods we find necessary to contain a model for this game. The model
drives the key subcomponents listed below:

- [Board](#board)
- [Card](#card)
- [Hand](#hand)
- [Rules](#battlerules)

The Model dictates how the player uses the cards in their hand to interact with the game.
The players are kept track off in a Player Enum which maps to their respective Hands. 
This allows the model to keep track of the current player as an enum and disallow playing
outside of ones turn. 

The Model will tell the Board which cards are being played from which hand, then
the control is passed onto the Board where the state of each Cell is updated. 

### Controller:

The controller is designed as a listener for the model, view, and the players.
It acts as the middle man for all components. Through usage of the command callback pattern,
the controller acts asynchronously when it receives notifications from components A new
component for the controller is the players:

- [Player](#)

The controller acts up the events it recieves and tells other components information
that it receives. Through these calls the game is played. The game is playable
with two screens. 

### View:

#### GUI:

The Gui is designed with Java Swing. The view retrieves data from the model through
a read only model variation, that was added in homework 6. The architecture contains a frame object which is 
the top level graphical object, then the panels and sub panels that make up the parts of
the view. Each component from the model has its own panel including both player hand
panels and a singular board panel:

- [Board Panel](#board-panel)
- [Hand Panel](#hand-panel)
- [Graphics Card](#graphics-card)

The view interact with the controller through a view features interface. The controller
will implement this view features class and be used as the communicator between view actions
and model actions.

#### Text View:
The text view for a game of Triple Triad displays the current player's turn, 
the board, and the hand with names of cards and attack values.
The board shows all cells with either the owner of the cell, 
an empty space for a cell with no card, or a hole for cells that can hold no cards.
The attack values are listed in the following order: North, South, East, West.

The following is an example of a textual view:\
The board has holes in the corners and no cards in playable cells.
<pre>
Player: RED
_   _
     
_   _
Hand: 
CardOne 1 4 1 3
CardTwo 2 2 4 2
CardThree 3 6 3 3
CardFour 4 4 5 4
CardFive 5 3 5 5
CardSix 6 4 6 6
</pre>

### Strategy

A strategy represents an AI player that chooses a move to play
based on the state of the board. 

- One strategy finds the move that
will flip the most amount of cards possible. This strategy checks
which card in which position will increase the score the most.
- One strategy finds the open corners of the board and which card
is the least likely to be flipped in that position. This means whichever
card has the highest numbers opposing the corner.

## Key Subcomponents:

### Model Subcomponents:

#### Board:

The Board is the main subcomponent. It is stored as a matrix of Cells. It follows a 
zero based coordinate system, with rows expanding down, and columns across. These Cells can either 
be playable or non-playable (read more in [Cell](#cell)). The Board is driven by the model,
the Board is told when and where to place Cards. 

The Board is in charge of updating the states of which player currently owns each cell. This
is accomplished upon a player playing a card to the Board. Upon this action, the board utilizes
a rules class to implement how the cells within the board will "Battle".

#### Hand:

The Hand is a subcomponent that houses the players Cards. It acts as the go-between for the 
Model to get Cards from the player. A Hand is implemented as a list of cards. It is designed
to constrain all the ways a model can interact with the Hand, instead of a list which has
many different functionalities.

#### Card:

The Card is a subcomponent that represents a playing card in the game triple triad. 
Each Card contains directional values. 
That meaning a north, south, east, and west values. The values span 1-10, with
the number 10 being displayed as the letter A. 

The Cards are used by the players to be played to the board. They are a separate entity that
is used by the other subcomponents. They have the ability to compare themselves to other Cards.

#### Cell:

A Cell is a subcomponent that is broken up into multiple parts. A Cell is a square
on the board that may or may not have a Card placed on it. It is currently broken into two subclasses of cell:

- Hole Cell
- Card Cell

A Hole cell is a non-playable cell that exists as a void space on the board. 

A Card cell is a playable cell. It tracks the current player that owns it. Once a cell
has been played to, it cannot be played to again.

#### BattleRules:

A Rule class dictates how the cells in the board interact with one another.
The Rules take in a Board and enact the given ruleset on the board. This manipulates
the state of the Board based on implementations.

### Controller Subcomponents:

#### Players

A player consists of AI players and Human players. The AI players are called 
asynchronously to produce a move. They use the created strategies to dictate their 
moves and then send those to the controller, so they can be acted on. 

A human player exists, however humans interact with the game through the view. 
Their commands are sent through the same command callback pattern, just from a
different component. 

### View Subcomponents:

#### Board Panel:

The Board panel extends the JPanel interface. It uses the ReadOnlyModel to get
the state of the board at a given point in the game. It draws out the cells as rectangle
shape objects, drawing different colors based on whether its occupied, not occupied, or
a hole cell. 

#### Hand Panel:

The hand panel extends the JPanel interface. This component is reused to draw
the hand panels for both players. It has an assigned color for each player.
The cards are drawn out showing as many as can be fit given the current screen size.

#### Graphics Card:

The Graphics Card component is a shape that is created to draw out the card. It displays
all relevant data to the card including, color, and each of the directional numbers.

## Source Organization:

The top most level of this project contains the source folder, testing folder, and documents folder.
Within the source are Model and View implementations. In the model directory is the
pieces that make up the model. This includes subdirectories for Cards, Cells, Rules, and 
remaining files for the other components.

The view folder thus far contains the text view.

The docs folder contains example configuration files to start a game of Triple Triad.
test_board.txt and test_hand.txt are simple examples for easy testing. The rest of the 
text files contain unique playing configurations for playing the game.
Also in the docs folder is the PlayerInteraction document explaining how players outside 
the model, view, and controller could interact with a game of Triple Triad.

## Changes For Part Two:

The changes that we implemented for part two include the creation of a ReadOnlyModel,
for the usage of the view. And the implementation of recommended methods that were
missing. These methods were implemented in the model, with some calling a board method 
as to hide observer methods that make sense in the board class. These include the following: 

- Adding a method to check how many cards a player can flip by playing at a given coordinate. 
This method takes in a card and a coordinate then returns the number of cells that change
owner, this includes the given card.

- Adding a method to check if a cell is open to be played to. This takes in a coordinate
and returns if the cell is open for cards.

- Adding a method to retrieve what the given object is at a coordinate.

- Adding methods for the number of rows and columns in the Board.

## Changes For Part Three:

Listed below are the changes: 

- Creating an object adapter for the ReadOnlyModel to prevent casting to a non-readonly model.

- Adding the ModelFeatures as a listener to the model. This is so the model
can send notifications upon certain important actions to the listener. 