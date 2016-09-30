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

    @OneToMany (targetEntity=model.User.class, fetch=FetchType.LAZY, mappedBy="roleId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <User> userRoleViaRoleId = new HashSet<User>();

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

    public Set<User> getUserRoleViaRoleId() {
        if (userRoleViaRoleId == null){
            userRoleViaRoleId = new HashSet<User>();
        }
        return userRoleViaRoleId;
    }

    public void setUserRoleViaRoleId (Set<User> userRoleViaRoleId) {
        this.userRoleViaRoleId = userRoleViaRoleId;
    }	
    
    public void addUserRoleViaRoleId (User element) {
    	    getUserRoleViaRoleId().add(element);
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
