# Animal_Keeper
 This program is supposed to replicate a zoo keeping system that knows the capacity of every enclosure and cage, every animal and the food stock. It can tell whether actions performed in the zoo such as moving animals from one cage to another or feeding are possible.

 INPUT:
 As input, the program should accept a (space-separated) sequence of commands,
each in one of the following formats:
1. 0 t “name” h: add (command ‘0’) an animal of type t (see the list of
animals given before) with name “name” to the home with number h
(see the restrictions given later for the home IDs);
2. 1 “name” h: move the animal with name “name” from its current home
to the home with number h;
3. 2 “name”: remove the animal with name “name” from the Zoo;
4. 3 f x: buy of food type f (see the list of food given above) the amount x;
5. 4 f x h: feed of food type f the amount x to home h.

OUTPUT:
The output must consist of a (space-separated) sequence of responses, each
reflecting the result of executing the corresponding command in the input se-
quence. A response is either of the form c, with c being the number of the
corresponding input command (see the list given before), in case the command
could be executed without any issues, or c! if the command could, for what-
ever reason, not be executed. You should think about what can possibly go
wrong, and prevent that your program executes a command under any of those
circumstances.

SPECIFICATIONS:
1. There are 10 cages in the Zoo, numbered 0 to 9, and 5 enclosures, numbered 10 to 14;
2. Each animal has a unique name. Accommodation of more than one animal with the same name is not allowed;
3. You have a single storage for each type of food (one for hay, one for corn, etc.), each with a maximum capacity of 100. Each storage initially contains no food.