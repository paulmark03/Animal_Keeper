import java.util.ArrayList;

/**
 * @author Paul Marcu
 * @ID 1844989
 */
public class MyZoo {
    ArrayList<Animal> allAnimalsList = new ArrayList<Animal>();
    ArrayList<House> allHouses = new ArrayList<House>();
    ArrayList<Food> foodStorage = new ArrayList<Food>();

    /**
     * 1. Lion;
     * 2. Tiger;
     * 3. Leopard;
     * 4. Zebra;
     * 5. Antelope;
     * 6. Giraffe;
     * 7. Bear.
     */
    public class Animal {
        int type;
        String name;

        public Animal(int argType, String argName) {
            type = argType;
            name = argName;
        }

        /**
         * Returns 0 for carnivore, 1 for herbivore and 2 for omnivore
         * @return int
         */
        public int getType() {
            if (this.type >= 1 && this.type <= 3) {
                return 0;
            } else if (this.type >= 4 && this.type <= 6){
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * 1. hay;
     * 2. corn;
     * 3. grain;
     * 4. carrots;
     * 5. chicken;
     * 6. beef.
     */
    public class Food {
        int quantity;

        public Food() {
            quantity = 0;
        }

        public boolean addAmount(int amount) {
            if (this.quantity + amount <= 100) {
                this.quantity += amount;
                return true;
            } else {
                return false;
            }
        }

        public boolean removeAmount(int amount) {
            if (this.quantity - amount >= 0) {
                this.quantity -= amount;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * houses 0-9 are cages (type = 0)
     * houses 10-14 are enclosures (type = 1)
     */
    public class House {
        ArrayList<Animal> animalsInHouse = new ArrayList<Animal>();
        int type;

        public House(int houseType) {
            type = houseType;
        }

        public boolean checkCapacity() {
            if (this.type == 0) {
                return this.animalsInHouse.size() < 2;
            } else {
                return this.animalsInHouse.size() < 6;
            }
        }

        public boolean checkIfAnyAntelope() {
            for (Animal animal : this.animalsInHouse) {
                if (animal.type == 5) {
                    return true;
                }
            }
            return false;
        }

        public boolean checkIfAnyCarnivore() {
            for (Animal animal : this.animalsInHouse) {
                if (animal.getType() != 1) {
                    return true;
                }
            }
            return false;
        }

        public boolean checkAllSameTypes(int type) {
            for (Animal animal : this.animalsInHouse) {
                if (animal.type != type) {
                    return false;
                }
            }
            return true;
        }

        public boolean checkAnimalInHouse(Animal animalToFind) {
            int indexOf = this.animalsInHouse.indexOf(animalToFind);
            return indexOf != -1;
        }

        public Animal removeAnimal(Animal animalToRemove) {
            int indexOf = this.animalsInHouse.indexOf(animalToRemove);
            Animal returnAnimal = this.animalsInHouse.get(indexOf);
            this.animalsInHouse.remove(returnAnimal);
            return returnAnimal;
        }

        public boolean addAnimal(Animal animalToAdd) {
            if (!this.checkCapacity()) {
                return false;
            }

            // cases in which not to add herbivores
            if (animalToAdd.getType() == 1) {
                if (this.type == 0) {
                    return false;
                }
                if (this.checkIfAnyCarnivore()) {
                    return false;
                }
            } else {
                if (animalToAdd.type == 1 && !this.checkAllSameTypes(1)) {
                    return false;
                }
                if (animalToAdd.type >= 2 && animalToAdd.type <= 3 && this.animalsInHouse.size() > 0) {
                    return false;
                }
                if (animalToAdd.type == 7 && !this.checkAllSameTypes(7)) {
                    return false;
                }
            }

            this.animalsInHouse.add(animalToAdd);
            return true;
        }
    }

    public void initZoo() {
        for (int i=0; i < 15; i++) {
            House myHouse;
            if (i < 10) {
                myHouse = new House(0);
            } else {
                myHouse = new House(1);
            }
            this.allHouses.add(myHouse);
        }
        // adding one extra food type on pos 0 for simplicity
        for (int i = 0; i <= 6; i++) {
            Food myFood = new Food();
            this.foodStorage.add(myFood);
        }
    }

    public Animal findAnimalByName(String animalName) {
        for (Animal animal : this.allAnimalsList) {
            if (animalName.equals(animal.name)) {
                return animal;
            }
        }
        return new Animal(-1, "");
    }

    public int findAnimalHouse(Animal animal) {
        for (int i=0; i < 15; i++) {
            if (this.allHouses.get(i).checkAnimalInHouse(animal)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addAnimal(int animalType, String animalName, int house) {
        for (Animal animal : this.allAnimalsList) {
            if (animalName.equals(animal.name)) {
                return false;
            }
        }

        if (animalType < 1 || animalType > 7) {
            return false;
        }
        if (house < 0 || house > 14) {
            return false;
        }

        Animal myAnimal = new Animal(animalType, animalName);

        if (this.allHouses.get(house).addAnimal(myAnimal)) {
            this.allAnimalsList.add(myAnimal);
            return true;
        } else {
            return false;
        }
    }

    public boolean moveAnimal(String animalName, int newHouse) {
        if (newHouse < 0 || newHouse > 14) {
            return false;
        }

        Animal myAnimal = this.findAnimalByName(animalName);
        if (myAnimal.type == -1) {
            return false;
        }

        int houseWithAnimal = this.findAnimalHouse(myAnimal);
        if (houseWithAnimal == -1) {
            return false;
        }

        this.allHouses.get(houseWithAnimal).removeAnimal(myAnimal);

        return this.allHouses.get(newHouse).addAnimal(myAnimal);
    }

    public boolean removeAnimal(String animalName) {
        Animal myAnimal = this.findAnimalByName(animalName);
        if (myAnimal.type == -1) {
            return false;
        }

        int houseWithAnimal = this.findAnimalHouse(myAnimal);
        if (houseWithAnimal == -1) {
            return false;
        }

        this.allHouses.get(houseWithAnimal).removeAnimal(myAnimal);

        return true;
    }

    public boolean addFood(int foodType, int amount) {
        if (foodType < 1 || foodType > 6) {
            return false;
        }
        return this.foodStorage.get(foodType).addAmount(amount);
    }

    public boolean giveFood(int foodType, int amount, int house) {
        if (foodType < 1 || foodType > 6) {
            return false;
        }

        //check house not empty
        if (this.allHouses.get(house).animalsInHouse.size() == 0) {
            return false;
        }

        //check carnivore food
        if (this.allHouses.get(house).animalsInHouse.get(0).getType() == 0 && foodType <= 4) {
            return false;
        }

        //check herbivore food
        if (this.allHouses.get(house).animalsInHouse.get(0).getType() == 1) {
            if (this.allHouses.get(house).checkIfAnyAntelope() && foodType == 4) {
                return false;
            } else {
                if (foodType >= 5) {
                    return false;
                }
            }
        }
        //check bears food
        if (this.allHouses.get(house).animalsInHouse.get(0).getType() == 2 && foodType <= 3) {
            return false;
        }

        //check enough food and substract
        return this.foodStorage.get(foodType).removeAmount(amount);
    }
}