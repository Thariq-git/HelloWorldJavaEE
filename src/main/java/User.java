import java.sql.Date;

public class User {
	
	protected String name;
	protected String password;
	protected String email;
	protected int contact;
	protected Date dob;
	protected String language;
	
	public User(String name, String password, String email, int contact, Date dob, String language) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.dob = dob;
		this.language = language;
	
}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
