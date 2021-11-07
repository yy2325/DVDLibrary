/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary;

import controller.DVDLibraryController;
import DAO.DVDLibraryDAOImpl;
import DAO.DVDLibraryDAO;
import UI.DVDLibraryView;
import UI.UserIOConsoleImpl;
import UI.UserIO;

/**
 * main method that runs the application
 * @author Yi Yang
 *
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDAO myDao = new DVDLibraryDAOImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
    
}