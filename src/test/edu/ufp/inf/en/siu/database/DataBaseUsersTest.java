package test.edu.ufp.inf.en.siu.database;

import java.time.LocalDate;

import org.junit.Test;

import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.user.Admin;
import main.edu.ufp.inf.en.models.siu.user.Basic;
import main.edu.ufp.inf.en.models.siu.user.UserAlreadyExistsException;
import main.edu.ufp.inf.en.models.siu.user.UserNotFoundException;

import static org.junit.Assert.assertTrue;

public class DataBaseUsersTest {
    
    @Test
    public void testAddUserAdmin() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        assertTrue(db.numberOfUsers() == 1);
        assertTrue(db.searchUser(admin.getIdNumber()) instanceof Admin);
    }

    @Test
    public void testAddUserBasic() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        assertTrue(db.numberOfUsers() == 1);
    }

    @Test
    public void testAddUserAdminAndBasic() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic basic = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.addUser(basic);
        assertTrue(db.numberOfUsers() == 2);
    }

    @Test
    public void testRemoveUserAdmin() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.removeUser(admin);
        assertTrue(db.numberOfUsers() == 0);
    }

    @Test
    public void testRemoveUserBasic() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        db.removeUser(basic);
        assertTrue(db.numberOfUsers() == 0);
    }

    @Test
    public void testRemoveUserAdminAndBasic() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic basic = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.addUser(basic);
        db.removeUser(admin);
        db.removeUser(basic);
        assertTrue(db.numberOfUsers() == 0);
    }

    @Test
    public void testEditUserAdmin() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin oldAdmin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Admin newAdmin = new Admin("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(oldAdmin);
        //db.editUser(oldAdmin, newAdmin);
        assertTrue(!db.containsUser(oldAdmin.getIdNumber()));
        assertTrue(db.containsUser(newAdmin.getIdNumber()));
    }

    @Test
    public void testEditUserBasic() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Basic oldAdmin = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic newAdmin = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(oldAdmin);
        //db.editUser(oldAdmin, newAdmin);
        assertTrue(!db.containsUser(oldAdmin.getIdNumber()));
        assertTrue(db.containsUser(newAdmin.getIdNumber()));
    }

    @Test
    public void testEditUserAdminAndBasic() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin old = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic neww = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(old);
        //db.editUser(old, neww);
        assertTrue(!db.containsUser(old.getIdNumber()));
        assertTrue(db.containsUser(neww.getIdNumber()));
    }

    @Test
    public void testSearchUserAdmin() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        assertTrue(admin.equals(db.searchUser(admin.getIdNumber())));
    }

    @Test
    public void testSearchUserBasic() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        assertTrue(basic.equals(db.searchUser(basic.getIdNumber())));
    }

    @Test
    public void testSearchUserAdminAndBasic() throws UserNotFoundException, UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic basic = new Basic("Vitor", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.addUser(basic);
        assertTrue(admin.equals(db.searchUser(admin.getIdNumber())));
        assertTrue(basic.equals(db.searchUser(basic.getIdNumber())));
        assertTrue(!basic.equals(db.searchUser(admin.getIdNumber())));
    }

    @Test
    public void testListUsers() throws UserAlreadyExistsException {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.listUsers();
    }



}
