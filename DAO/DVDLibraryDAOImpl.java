/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.DVD;
import java.util.*;
import java.io.*;
/**
 * This class implements file I/O, including reading and writing data from and to 
 * dvdlibrary.txt file
 * @author Yi Yang
 *
 */
public class DVDLibraryDAOImpl implements DVDLibraryDAO{
    public static final String DVDLIBRARY_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";
    private Map<String, DVD> dvds = new HashMap<>();
    
    private void loadLibrary() throws DVDLibraryDAOException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDAOException("!!! Could not load library data into memory !!!", e);
        } 
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens will hold the different elements of the DVD object
        String[] currentTokens;
        
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            
            DVD currentDvd = new DVD(currentTokens[0]);
            // sets the elements
            currentDvd.setReleaseDate(Integer.parseInt(currentTokens[1]));
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setStudio(currentTokens[3]);
            currentDvd.setDirectorsName((currentTokens[4]));
            currentDvd.setViewerNotes(currentTokens[5]);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeLibrary() throws DVDLibraryDAOException {
        
        PrintWriter out;

        try {
        	// Create PrintWriter for writing the file
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDAOException("Could not save dvd data.", e);
        }
        // Write out DVD info to the library file
        List<DVD> dvdList = this.getAllDvds();
        // iterate through the list and get all the elements
        for (DVD currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER 
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getDirectorsName() + DELIMITER 
                    + currentDvd.getViewerNotes());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    @Override
    public List<DVD> getAllDvds() throws DVDLibraryDAOException {
       loadLibrary();
       return new ArrayList<>(dvds.values());
    }
    
    @Override
    public DVD addDvd(String dvdTitle, DVD dvd) throws DVDLibraryDAOException{
        DVD newDvd = dvds.put(dvdTitle, dvd);
        writeLibrary();
        return newDvd;
    }
    
    @Override
    public DVD removeDvd(String dvdTitle) throws DVDLibraryDAOException {
        DVD removedDvd = dvds.remove(dvdTitle);
        writeLibrary();
        return removedDvd;
    }
    
    @Override
    public DVD getDvd(String dvdTitle) throws DVDLibraryDAOException {
        loadLibrary();
        return dvds.get(dvdTitle);
    }
    
    
    
}