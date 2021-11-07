/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DVDLibraryDAO;
import DAO.DVDLibraryDAOException;
import DTO.DVD;
import UI.DVDLibraryView;
import java.util.*;

/**
 * This class is the controller, it controls the menu, as well as
 * implementing different methods for different options.
 * This program allows 5 different features for users to interact with the dvd library
 * @author Yi Yang
 *
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDAO dao;

    public DVDLibraryController(DVDLibraryDAO dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        removeDvd();
                        break;
                    case 4:
                        editDvd(); 
                        break;
                    case 5:
                        searchAndViewDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDAOException e) {
	        view.displayErrorMessage(e.getMessage());
	    }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listDvds() throws DVDLibraryDAOException{
        view.displayListAllDvdsBanner();
        List<DVD> dvdList = dao.getAllDvds(); 
        view.displayDvdList(dvdList);
    }
    
    private void addDvd() throws DVDLibraryDAOException{
        view.displayAddDvdBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd); 
        view.displayAddDvdSuccessBanner();
    }
    
    private void removeDvd() throws DVDLibraryDAOException{
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveDvdSuccessBanner();
    }
    
    private void editDvd() throws DVDLibraryDAOException{
        view.displayEditDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDvd(dvdTitle);
        view.getEditDvdInfo(dvd);
        dao.addDvd(dvdTitle, dvd);
        view.displayEditDvdSuccessBanner();
    }
    
    private void searchAndViewDvd() throws DVDLibraryDAOException{
        view.displaySearchDvdByNameBanner();
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}