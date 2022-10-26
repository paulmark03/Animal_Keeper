import java.util.Scanner;

/**
 * @author Paul Marcu
 * @ID 1844989
 */
public class AnimalKeeper {

    public static void main(String[] agrs) {
        MyZoo myZoo = new MyZoo();
        myZoo.initZoo();

        Scanner scanner = new Scanner(System.in);
        String input;
        int animal;
        String animalName;
        int home;
        int food;
        int amount;
        input = scanner.nextLine();
        input += " ";
        while (input.length() > 1) {
            int nextSpace = input.indexOf(" ");
            String command = input.substring(0, nextSpace);
            input = input.substring(nextSpace + 1);

            if (command.equals("0")) {
                nextSpace = input.indexOf(" ");
                animal = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                animalName = input.substring(0, nextSpace);
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                home = Integer.parseInt(input.substring(0, nextSpace)); // not sure
                //conditie daca merge animalu cu home in care e pus
                input = input.substring(nextSpace + 1);

                if (myZoo.addAnimal(animal, animalName, home)){
                    System.out.print(command + " ");
                } else {
                    System.out.print(command + "! ");
                }
            } else if (command.equals("1")) {
                nextSpace = input.indexOf(" ");
                animalName = input.substring(0, nextSpace);
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                home = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                if (myZoo.moveAnimal(animalName, home)){
                    System.out.print(command + " ");
                } else {
                    System.out.print(command + "! ");
                }

            } else if (command.equals("2")) {
                nextSpace = input.indexOf(" ");
                animalName = input.substring(0, nextSpace);
                input = input.substring(nextSpace + 1);
                //remove animal

                if (myZoo.removeAnimal(animalName)){
                    System.out.print(command + " ");
                } else {
                    System.out.print(command + "! ");
                }

            } else if (command.equals("3")) {
                // Food food = new Food(Integer.parseInt(input.substring(2, 3)));
                // get x amount

                nextSpace = input.indexOf(" ");
                food = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                amount = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                if (myZoo.addFood(food, amount)){
                    System.out.print(command + " ");
                } else {
                    System.out.print(command + "! ");
                }

            } else if (command.equals("4")) {
                //feed food type to animal type
                //check is they are compatible
                //check if there is food or animal

                nextSpace = input.indexOf(" ");
                food = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                amount = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                nextSpace = input.indexOf(" ");
                home = Integer.parseInt(input.substring(0, nextSpace));
                input = input.substring(nextSpace + 1);

                if (myZoo.giveFood(food, amount, home)){
                    System.out.print(command + " ");
                } else {
                    System.out.print(command + "! ");
                }
            }
        }
    }
}