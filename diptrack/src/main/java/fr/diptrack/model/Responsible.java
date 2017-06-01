package fr.diptrack.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Responsible extends Teacher {

	private static final long serialVersionUID = 1303624143421117304L;
	@OneToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.REMOVE }, mappedBy = "responsible")
	private List<Course> listBranchs;

	public Responsible() {
		super();
	}
	
	public Responsible(String mail, String password, String lastName, String firstName) {
		super(mail, password, lastName, firstName);
	}

	public List<Course> getListBranchs() {
		return listBranchs;
	}

	public void setListBranchs(List<Course> listBranchs) {
		this.listBranchs = listBranchs;
	}


}