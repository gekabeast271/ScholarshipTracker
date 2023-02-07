
import java.time.LocalDate;
import java.util.Scanner;

public class Scholarship {
    
    
    //Attributes//////
    private String name;
    private int amount;
    private static int num;
    

    //year-month-day -- ex."2023-11-04" is November 4th, 2023
    private LocalDate deadline;
    /////////////////

    //Constructors///////
    public Scholarship(){
        this.name = "";
        this.amount = 0;
        this.deadline = LocalDate.parse("2023-01-01");
        num++;
        
    }

    public Scholarship(String name, int amount, LocalDate deadline){
        this.name = name;
        this.amount = amount;
        this.deadline = deadline;
    }
    /////////////////////



    //Getter and Setter Methods//
    public String getName(){
        return this.name;
    }
    public int getAmount(){
        return this.amount;
    }
    public LocalDate getDate(){
        return this.deadline;
    }
    public static int getNum(){
        return num;
    }
    // public String getDateString(){
    //     return this.deadline.toString();
        
    // }

    public void setName(String name){
        this.name = name;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public void setDate(LocalDate deadline){
        this.deadline = deadline;
    }
    public static void setNum(int a){
        num = a;
    }
    ///////////////////////////////
   

    public void instantiate(Scanner scan){
        System.out.println("Instantiating Scholarship:");

        System.out.println("Enter the name: ");
        this.name = scan.nextLine();
        System.out.println("Enter the amount: ");
        this.amount= scan.nextInt();

        String date = "2023-";
        System.out.println("Enter the month of deadline: ");
        int c = scan.nextInt();

        if(c < 10){
            date+="0"+c+"-";
        }
        else{
            date+=c+"-";
        }
        System.out.println("Enter the day of deadline: ");
        int d = scan.nextInt();
        if(d < 10){
            date+="0"+d;
        }
        else{
            date+=d;
        }
        
        this.deadline = LocalDate.parse(date);
    }

    public String toString(){
        return num+ " "+name +" | " +"$"+amount+" @"+deadline;
    }


}
