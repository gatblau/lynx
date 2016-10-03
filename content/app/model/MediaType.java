package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="MediaType")
@Table (name="\"media_type\"")
@NamedQueries ({
	 @NamedQuery(name="MediaType.findAll", query="SELECT a FROM MediaType a")
	,@NamedQuery(name="MediaType.findByName", query="SELECT a FROM MediaType a WHERE a.name = :name")
	,@NamedQuery(name="MediaType.findByNameContaining", query="SELECT a FROM MediaType a WHERE a.name like :name")
	,@NamedQuery(name="MediaType.findByTemplate", query="SELECT a FROM MediaType a WHERE a.template = :template")
	,@NamedQuery(name="MediaType.findByTemplateContaining", query="SELECT a FROM MediaType a WHERE a.template like :template")
	,@NamedQuery(name="MediaType.findByReference", query="SELECT a FROM MediaType a WHERE a.reference = :reference")
	,@NamedQuery(name="MediaType.findByReferenceContaining", query="SELECT a FROM MediaType a WHERE a.reference like :reference")
	,@NamedQuery(name="MediaType.findByIconPath", query="SELECT a FROM MediaType a WHERE a.iconPath = :iconPath")
	,@NamedQuery(name="MediaType.findByIconPathContaining", query="SELECT a FROM MediaType a WHERE a.iconPath like :iconPath")
})
public class MediaType implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "MediaType.findAll";
    public static final String FIND_BY_NAME = "MediaType.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="MediaType.findByNameContaining";
    public static final String FIND_BY_TEMPLATE = "MediaType.findByTemplate";
    public static final String FIND_BY_TEMPLATE_CONTAINING ="MediaType.findByTemplateContaining";
    public static final String FIND_BY_REFERENCE = "MediaType.findByReference";
    public static final String FIND_BY_REFERENCE_CONTAINING ="MediaType.findByReferenceContaining";
    public static final String FIND_BY_ICONPATH = "MediaType.findByIconPath";
    public static final String FIND_BY_ICONPATH_CONTAINING ="MediaType.findByIconPathContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=150 , nullable=false , unique=false)
    private String name; 

    @Column(name="template"  , length=150 , nullable=true , unique=false)
    private String template; 

    @Column(name="reference"  , length=250 , nullable=true , unique=false)
    private String reference; 

    @Column(name="icon_path"  , length=250 , nullable=true , unique=false)
    private String iconPath; 

    @OneToMany (targetEntity=model.Resource.class, fetch=FetchType.LAZY, mappedBy="mediaTypeId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Resource> resourceMediaTypeViaMediaTypeId = new HashSet<Resource>(); 

    public MediaType() {
    }

    public MediaType(
       Integer id,
       String name,
       String template,
       String reference,
       String iconPath) {
	 this(
       id,
       name,
       template,
       reference,
       iconPath
	 ,true);
	}
    
	public MediaType(
       Integer id,
       String name,
       String template,
       String reference,
       String iconPath	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setTemplate (template);
       setReference (reference);
       setIconPath (iconPath);
    }

	public MediaType flat() {
	   return new MediaType(
          getId(),
          getName(),
          getTemplate(),
          getReference(),
          getIconPath()
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

    public String getTemplate() {
        return template;
    }
	
    public void setTemplate (String template) {
        this.template =  template;
    }

    public String getReference() {
        return reference;
    }
	
    public void setReference (String reference) {
        this.reference =  reference;
    }

    public String getIconPath() {
        return iconPath;
    }
	
    public void setIconPath (String iconPath) {
        this.iconPath =  iconPath;
    }

    public Set<Resource> getResourceMediaTypeViaMediaTypeId() {
        if (resourceMediaTypeViaMediaTypeId == null){
            resourceMediaTypeViaMediaTypeId = new HashSet<Resource>();
        }
        return resourceMediaTypeViaMediaTypeId;
    }

    public void setResourceMediaTypeViaMediaTypeId (Set<Resource> resourceMediaTypeViaMediaTypeId) {
        this.resourceMediaTypeViaMediaTypeId = resourceMediaTypeViaMediaTypeId;
    }	
    
    public void addResourceMediaTypeViaMediaTypeId (Resource element) {
    	    getResourceMediaTypeViaMediaTypeId().add(element);
    }
}
