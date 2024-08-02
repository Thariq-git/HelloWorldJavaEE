/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

/**
 * 
 */
class UserTest {

    private User user;
    private String name = "Neal Caffrey";
    private String password = "FBI";
    private String email = "neal.caffrey@gmail.com";
    private int contact = 91659160;
    private Date dob = Date.valueOf("1977-03-21");
    private String language = "English";
    
	/**
	 * @throws java.lang.Exception
	 */
    @BeforeEach
    void setUp() throws Exception {
        user = new User(name, password, email, contact, dob, language);
    }

	/**
	 * @throws java.lang.Exception
	 */
    @AfterEach
    void tearDown() throws Exception {
        user = null;
    }

	/**
	 * Test method for {@link User#getName()}.
	 */
    @Test
    void testGetName() {
        assertEquals(name, user.getName());
    }

	/**
	 * Test method for {@link User#setName(java.lang.String)}.
	 */
    void testSetName() {
        String newName = "Neal Caffrey";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }

	/**
	 * Test method for {@link User#getPassword()}.
	 */
	@Test
	void testGetPassword() {
		assertEquals(password, user.getPassword());
	}

	/**
	 * Test method for {@link User#setPassword(java.lang.String)}.
	 */
	@Test
	void testSetPassword() {
		String newPassword = "FBI";
		user.setPassword(newPassword);
		assertEquals(newPassword, user.getPassword());
	}

	/**
	 * Test method for {@link User#getEmail()}.
	 */
	@Test
	void testGetEmail() {
		assertEquals(email, user.getEmail());
	}

	/**
	 * Test method for {@link User#setEmail(java.lang.String)}.
	 */
	@Test
	void testSetEmail() {
		String newEmail = "neal.caffrey@gmail.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
	}

	/**
	 * Test method for {@link User#getContact()}.
	 */
	@Test
	void testGetContact() {
		assertEquals(contact, user.getContact());
	}

	/**
	 * Test method for {@link User#setContact(int)}.
	 */
	@Test
	void testSetContact() {
		int newContact = 91659160;
        user.setContact(newContact);
        assertEquals(newContact, user.getContact());
	}

	/**
	 * Test method for {@link User#getDob()}.
	 */
	@Test
	void testGetDob() {
		assertEquals(dob, user.getDob());
	}

	/**
	 * Test method for {@link User#setDob(java.sql.Date)}.
	 */
	@Test
	void testSetDob() {
		Date newDob = Date.valueOf("1977-03-21");
        user.setDob(newDob);
        assertEquals(newDob, user.getDob());
	}

	/**
	 * Test method for {@link User#getLanguage()}.
	 */
	@Test
	void testGetLanguage() {
		assertEquals(language, user.getLanguage());
	}

	/**
	 * Test method for {@link User#setLanguage(java.lang.String)}.
	 */
	@Test
	void testSetLanguage() {
		String newLanguage = "English";
        user.setLanguage(newLanguage);
        assertEquals(newLanguage, user.getLanguage());
	}

}
