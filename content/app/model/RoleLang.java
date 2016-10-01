package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="RoleLang")
@Table (name="\"role_lang\"")
@NamedQueries ({
	 @NamedQuery(name="RoleLang.findAll", query="SELECT a FROM RoleLang a")
	,@NamedQuery(name="RoleLang.findByName", query="SELECT a FROM RoleLang a WHERE a.name = :name")
	,@NamedQuery(name="RoleLang.findByNameContaining", query="SELECT a FROM RoleLang a WHERE a.name like :name")
	,@NamedQuery(name="RoleLang.findByDescription", query="SELECT a FROM RoleLang a WHERE a.description = :description")
	,@NamedQuery(name="RoleLang.findByDescriptionContaining", query="SELECT a FROM RoleLang a WHERE a.description like :description")
})
public class RoleLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "RoleLang.findAll";
    public static final String FIND_BY_NAME = "RoleLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="RoleLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "RoleLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="RoleLang.findByDescriptionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="role_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Role roleId;  

    @Column(name="role_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer roleId_;

    public RoleLang() {
    }

    public RoleLang(
       Integer id,
       String name,
       String description,
       Integer roleId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       roleId,
       languageId
	 ,true);
	}
    
	public RoleLang(
       Integer id,
       String name,
       String description,
       Integer roleId,
       Integer languageId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setDescription (description);
       //parents
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && roleId!=null) {
          this.roleId = new Role();
          this.roleId.setId(roleId);
	      setRoleId_ (roleId);
       }
    }

	public RoleLang flat() {
	   return new RoleLang(
          getId(),
          getName(),
          getDescription(),
          getRoleId_(),
          getLanguageId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }

    public String getDescription() {
        return description;
    }
	
    public void setDescription (String description) {
        this.description =  description;
    }

    public Language getLanguageId () {
    	return languageId;
    }
	
    public void setLanguageId (Language languageId) {
    	this.languageId = languageId;
    }

    public Integer getLanguageId_() {
        return languageId_;
    }
	
    public void setLanguageId_ (Integer languageId) {
        this.languageId_ =  languageId;
    }
	
    public Role getRoleId () {
    	return roleId;
    }
	
    public void setRoleId (Role roleId) {
    	this.roleId = roleId;
    }

    public Integer getRoleId_() {
        return roleId_;
    }
	
    public void setRoleId_ (Integer roleId) {
        this.roleId_ =  roleId;
    }
}
