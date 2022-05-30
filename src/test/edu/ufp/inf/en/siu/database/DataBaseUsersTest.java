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
    public void testEditUserAdmin() throws UserAlreadyExistsException, UserNotFoundException {
        DataBase db = new DataBase();
        Admin user = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(user);
        String name = "new";
        String addr = "newaddr";
        LocalDate birth = LocalDate.of(1999, 4, 2);
        
        db.editUser(user.getIdNumber(), name, addr, birth);
        assertTrue(user.getName() == name);
        assertTrue(user.getAddress() == addr);
        assertTrue(user.getBirth() == birth);    
    }

    @Test
    public void testEditUserBasic() throws UserAlreadyExistsException, UserNotFoundException {
        DataBase db = new DataBase();
        Basic user = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(user);
        String name = "new";
        String addr = "newaddr";
        LocalDate birth = LocalDate.of(1999, 4, 2);
        
        db.editUser(user.getIdNumber(), name, addr, birth);
        assertTrue(user.getName() == name);
        assertTrue(user.getAddress() == addr);
        assertTrue(user.getBirth() == birth);
    }

    @Test
    public void testEditUserAdminAndBasic() throws UserAlreadyExistsException, UserNotFoundException {
        DataBase db = new DataBase();
        Admin old = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic neww = new Basic("Hugo", "Lisboa", "1234", LocalDate.of(1998, 9, 12), "asdadasd@gmail.com", "sddadwdwe");
        db.addUser(old);
        db.editUser(old.getIdNumber(), neww.getName(), neww.getAddress(), neww.getBirth());
        assertTrue(old.getName() == neww.getName());
        assertTrue(old.getAddress() == neww.getAddress());
        assertTrue(old.getBirth() == neww.getBirth());
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
