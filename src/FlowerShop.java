import java.util.Arrays;
import java.util.Comparator;

/**
 * General class for flowers
 */
class Flower {
    private String name;
    private double price;
    private int freshness; // from 1 (fresh) to 10 (not fresh)
    private double stemLength;

    public Flower(String name, double price, int freshness, double stemLength) {
        if (price <= 0 || freshness < 1 || freshness > 10 || stemLength <= 0) {
            throw new IllegalArgumentException("Invalid flower parameters.");
        }
        this.name = name;
        this.price = price;
        this.freshness = freshness;
        this.stemLength = stemLength;
    }

    public double getPrice() {
        return price;
    }

    public int getFreshness() {
        return freshness;
    }

    public double getStemLength() {
        return stemLength;
    }

    @Override
    public String toString() {
        return name + " (Freshness: " + freshness + ", Stem Length: " + stemLength + " cm, Price: $" + price + ")";
    }
}

/**
 * Rose class, subclass of Flower
 */
class Rose extends Flower {
    public Rose(double price, int freshness, double stemLength) {
        super("Rose", price, freshness, stemLength);
    }
}

/**
 * Tulip class, subclass of Flower
 */
class Tulip extends Flower {
    public Tulip(double price, int freshness, double stemLength) {
        super("Tulip", price, freshness, stemLength);
    }
}

/**
 * Lily class, subclass of Flower
 */
class Lily extends Flower {
    public Lily(double price, int freshness, double stemLength) {
        super("Lily", price, freshness, stemLength);
    }
}

/**
 * General class for accessories
 */
class Accessory {
    private String type;
    private double price;

    public Accessory(String type, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Accessory price can't be negative.");
        }
        this.type = type;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " (Price: $" + price + ")";
    }
}

/**
 * Ribbon class, subclass of Accessory
 */
class Ribbon extends Accessory {
    public Ribbon(double price) {
        super("Ribbon", price);
    }
}

/**
 * Wrapper class, subclass of Accessory
 */
class Wrapper extends Accessory {
    public Wrapper(double price) {
        super("Wrapper", price);
    }
}

/**
 * Card class, subclass of Accessory
 */
class Card extends Accessory {
    public Card(double price) {
        super("Card", price);
    }
}

/**
 * Class representing a bouquet
 */
class Bouquet {
    private Flower[] flowers;
    private Accessory[] accessories;

    public Bouquet(Flower[] flowers, Accessory[] accessories) {
        this.flowers = flowers;
        this.accessories = accessories;
    }

    /**
     * Calculate the total cost of the bouquet
     */
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Flower flower : flowers) {
            totalCost += flower.getPrice();
        }
        for (Accessory accessory : accessories) {
            totalCost += accessory.getPrice();
        }
        return totalCost;
    }

    /**
     * Sort flowers by freshness
     */
    public void sortByFreshness() {
        Arrays.sort(flowers, Comparator.comparingInt(Flower::getFreshness));
    }

    /**
     * Find flowers within a given stem length range
     */
    public Flower[] findFlowersByStemLength(double minLength, double maxLength) {
        return Arrays.stream(flowers)
                     .filter(flower -> flower.getStemLength() >= minLength && flower.getStemLength() <= maxLength)
                     .toArray(Flower[]::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flowers:\n");
        for (Flower flower : flowers) {
            sb.append(flower).append("\n");
        }
        sb.append("Accessories:\n");
        for (Accessory accessory : accessories) {
            sb.append(accessory).append("\n");
        }
        return sb.toString();
    }
}

public class FlowerShop {
    public static void main(String[] args) {
        try {
            // Create flowers
            Flower[] flowers = {
                    new Rose(10.5, 2, 50),
                    new Tulip(7.0, 5, 30),
                    new Lily(12.0, 3, 40)
            };

            // Create accessories
            Accessory[] accessories = {
                    new Ribbon(2.0),
                    new Wrapper(3.0),
                    new Card(1.5)
            };

            // Create bouquet with flowers and accessories
            Bouquet bouquet = new Bouquet(flowers, accessories);

            // Display bouquet before sorting
            System.out.println("Bouquet before sorting by freshness:");
            System.out.println(bouquet);

            // Sort flowers by freshness
            bouquet.sortByFreshness();
            System.out.println("Bouquet after sorting by freshness:");
            System.out.println(bouquet);

            // Find flowers within a stem length range
            double minLength = 35;
            double maxLength = 55;
            Flower[] foundFlowers = bouquet.findFlowersByStemLength(minLength, maxLength);
            if (foundFlowers.length > 0) {
                System.out.println("Flowers found within stem length range (" + minLength + " - " + maxLength + " cm):");
                for (Flower flower : foundFlowers) {
                    System.out.println(flower);
                }
            } else {
                System.out.println("No flowers found within stem length range (" + minLength + " - " + maxLength + " cm).");
            }

            // Calculate total cost of the bouquet
            double totalCost = bouquet.calculateTotalCost();
            System.out.println("Total cost of the bouquet: $" + totalCost);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
