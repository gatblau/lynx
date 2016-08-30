package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: RoleLang</p>
 *
 * <p>Description: Domain Object describing a RoleLang entity</p>
 *
 */
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @description-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @description-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-description@
    @Column(name="description"   , nullable=true , unique=false)
    private String description; 
//MP-MANAGED-UPDATABLE-ENDING

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

    /**
    * Default constructor
    */
    public RoleLang() {
    }

	/**
	* All field constructor 
	*/
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
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-description@
    public String getDescription() {
        return description;
    }
	
    public void setDescription (String description) {
        this.description =  description;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


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
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
