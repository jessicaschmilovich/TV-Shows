import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class NetworkMapMenu 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        NetworkMap networkMap = new NetworkMap();

        try 
        {
            networkMap.populateFromFile("Shows.txt");
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
            scanner.close();
            return;
        }

        while (true) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. View all TV shows by network");
            System.out.println("2. Search shows by cast member");
            System.out.println("3. View shows by network sorted by time slot");
            System.out.println("4. Add a show to our fantastic list!");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) 
            {
                System.out.println("\nAll TV shows by network:\n");
                networkMap.printAllShows();
            } 
            else if (choice == 2) 
            {
                System.out.print("\nEnter cast member name: ");
                String castMember = scanner.nextLine();
                List<TVShow> showsByCast = networkMap.getShowsByCastMember(castMember);

                if (showsByCast.isEmpty()) 
                {
                    System.out.println("No shows found for cast member: " + castMember);
                } 
                else 
                {
                    System.out.println("\nShows featuring " + castMember + ":\n");
                    for (TVShow show : showsByCast) 
                    {
                        System.out.println(show + "\n");
                    }
                }
            } 
            else if (choice == 3) 
            {
                System.out.print("\nEnter network name: ");
                String networkName = scanner.nextLine();
                List<TVShow> sortedShows = networkMap.getShowsByTimeSlot(networkName);

                if (sortedShows.isEmpty()) 
                {
                    System.out.println("No shows found for network: " + networkName);
                } 
                else 
                {
                    System.out.println("\n" + networkName + " shows sorted by time slot:\n");
                    for (TVShow show : sortedShows) 
                    {
                        System.out.println(show + "\n");
                    }
                }
            }
            else if (choice == 4) 
            {
                System.out.println("\nWhat network is your show in?");
                String userNetwork = scanner.nextLine();
                
                System.out.println("What is the name of the TV Show?");
                String userShow = scanner.nextLine();
            
                boolean isDuplicate = false;
                if (networkMap.getNetwork().containsKey(userNetwork)) 
                {
                    for (TVShow show : networkMap.getNetwork().get(userNetwork)) 
                    {
                        if (show.getTitle().equalsIgnoreCase(userShow)) 
                        {
                            isDuplicate = true;
                            break;
                        }
                    }
                }
            
                try 
                {
                    BufferedReader reader = new BufferedReader(new FileReader("Shows.txt"));
                    reader.readLine();
                    String line;
                    while ((line = reader.readLine()) != null) 
                    {
                        if (line.equalsIgnoreCase(userShow)) 
                        {
                            isDuplicate = true;
                            break;
                        }
                    }
                    reader.close();
                } 
                catch (IOException e) 
                {
                    System.out.println("Error reading file: " + e.getMessage());
                }
            
                if (isDuplicate) 
                {
                    System.out.println("\nThis show is already in the list! No need to add it again.");
                    continue;
                }
            
                System.out.println("Who are the 3 or 4 main cast members? (Separate by commas)");
                String userCast = scanner.nextLine();
                
                System.out.println("What is the average time duration per episode?");
                String userAverageDuration = scanner.nextLine();
            
                TVShow newShow = new TVShow(userShow, networkMap.splitCast(userCast), userAverageDuration);
            
                if (!networkMap.getNetwork().containsKey(userNetwork)) 
                {
                    networkMap.getNetwork().put(userNetwork, new TreeSet<TVShow>());
                }
            
                networkMap.getNetwork().get(userNetwork).add(newShow);
            
                try 
                {
                    List<String> lines = new ArrayList<String>();
                    BufferedReader reader = new BufferedReader(new FileReader("Shows.txt"));
                    int numShows = Integer.parseInt(reader.readLine().trim()) + 1;  
                    lines.add(String.valueOf(numShows));
            
                    String line;
                    while ((line = reader.readLine()) != null) 
                    {
                        lines.add(line);
                    }
                    reader.close();
            
                    BufferedWriter writer = new BufferedWriter(new FileWriter("Shows.txt"));
                    for (String updatedLine : lines) 
                    {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
            
                    writer.write(userNetwork);
                    writer.newLine();
                    writer.write(userShow);
                    writer.newLine();
                    writer.write(userCast);
                    writer.newLine();
                    writer.write(userAverageDuration);
                    writer.newLine();
                    writer.close();
            
                    System.out.println("\nShow successfully added to the list!");
                } 
                catch (IOException e) 
                {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            }            
            else if (choice == 5) 
            {
                System.out.println("Exiting program. Goodbye!");
                break;
            } 
            else 
            {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
        scanner.close();
    }
}