import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


public class Main{
    static Scanner scan = new Scanner(System.in);
    static String file_path = "Scholarships.txt";
    static String finished_path = "Finished.txt";
    private static Scanner x;
    static File old_file = new File(file_path);
    static File old_file2 = new File(finished_path);
    static File new_file = new File("temp.txt");
    static File new_file2 = new File("temp_2.txt");
    public static void main(String args[]) throws IOException{
        
        
        
        displayCommands();

        
                
                
        boolean running = true;
        while (running){
            String command = scan.nextLine();
            if(command.toLowerCase().equals("n")){
                createNewScholarship(file_path);

            }
            else if(command.toLowerCase().equals("p")){
                displayScholarships(file_path);
            }
            else if(command.toLowerCase().equals("x")){
                break;
            }
            else if(command.toLowerCase().equals("f")){
                System.out.println("Enter the number of the scholarship finished: ");
                int num = scan.nextInt();
                finishScholarship(file_path, finished_path, num);
            }
                
            }

                System.out.println("Thank You for using this program.");
                



    }

    public static void displayCommands(){
        System.out.println("X: Quit");
        System.out.println("N: Create New Scholarship");
        System.out.println("P: Print Scholarships");
        System.out.println("F: Finish Scholarship");
    }

    public static int countLinesInFile(String fileName){
        Path path = Paths.get(fileName);
        long lines = 0;

        try{
            lines = Files.lines(path).count();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return (int) lines;
    }
    public static int getLineOfScholarship(int num) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        String line;
        
        int count = -1;
        while((line = br.readLine()) != null){
            count++;
            int index_space = line.indexOf(" ");
            int num_scholarship = Integer.parseInt(line.substring(0, index_space));
            if(num_scholarship == num){
                System.out.println("Line #" + count);
                br.close();
                return count;
                
            }
        }
        br.close();
        return -1;
    }
    public static String getDate(String scholarship, String delimiter){
        int i = scholarship.indexOf(delimiter);
        String date = scholarship.substring(i + 1, scholarship.length());
        date = date.strip();
        return date;
    }
    public static void createNewScholarship(String file_path) throws IOException{
        FileWriter fw = new FileWriter("temp.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        x = new Scanner(new File(file_path));
        x.useDelimiter("\n");
        Scholarship scholarship = new Scholarship();
        scholarship.instantiate(scan);
        Scholarship.setNum(countLinesInFile(file_path) + 1);
        int count = 0;
        while(x.hasNext()){
            
            String temp_scholar = x.next();
            temp_scholar = temp_scholar.strip();
            if(temp_scholar.equals("")){
                continue;
            }
            
            
            
            
            
            
            String date =  getDate(temp_scholar, "@");
            
            if(scholarship.getDate().isBefore(LocalDate.parse(date)) && count == 0){
                pw.println(scholarship.toString());
                pw.println(temp_scholar);
                count++;
            }
            else{
                pw.println(temp_scholar);
            }
            
        }
        if(count == 0){
            pw.println(scholarship.toString());
                count++;
        }
        x.close();
        pw.flush();
        pw.close();
        old_file.delete();
        File dump = new File(file_path);
        new_file.renameTo(dump);


    }

    public static void displayScholarships(String file_path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        String line;
        

        while((line = br.readLine()) != null){
            System.out.println("\t" + line);
        }
        br.close();
    }

    public static void finishScholarship(String file_path, String finished_path, int num) throws IOException{
        //still need to finish
        boolean scholarship_exists = false;
        
            String scholarship_finished = "";
            FileWriter fw = new FileWriter("temp.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(file_path));
            x.useDelimiter("\n");
            while(x.hasNext()){
                
                String temp_scholar = x.next();
                temp_scholar = temp_scholar.strip();
                if(temp_scholar.equals("")){
                    continue;
                }
                int count = Integer.parseInt(temp_scholar.substring(0, temp_scholar.indexOf(" ")));

                if(count != num){
                    pw.println(temp_scholar);
                }
                else{
                    scholarship_finished = temp_scholar;
                    scholarship_exists = true;
                }
                
                
                
            }

            if(scholarship_exists){
                FileWriter fw2 = new FileWriter("temp_2.txt");
                BufferedWriter bw2 = new BufferedWriter(fw2);
                PrintWriter pw2 = new PrintWriter(bw2);
                Scanner y = new Scanner(new File(finished_path));
                y.useDelimiter("\n");
                System.out.println("Scholarship finished: " + scholarship_finished);
                pw2.println(scholarship_finished);
                while(y.hasNext()){
                    
                    String temp_scholar = y.next();
                    temp_scholar = temp_scholar.strip();
                    if(temp_scholar.equals("")){
                        continue;
                    }
    
    
                    pw2.println(temp_scholar);
                    
                }

                y.close();
                pw2.flush();
                pw2.close();
                old_file2.delete();
                File dump2 = new File(finished_path);
                new_file2.renameTo(dump2);
            }
            else{
                System.out.println("Scholarship doesn't exist.");
            }

            
            
            x.close();
            
            pw.flush();
            pw.close();
            old_file.delete();
            File dump = new File(file_path);
            new_file.renameTo(dump);
            
        }
        

    }
