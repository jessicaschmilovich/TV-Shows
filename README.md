# TV-Shows
TV Shows is a program that categorizes TV shows based on network, main cast, and average episode duration. It allows users to retrieve all shows from a specific network, find shows featuring a particular actor, and sort shows within a network by episode length. Users can also add new shows to the collection, with built-in duplicate detection preventing repeated entries. The program reads from and updates Shows.txt, storing show data in a structured format. It uses a TreeMap to map each network to a TreeSet of shows, ensuring alphabetical order and uniqueness. The menu-driven interface provides options for searching, sorting, and modifying the dataset, making it easy to manage and explore TV show listings.

**Features:**
Search by Network: View all TV shows available on a specific network.
Search by Cast Member: Find all shows featuring a specific actor.
Sort by Episode Duration: Organize shows within a network by their average episode length.
Add New Shows: Users can add shows to the collection, with automatic duplicate detection to maintain unique entries.
Data Management: Reads from and updates Shows.txt, ensuring all modifications persist across sessions.

**Project Structure:**
NetworkMap.java: Manages the collection of TV shows across different networks using a TreeMap, supporting searches, sorting, and adding new shows.
NetworkMapMenu.java: Provides a menu-driven interface for users to interact with the program, allowing them to explore and modify the show database.
TVShow.java: Defines the TVShow class, storing attributes like title, cast list, and average episode duration, with comparison methods for sorting.
Shows.txt: Stores TV show data in a structured format, including network name, show title, cast, and episode duration.
