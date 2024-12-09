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


## Homework 7 additions: 
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
functions of the AI player is the same as a human. This was achieved through the AbstractGUIController class, which
both controllers use.


## Homework 8 Notes:
We created adapter classes for the View. Since the view takes in their model, we also created an adapter for the model.
In the process of creating the model adapter, we had to create listener, card, cell, and player adapters as well.

However, some methods could not be adapted. Therefore, our adapted view does not work completely. We made a design
decision to have our grid and hand panel accessible outside our view class. This is not how our providers designed 
their code, so it cannot be implemented. This design flaw on our end was not caught until 12/4 when we asked the 
Professor, and she pointed it out. Since this was not caught in HW6 grading and HW7 grading was not released by then, we 
decided not to edit our code for this assignment but rather justify it in our README. 


Here is a complete list of all methods that could not be adapted:

## ViewAdapterTheirsToOurs:
- getHandView(CardColor color)
  Can not be done because ThreeTriosView uses composition with ThreeTriosPanel. ThreeTriosPanel contains their handView.
  This class cannot access threeTriosPanel, which would otherwise handle this method function. Instead, our design 
  should not have made the hand view accessible outside the view class. This way it abstracts the functionality of the 
  view. 

- getGridPanel()
  Adapting could not be done because ThreeTriosView uses composition 
  with ThreeTriosPanel. ThreeTriosPanel contains their gridPanel. This class cannot access threeTriosPanel, which would 
  otherwise handle this method function.  Instead, our design should not have made the grid view accessible outside the 
  grid class. This way it abstracts the functionality of the view.


## ModelAdapterOurToTheirs:
- numCardsPlayerCanFlip(int row, int column, Card card, PlayerType player)
  We cannot implement this because our executeBattlePhase() does this using private helper methods. We abstract the 
  score so that it is calculated only inside the executeBattlePhase method and not seen by other classes. Because of
  that, there is no way to get the score outside of that class. To implement the requested method, we would need to 
  refactor our TripleTrioGameModel class substantially, potentially breaking encapsulation and altering the core game 
  logic. This would be a significant undertaking that goes beyond simple adaptation and could introduce new bugs or 
  inconsistencies in our existing, working implementation

- fetchPlayerScore(PlayerType player)
  Our model's getPlayerScore() method is intentionally private. This design choice encapsulates the scoring logic within
  the model, preventing external access or manipulation. The score depends on the current state of the game board and is 
  recalculated during each battle phase. This makes it difficult to provide an up-to-date score without potentially 
  exposing internal game state or triggering unintended side effects.

  
## CardAdapterTheirsToOurs:
- getColor()
  Their model keeps track of the color based on what player's hand the card is in, not within their card class. So it's
  not possible to get the color now. Due to these design differences, there's no way for our adapter to determine the
  color (ownership) of a card solely from the provider's Card object1.

- setCardColor(CardColor color)
  They do not store the color of the card in their card class. Instead, the model handles this so there is no 
  equivalent method. Our implementation allows for changing card ownership directly on the card object4, while the 
  provider's design seems to treat cards as immutable entities with ownership managed externally. These fundamental 
  differences in design and implementation make it impossible to directly adapt these methods without significant 
  changes to either our adapter or the provider's model structure

## ListenerModelAdapterTheirsToOurs:
- onCardPlaced(int x, int y, Card card)
  This is because our implementation breaks a turn into two parts
  (1) choosing a card and (2) placing it down their implementation combines the two in a single step, so there is no 
  equivalent method because their implementation doesn't break their step into two publicly.


## File Limit 
In order to stay within the file limit for this assignment, we removed the View screenshots from last homework since 
this homework doesn't require it. Also, we removed the strategies test because we are not using the AI player in this
homework. 

We also removed our Main.java, and mains that worked with AI. We deleted the hw8.iml, UI screenshot, README, all our 
tests, and strategies folder that our providers provided to us since we didn't need that for our code to work. 


## Homework 9 - Extra Credit:
We implemented all four levels. 
For level 0 : We created a decorator in order to wrap our GridPanel.java class so we could add the hints. We started by creating a HintDecorator interface, HintDecorator.java. We then created GridPanelHintDecorator.java which implements HintDecorator. This class decorates a GridPanel and adds the ability to display hints about the number of potential flips that can be made in a given cell on the grid, based on a selected card. These were added in in cs3500.tripletrios.view. The main class we used is just ThreeTrios. **In order to get the hints to show up, we press H and they will show up**


For level 1 : For this, we created new strategies to account for the two variants. We started by creating an interface BattleStrategy.java. We then made DefaultBattleStrategy, FallenAceBattleStrategy, and ReverseBattleStrategy which all implement BattleStrategy. These three strategies are accounting for the different variants during the battle phase: keeping it default, fallen ace, and reverse. All of these strategies were added to cs3500.tripletrios.strategies. We then made a variant model, TripleTrioVariantModel.java, which is the model for a game using the variant battle phases. This model class extends TripleTrioGameModel so it implements all the same functionality but still accounts for a different battle phases. This is in cs3500.tripletrios.model. We also created a new main so we could properly run the configurations, this is called TripleTrioReverseAce. 

For level 2: For this, we created two more strategies, PlusBattleStrategy.java and SameBattleStrategy.java. Both of these strategies implement the BattleStrategy. We also created a BattleStrategyFactory which was to create and return an appropriate BattleStrategy based on two boolean flags: sameRule and plusRule. All of these are in cs3500.tripletrios.strategies. We created another variant model called TripleTrioSamePlusModel.java which is in cs3500.tripletrios.model. The main we used so we could properly run the configurations is called ThreeTriosSamePlus. 

For level 3: For this, we created TripleTrioCombinedModel in cs3500.tripletrios.model. The TripleTrioCombinedModel class is an extension of the TripleTrioGameModel that integrates two sets of battle strategies based on different game rules. It combines Set 1 strategies (such as reverse and fallen ace rules) and Set 2 strategies (same and plus rules) to determine how card battles are resolved, ensuring that the appropriate rules are applied during each battle phase. We also made a final main class called ThreeTriosLevel3 to run this. 

We did not modify any of the existing code base from our previous projects. However, we did add onto classes like GridPanel.java and TTFrameViewImpl.java in cs3500.tripletrios.view. We added methods and fields to account for the wrapper class in GridPanel.java. We also added a keyListener in TTFrameViewImpl.java to account for finding the hints. **In order to get the hints to show up, we press H and they will show up**


