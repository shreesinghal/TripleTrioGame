Overview:

Our TripleTrios codebase implements a grid-based card game which is designed to support multiple players, cards with 
attack values, and configurable game setups. Our codebase was designed to create an interactive, card-based grid game 
that manages player interactions with a grid layout. Players can interact with cards(with a color corresponding to 
player and attack values) to achieve a winning state by arranging the cards according to the rules of TripleTrios. 
The logic of this game requires tracking the game state, managing player actions, and updating visual feedback for each
move. We assume that the set up of the game is a text view setup. This project is built with extensibility that allows 
for customization of card configurations, grid sizes, and game rules via the model, controller, and configurations. 
The scope of the project doesn’t include GUI or AI so that limits it to text based interface.

Quick start:

To get started using this code base, create a controller and model object and 
call the controller in the following manner:
TripleTrioModel model = new TripleTrioGameModel();
BufferedReader readable = new BufferedReader(new InputStreamReader(System.in));
TripleTrioController controller = new TripleTrioControllerImpl(readable, System.out);
controller.playGame(model, deckPath, gridPath);

Here, deckPath points to a .txt deckConfiguration file and gridPath points to a similar grid configuration file. 
The playGame method of the controller will call all supporting classes necessary to run and play the game.

Key components and subcomponents:

The way we implemented our codebase is around the Model-View-Controller pattern. 
Our Model is for the game logic of TripleTrios, the Controller is for user interaction and gameplay flow, and the View 
is for displaying the game state to the player. The model is where the core logic of the game and state management is. 
Here, we drive the game rules, track each player's actions, and evaluate winning conditions. We also enforce the rules
that the players must follow. The model class, TripleTrioModel, is where we manage the grid state and supporting 
classes such as Card, Player, and Cell which represent individual game entities. We ensure the coordinates and the grid
holding those components using a 2D array. The model controls the valid actions available to a player based on the
current state of the game and rules we have implemented. It reacts to input that is given from the controller and 
updates the internal state which is then reflected back to the view and the user. The controller controls the user 
interactions and game progression by facilitating communication between the Model and View. It captures user inputs, 
passes commands to the Model, and triggers updates to the view. TripleTrioControllerImpl class allows for actions such 
as starting the game and placing the game, playGame method and playMove method respectively, or resetting the grid. 
Additional classes like GridConfigReader and CardDataBaseReader are used to initialize grid configurations and to load 
custom card setups. The TripleTrioTextView class is the view and it displays the grid, players, and the cards according 
to the current state that is maintained by the model. It is a “passive” component which is driven by updates from the 
Controller and has no control over the game logic. The view has a toString and render function which represents the 
current state of the game to the user in the format required. The view is crucial because it continuously reflects 
changes to the game based on player actions and system responses. 
For our AI player, we made multiple strategies the AI player can use. In our controller, we call these strategy classes.

How to Play:

To play with two human players, use starter code from ThreeTrios. Each player has their own view that only they can
play on. Red goes first, selecting a card from the red panel on their view and placing it on the grid. The blue then 
does the same on their view. The views automatically update. The game prevents players from clicking on the wrong view/
wrong panel/on the wrong tile. 

Command Line:
We document the progress of the game in the command line. Every click prints something - whether that be a player 
taking their turn, placing a card, or clicking on an arbitrary point in the game. It also prints the winner.

Source organization:

Our codebase is organized into directories by component so that its navigation through the MVC is straightforward.
We have a model directory containing all key components/subcomponents of the model, controller directory for its own, 
view directory for its own, and test directory for its own.

Invariant:

One invariant that is enforced in our implementation is that the input must be given in the form 
[x position] [y position] [card name]. The user should enter using this format to tell the computer what card is placed 

where on the board. If this format is not followed - either if the position is out of bounds or if the values entered 
do not match the order/type indicated - then the user is prompted again with the message. This is checked for in the 
model. The model would throw an exception which would then be caught in the controller. The controller would then tell
the view to display a message to the user indicating that the input format was incorrect and that they should 
try again.


Changes for part 2:
In part 2 we made some changes to our initial design from homework 5. In this project, we added some methods into our 
model to ensure that it worked well with our GUI view and strategies. First, we created a ReadOnlyTripleTrioModel 
which is a interface that is like our original model interface, but it only includes the methods that do not change the 
state of our game. This interface is used in our TTFrameImpl, HandPanel, and GridPanel. This is so that these classes 
don't have the ability to even accidentally change our model therefore changing the state of the game. In our model, we
added methods getGridHeight and getGridWidth to be able to get the height and width of the grid so that when we are 
creating the GUI, we need to be able to see what our grid width and height is so that we can a) draw the grid and 
b) make it resizable. We ensured that we had the methods getCurrentGrid and getOriginalGrid that gets the grid 
respectively. This was so that we could have a copy of the original grid before changes were applied just in case
we could go back and make changes. While we already had the logic implemented, we added a getPlayerScore method to just 
calculate a single players score. 

In our controller, we made some additions so that our controller interface, TripleTrioController, would be able to 
account for both the text view and the GUI view. We added methods like playGameWithModel, handleCellClickForHand, and
handleCellClickForGrid. These are methods that account for the GUI view, and when implemented into the controller for 
the textViewController, we do not have a implementation for it. 



Changes for part 3:
Firstly, we would like to note that we updated our code base to include the feedback we received from homework 6. We 
first made sure that our model had a method to retrieve a copy of the grid, as we had forgotten to implement this in 
homework 6. This ensured that we adhered to encapsulation principles by avoiding direct access to the grid's state. 
Additionally, we modified our strategies to accept only the ReadOnlyModel instead of the actual model, preventing
accidental modifications to the model's state, which could disrupt the entire game flow.

From the previous assignment, we introduced a feature to allow the model to start a GUI game 
(line 114 in TripleTrioModel). We also added new methods, namely notifyPlayerTurn, notifyCardPlaced, 
and notifyGameStateUpdated. These methods notify registered listeners about specific events in the game model, 
such as changes in player turns, card placements, or updates to the game state. These changes enhanced the communication
between the model and external components like the views and controllers.

We updated our GUI view (TTTFrameViewImpl.java) to fix bugs and reflect changes to the game's overall functionality. 
In the last assignment, players could not place cards onto the grid despite the presence of the underlying logic. This 
issue was resolved in this assignment. We also ensured that our game supports two views created from the main method,
both connected to the same model, enabling synchronized gameplay. Each view corresponds to one player, ensuring that
players can only play during their turn and cannot interact with the other player’s view. These changes were primarily
implemented in TTTFrameViewImpl and TripleTrioHumanPlayerController.

To facilitate these updates, we adopted the observer design pattern to ensure communication between the model, views, 
and controllers. By doing so, we decoupled the components, improving the modularity and maintainability of our code.


Homework 7 additions: 
I will now explain the new files that we added to our program. All classes in the view package remain the same. 
We added two new classes in our model package, a PlayerHumanImpl and PlayerAIImpl, both which implement the Player
interface we have had since homework 5. The purpose of creating these two classes were to have a way to keep track 
of which player is human and which player is AI since we are now accounting for two different types of players. These 
are used in the main class (ThreeTrios) and in the model. In our controller, we did add new classes/interfaces. 
We first created two controllers, one for the AI player and one for the Human player. We realized that a lot of the 
functionality was duplicated in both classes so in order to take care of the duplicated code, we created 
TripleTrioAbstractGUIController and both the AI and Human controller are subclasses of that class. This is a use of 
abstraction as we abstracted duplicated code into a separate class, creating for modularity and single responsibility. 
We also created two new interfaces, TripleTrioModelListener and TripleTrioFeatureController. TripleTrioFeatureController
is the interface that both the GUI view and text-based view use. It delegates between the model and view. 
TripleTrioModelListener is for the behaviors needed for a controller that listens and works specifically with the
TripleTrio model. It allows the model to know specifics to which player is playing, where a card was placed, 
and that the game state has been updated. We ensured that the ai player only calculates the move, and all other
functions of the ai player is the same as a human. This was achieved through the AbstractGUIController class, which
both controllers use.