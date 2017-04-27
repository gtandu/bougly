package fr.bougly.web.dtos;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.security.core.GrantedAuthority;

import fr.bougly.model.UserAccount;
import fr.bougly.model.enumeration.RoleAccountEnum;
import fr.bougly.model.Student;

public class AccountDto {
	
	private String mail;
	private String password;
	private String role;
	private String lastName;
	private String firstName;
	private String studentNumber;
	
	public AccountDto() {
		super();
	}
	
	public AccountDto(UserAccount account) {
		this.mail = account.getMail();
		this.lastName = account.getLastName();
		this.firstName = account.getFirstName();
		for(GrantedAuthority role : account.getAuthorities())
		{
			RoleAccountEnum roleEnumObject = RoleAccountEnum.valueOf(role.getAuthority());
			this.role = roleEnumObject.getRole();
		}
		if(account instanceof Student)
		{
			Student compteEtudiant = (Student) account;
			this.studentNumber = compteEtudiant.getStudentNumber();
		}
		
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		lastName = StringUtils.lowerCase(lastName);
		this.lastName = WordUtils.capitalizeFully(lastName);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		firstName = StringUtils.lowerCase(firstName);
		this.firstName = WordUtils.capitalizeFully(firstName);;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDto other = (AccountDto) obj;
		if (mail == null) {
			if (other.getMail() != null)
				return false;
		} else if (!mail.equals(other.getMail()))
			return false;
		return true;
	}
	
	

}