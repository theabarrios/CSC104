import java.util.Scanner;

class Book{
    private String name;
    private String author;
    private String genre;
    private String publishYear;
    private double price;
    private double quality;
    private int quantity;

    public Book(String name, 
                String author, 
                String genre, 
                String publishYear){
        this.name=name;
        this.author=author;
        this.genre=genre;
        this.publishYear=publishYear;
        setPrice(0.00);
        setQuality(100.00);
        setQuantity(1);
    }

    //getters
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public double getQuality(){
        return quality;
    }
    public double getQuantity(){
        return quantity;
    }
    
    //setters
    public void setPrice(double price){
        if(price>=0){
            this.price=price;
        }
    }

    public void setQuality(double quality){
        if(quality>=0 && quality<=100){
            this.quality=quality;
        }
    }

    public void setQuantity(int quantity){
        if(quantity>0){
            this.quantity=quantity;
        }
    }

    //methods
    public void calculateResellPrice(){
        if(quality>90){
            price=price;
        }
        else if(quality<=90 && quality>=50){
            price=price*(quality/100);
        }
        else{
            price=0.00;
        }
    }

    public void modifyBasePrice(double price){
        System.out.println("Attempting to set price to " + price + "...");
        if(price<1){
            System.out.println("Invalid. Price must be more than zero.");
        }
        else{
            this.price=price;
            System.out.println("Set Price Successfully.");
        }
    }

    public void modifyQuality(double quality){
        System.out.println("Attempting to set quality to " + quality + "%...");
        if(quality<0 || quality >100){
            System.out.println("Invalid. Quality must be in range 0 to 100.");
        }
        else{
            this.quality=quality;
            System.out.println("Set Quality Successfully.");
        }
    }

    public void modifyQuantity(int quantity){
        System.out.println("Attempting to set quantity to " + quantity + "...");
        if(quantity<1){
            System.out.println("Invalid. Quantity must be at least 1.");
        }
        else{
            this.quantity=quantity;
            System.out.println("Set Quantity Successfully.");
        }
    }

    public String DisplayRating(){
        if (quality>90){
            return "Pristine";
        }
        else if(quality<=90 && quality >=50){
            return "Good";
        }
        else{
            return "Poor";
        }
    }

    public void displayBookInformation(int i){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("           BOOK %d          \n", i+1);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name: " + getName());
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Publish Year: " + publishYear);
        System.out.println("Resell Price: " + getPrice());
        System.out.println("Quality: " + quality);
        System.out.println("Quality Rating: " + DisplayRating());
        System.out.println("Quantity: " + quantity);
    }
}

public class BookResellSystem{
    public static void main(String[] args){
        System.out.println("=========================BOOK RESELL SYSTEM=========================");
        Scanner scanner= new Scanner(System.in);
        
        int numOfBooks;
        System.out.printf("Enter number of books: ");
        numOfBooks=scanner.nextInt();

        while(numOfBooks<5){
            System.out.printf("\nInvalid. Must have at least 5 books. \nReenter: ");
            numOfBooks=scanner.nextInt();
        }

        Book[] books= new Book[numOfBooks];
        scanner.nextLine();

        String name;
        String author;
        String genre;
        String publishYear;
        double basePrice;
        double quality;
        int quantity;

        for(int i=0; i<numOfBooks; i++){
            System.out.printf("\n=====Book %d=====", i+1);
            System.out.printf("\nEnter Name: ");
            name=scanner.nextLine();

            System.out.printf("Enter Author: ");
            author=scanner.nextLine();

            System.out.printf("Enter Genre: ");
            genre=scanner.nextLine();

            System.out.printf("Enter Year Published: ");
            publishYear=scanner.nextLine();

            books[i]= new Book(name,
                         author,
                         genre,
                         publishYear);

            System.out.printf("Enter Base Price: ");
            basePrice=scanner.nextDouble();
            scanner.nextLine();
            books[i].modifyBasePrice(basePrice);

            System.out.printf("\nEnter Quality: ");
            quality=scanner.nextDouble();
            scanner.nextLine();
            books[i].modifyQuality(quality);

            System.out.printf("\nEnter Quantity: ");
            quantity=scanner.nextInt();
            scanner.nextLine();
            books[i].modifyQuantity(quantity);
            
        }
	System.out.printf("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("Before Quality-based Price Adjustment: ");
        for(int i=0; i<numOfBooks;i++){
            System.out.printf("\n=====Book %d=====\n", i+1);
            System.out.println("Name: " + books[i].getName());
            System.out.println("Price: " + books[i].getPrice());
        }
	System.out.printf("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("After Quality-based Price Adjustment: ");
        for(int i=0; i<numOfBooks;i++){
            books[i].calculateResellPrice();
            System.out.printf("\n=====Book %d=====\n", i+1);
            System.out.println("Name: " + books[i].getName());
            System.out.println("Price: " + books[i].getPrice());
        }

        System.out.printf("\n\n==============BOOK INFORMATION==============\n");
        for(int i=0;i<numOfBooks;i++){
            books[i].displayBookInformation(i);
        }

        System.out.printf("\n\n==============FINAL SUMMARY==============\n");
        Book highest=books[0];
        Book lowest=books[0];
        double highestQuality=books[0].getQuality();
        double lowestQuality=books[0].getQuality();
        int booksToSell=0;
        double totalPrice=0;
        double averagePrice;

        for(int i=0; i<numOfBooks; i++){
            if(books[i].getQuality()>highestQuality){
                highestQuality=books[i].getQuality();
                highest=books[i];
            }

            if(books[i].getQuality()<lowestQuality){
                lowestQuality=books[i].getQuality();
                lowest=books[i];
            }
            if(books[i].getQuality()>=50){
                booksToSell+=books[i].getQuantity();
                totalPrice+=(books[i].getPrice())*books[i].getQuantity();
            }
        }
        if(booksToSell>0){
            averagePrice=totalPrice/booksToSell;
        }
        else{
            averagePrice=0.00;
        }
        
        System.out.println("Book with Highest Quality: " + highest.getName());
        System.out.println("Book with Lowest Quality: " + lowest.getName());
        System.out.println("Total Number of Books that can be sold: " + booksToSell);
        System.out.printf("Average Price of books that can be sold: %.2f ", averagePrice);
    }
}
