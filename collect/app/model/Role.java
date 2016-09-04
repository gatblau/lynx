package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Role")
@Table (name="\"role\"")
@NamedQueries ({
	 @NamedQuery(name="Role.findAll", query="SELECT a FROM Role a")
})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Role.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="roleId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentRoleViaRoleId = new HashSet<Respondent>(); 

    @OneToMany (targetEntity=model.RoleLang.class, fetch=FetchType.LAZY, mappedBy="roleId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <RoleLang> roleLangRoleViaRoleId = new HashSet<RoleLang>(); 

    public Role() {
    }

    public Role(
       Integer id) {
	 this(
       id
	 ,true);
	}
    
	public Role(
       Integer id	
    , boolean setRelationship) {
       setId (id);
    }

	public Role flat() {
	   return new Role(
          getId()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Set<Respondent> getRespondentRoleViaRoleId() {
        if (respondentRoleViaRoleId == null){
            respondentRoleViaRoleId = new HashSet<Respondent>();
        }
        return respondentRoleViaRoleId;
    }

    public void setRespondentRoleViaRoleId (Set<Respondent> respondentRoleViaRoleId) {
        this.respondentRoleViaRoleId = respondentRoleViaRoleId;
    }	
    
    public void addRespondentRoleViaRoleId (Respondent element) {
    	    getRespondentRoleViaRoleId().add(element);
    }

    public Set<RoleLang> getRoleLangRoleViaRoleId() {
        if (roleLangRoleViaRoleId == null){
            roleLangRoleViaRoleId = new HashSet<RoleLang>();
        }
        return roleLangRoleViaRoleId;
    }

    public void setRoleLangRoleViaRoleId (Set<RoleLang> roleLangRoleViaRoleId) {
        this.roleLangRoleViaRoleId = roleLangRoleViaRoleId;
    }	
    
    public void addRoleLangRoleViaRoleId (RoleLang element) {
    	    getRoleLangRoleViaRoleId().add(element);
    }
}
