package test.edu.ufp.inf.en.siu.database;

import java.time.LocalDate;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import main.edu.ufp.inf.en.siu.*;

public class DataBaseUsersTest {
    
    @Test
    public void testAddUserAdmin() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        assertTrue(!db.getUserST().isEmpty());
        assertTrue(db.getUserST().size() == 1);
        assertTrue(db.searchUser(admin.getIdNumber()) instanceof Admin);
    }

    @Test
    public void testAddUserBasic() {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        assertTrue(!db.getUserST().isEmpty());
        assertTrue(db.getUserST().size() == 1);
    }

    @Test
    public void testAddUserAdminAndBasic() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic basic = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.addUser(basic);
        assertTrue(!db.getUserST().isEmpty());
        assertTrue(db.getUserST().size() == 2);
    }

    @Test
    public void testRemoveUserAdmin() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.removeUser(admin);
        assertTrue(db.getUserST().isEmpty());
    }

    @Test
    public void testRemoveUserBasic() {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        db.removeUser(basic);
        assertTrue(db.getUserST().isEmpty());
    }

    @Test
    public void testRemoveUserAdminAndBasic() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic basic = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.addUser(basic);
        db.removeUser(admin);
        db.removeUser(basic);
        assertTrue(db.getUserST().isEmpty());
    }

    @Test
    public void testEditUserAdmin() {
        DataBase db = new DataBase();
        Admin oldAdmin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Admin newAdmin = new Admin("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(oldAdmin);
        db.editUser(oldAdmin, newAdmin);
        assertTrue(!db.getUserST().contains(oldAdmin.getIdNumber()));
        assertTrue(db.getUserST().contains(newAdmin.getIdNumber()));
    }

    @Test
    public void testEditUserBasic() {
        DataBase db = new DataBase();
        Basic oldAdmin = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic newAdmin = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(oldAdmin);
        db.editUser(oldAdmin, newAdmin);
        assertTrue(!db.getUserST().contains(oldAdmin.getIdNumber()));
        assertTrue(db.getUserST().contains(newAdmin.getIdNumber()));
    }

    @Test
    public void testEditUserAdminAndBasic() {
        DataBase db = new DataBase();
        Admin old = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        Basic neww = new Basic("Hugo", "Porto", "1234", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(old);
        db.editUser(old, neww);
        assertTrue(!db.getUserST().contains(old.getIdNumber()));
        assertTrue(db.getUserST().contains(neww.getIdNumber()));
    }

    @Test
    public void testSearchUserAdmin() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        assertTrue(admin.equals(db.searchUser(admin.getIdNumber())));
    }

    @Test
    public void testSearchUserBasic() {
        DataBase db = new DataBase();
        Basic basic = new Basic("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(basic);
        assertTrue(basic.equals(db.searchUser(basic.getIdNumber())));
    }

    @Test
    public void testSearchUserAdminAndBasic() {
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
    public void testListUsers() {
        DataBase db = new DataBase();
        Admin admin = new Admin("Vitor", "Porto", "38132", LocalDate.of(1998, 8, 21), "vhugosantos144@gmail.com", "hugo1998");
        db.addUser(admin);
        db.listUsers();
    }



}
