import java.util.List;

public class TVShow implements Comparable<TVShow> 
{
    private String title;
    private List<String> cast;
    private double averageDuration;

    public TVShow(String title, List<String> cast, String averageDuration) 
    {
        this.title = title;
        this.cast = cast;
        this.averageDuration = convertToHours(averageDuration);
    }

    public String getTitle() 
    {
        return title;
    }

    public List<String> getCast() 
    {
        return cast;
    }

    public double getAverageDuration() 
    {
        return averageDuration;
    }

    public double convertToHours(String duration) 
    {
        if (duration.contains("hour")) 
        {
            String[] parts = duration.split(" ");
            int hours = Integer.parseInt(parts[0]);
            int minutes = 0;
            if (parts.length > 2) 
            {
                minutes = Integer.parseInt(parts[2]);
            }
            return hours + (minutes / 60.0);
        } 
        if (duration.contains("minutes") || duration.contains("minute")) 
        {
            return Integer.parseInt(duration.split(" ")[0]) / 60.0;
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (!(obj instanceof TVShow)) 
        {
            return false;
        }
        TVShow other = (TVShow) obj;
        return this.title.equals(other.title);
    }

    @Override
    public int hashCode() 
    {
        return title.length();
    }

    @Override
    public int compareTo(TVShow other) 
    {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() 
    {
        return title + " (" + averageDuration + " hours)\nCast: " + String.join(", ", cast);
    }
}