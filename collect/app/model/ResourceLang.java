package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ResourceLang")
@Table (name="\"resource_lang\"")
@NamedQueries ({
	 @NamedQuery(name="ResourceLang.findAll", query="SELECT a FROM ResourceLang a")
	,@NamedQuery(name="ResourceLang.findByName", query="SELECT a FROM ResourceLang a WHERE a.name = :name")
	,@NamedQuery(name="ResourceLang.findByNameContaining", query="SELECT a FROM ResourceLang a WHERE a.name like :name")
	,@NamedQuery(name="ResourceLang.findByDescription", query="SELECT a FROM ResourceLang a WHERE a.description = :description")
	,@NamedQuery(name="ResourceLang.findByDescriptionContaining", query="SELECT a FROM ResourceLang a WHERE a.description like :description")
})
public class ResourceLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ResourceLang.findAll";
    public static final String FIND_BY_NAME = "ResourceLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ResourceLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "ResourceLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="ResourceLang.findByDescriptionContaining";
	
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
    @JoinColumn(name="resource_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Resource resourceId;  

    @Column(name="resource_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer resourceId_;

    public ResourceLang() {
    }

    public ResourceLang(
       Integer id,
       String name,
       String description,
       Integer resourceId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       resourceId,
       languageId
	 ,true);
	}
    
	public ResourceLang(
       Integer id,
       String name,
       String description,
       Integer resourceId,
       Integer languageId	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && resourceId!=null) {
          this.resourceId = new Resource();
          this.resourceId.setId(resourceId);
	      setResourceId_ (resourceId);
       }
    }

	public ResourceLang flat() {
	   return new ResourceLang(
          getId(),
          getName(),
          getDescription(),
          getResourceId_(),
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
	
    public Resource getResourceId () {
    	return resourceId;
    }
	
    public void setResourceId (Resource resourceId) {
    	this.resourceId = resourceId;
    }

    public Integer getResourceId_() {
        return resourceId_;
    }
	
    public void setResourceId_ (Integer resourceId) {
        this.resourceId_ =  resourceId;
    }
}
