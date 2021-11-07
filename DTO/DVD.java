/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
/**
 * This class is the DVD class that contains 
 * getters and setters for each feature of the movie,
 * as well as a constructor to construct a new DVD variable.
 * @author Yi Yang
 *
 */
public class DVD {
    private String title;
    private int releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studio;
    private String viewerNotes;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getViewerNotes() {
        return viewerNotes;
    }

    public void setViewerNotes(String viewerNotes) {
        this.viewerNotes = viewerNotes;
    }    
}
