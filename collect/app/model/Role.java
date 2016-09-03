package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Role</p>
 *
 * <p>Description: Domain Object describing a Role entity</p>
 *
 */
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentRoleViaRoleId-field-role@
    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="roleId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentRoleViaRoleId = new HashSet<Respondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @roleLangRoleViaRoleId-field-role@
    @OneToMany (targetEntity=model.RoleLang.class, fetch=FetchType.LAZY, mappedBy="roleId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <RoleLang> roleLangRoleViaRoleId = new HashSet<RoleLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Role() {
    }

	/**
	* All field constructor 
	*/
    public Role(
       Integer id) {
	 this(
       id
	 ,true);
	}
    
	public Role(
       Integer id	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       //parents
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
    


//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentRoleViaRoleId-getter-role@
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
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @roleLangRoleViaRoleId-getter-role@
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
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
