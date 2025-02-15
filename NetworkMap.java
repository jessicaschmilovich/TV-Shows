import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class NetworkMap 
{
    private Map<String, Set<TVShow>> network;

    public NetworkMap() 
    {
        network = new TreeMap<String, Set<TVShow>>();
    }

    public Map<String, Set<TVShow>> getNetwork() 
    {
        return network;
    }

    public void populateFromFile(String filename) throws IOException 
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int numEntries = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < numEntries; i++) 
        {
            String networkName = reader.readLine().trim();
            String title = reader.readLine().trim();
            List<String> cast = splitCast(reader.readLine().trim());
            String averageDuration = reader.readLine().trim();
            TVShow show = new TVShow(title, cast, averageDuration);
            
            if (!network.containsKey(networkName)) 
            {
                network.put(networkName, new TreeSet<TVShow>());
            }
            network.get(networkName).add(show);
        }
        reader.close();
    }

    public List<String> splitCast(String castLine) 
    {
        List<String> cast = new ArrayList<String>();
        String[] castArray = castLine.split(", ");

        for (int i = 0; i < castArray.length; i++) 
        {
            cast.add(castArray[i]);
        }
        return cast;
    }

    public List<TVShow> getShowsByCastMember(String castMember) 
    {
        List<TVShow> result = new ArrayList<TVShow>();

        for (Map.Entry<String, Set<TVShow>> entry : network.entrySet()) 
        {
            for (TVShow show : entry.getValue()) 
            {
                if (show.getCast().contains(castMember)) 
                {
                    result.add(show);
                }
            }
        }
        return result;
    }

    public List<TVShow> getShowsByTimeSlot(String networkName) 
    {
        if (!network.containsKey(networkName)) 
        {
            return new ArrayList<TVShow>();
        }
        List<TVShow> shows = new ArrayList<TVShow>(network.get(networkName));

        shows.sort(new Comparator<TVShow>()
        {
            public int compare(TVShow o1, TVShow o2) 
            {
                return Double.compare(o1.getAverageDuration(), o2.getAverageDuration());
            }
        });
        return shows;
    }

    public void printAllShows() 
    {
        for (Map.Entry<String, Set<TVShow>> entry : network.entrySet()) 
        {
            System.out.println("Network: " + entry.getKey() + "\n");

            for (TVShow show : entry.getValue()) 
            {
                System.out.println(show + "\n");
            }
        }
    }
}