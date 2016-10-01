package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Resource")
@Table (name="\"resource\"")
@NamedQueries ({
	 @NamedQuery(name="Resource.findAll", query="SELECT a FROM Resource a")
	,@NamedQuery(name="Resource.findByResourcePath", query="SELECT a FROM Resource a WHERE a.resourcePath = :resourcePath")
	,@NamedQuery(name="Resource.findByResourcePathContaining", query="SELECT a FROM Resource a WHERE a.resourcePath like :resourcePath")
	,@NamedQuery(name="Resource.findByLink", query="SELECT a FROM Resource a WHERE a.link = :link")
	,@NamedQuery(name="Resource.findByLinkContaining", query="SELECT a FROM Resource a WHERE a.link like :link")
})
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Resource.findAll";
    public static final String FIND_BY_RESOURCEPATH = "Resource.findByResourcePath";
    public static final String FIND_BY_RESOURCEPATH_CONTAINING ="Resource.findByResourcePathContaining";
    public static final String FIND_BY_LINK = "Resource.findByLink";
    public static final String FIND_BY_LINK_CONTAINING ="Resource.findByLinkContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="resource_path"  , length=250 , nullable=false , unique=false)
    private String resourcePath; 

    @Column(name="link"  , length=250 , nullable=true , unique=false)
    private String link; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemDef itemDefId;

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="resource_type_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private ResourceType resourceTypeId;  

    @Column(name="resource_type_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer resourceTypeId_;

    @OneToMany (targetEntity=ResourceLang.class, fetch=FetchType.LAZY, mappedBy="resourceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ResourceLang> resourceLangResourceViaResourceId = new HashSet<ResourceLang>(); 

    public Resource() {
    }

    public Resource(
       Integer id,
       Integer resourceTypeId,
       String resourcePath,
       String link,
       Integer itemDefId) {
	 this(
       id,
       resourceTypeId,
       resourcePath,
       link,
       itemDefId
	 ,true);
	}
    
	public Resource(
       Integer id,
       Integer resourceTypeId,
       String resourcePath,
       String link,
       Integer itemDefId
    , boolean setRelationship) {
       setId (id);
       setResourcePath (resourcePath);
       setLink (link);
       if (setRelationship && itemDefId!=null) {
          this.itemDefId = new ItemDef();
          this.itemDefId.setId(itemDefId);
	      setItemDefId_ (itemDefId);
       }
       if (setRelationship && resourceTypeId!=null) {
          this.resourceTypeId = new ResourceType();
          this.resourceTypeId.setId(resourceTypeId);
	      setResourceTypeId_ (resourceTypeId);
       }
    }

	public Resource flat() {
	   return new Resource(
          getId(),
          getResourceTypeId_(),
          getResourcePath(),
          getLink(),
          getItemDefId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getResourcePath() {
        return resourcePath;
    }
	
    public void setResourcePath (String resourcePath) {
        this.resourcePath =  resourcePath;
    }

    public String getLink() {
        return link;
    }
	
    public void setLink (String link) {
        this.link =  link;
    }

    public ItemDef getItemDefId () {
    	return itemDefId;
    }
	
    public void setItemDefId (ItemDef itemDefId) {
    	this.itemDefId = itemDefId;
    }

    public Integer getItemDefId_() {
        return itemDefId_;
    }
	
    public void setItemDefId_ (Integer itemDefId) {
        this.itemDefId_ =  itemDefId;
    }
	
    public ResourceType getResourceTypeId () {
    	return resourceTypeId;
    }
	
    public void setResourceTypeId (ResourceType resourceTypeId) {
    	this.resourceTypeId = resourceTypeId;
    }

    public Integer getResourceTypeId_() {
        return resourceTypeId_;
    }
	
    public void setResourceTypeId_ (Integer resourceTypeId) {
        this.resourceTypeId_ =  resourceTypeId;
    }

    public Set<ResourceLang> getResourceLangResourceViaResourceId() {
        if (resourceLangResourceViaResourceId == null){
            resourceLangResourceViaResourceId = new HashSet<ResourceLang>();
        }
        return resourceLangResourceViaResourceId;
    }

    public void setResourceLangResourceViaResourceId (Set<ResourceLang> resourceLangResourceViaResourceId) {
        this.resourceLangResourceViaResourceId = resourceLangResourceViaResourceId;
    }	
    
    public void addResourceLangResourceViaResourceId (ResourceLang element) {
    	    getResourceLangResourceViaResourceId().add(element);
    }
}
