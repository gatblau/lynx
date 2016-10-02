package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Resource")
@Table (name="\"resource\"")
@NamedQueries ({
	 @NamedQuery(name="Resource.findAll", query="SELECT a FROM Resource a")
	,@NamedQuery(name="Resource.findByPath", query="SELECT a FROM Resource a WHERE a.path = :path")
	,@NamedQuery(name="Resource.findByPathContaining", query="SELECT a FROM Resource a WHERE a.path like :path")
	,@NamedQuery(name="Resource.findByLink", query="SELECT a FROM Resource a WHERE a.link = :link")
	,@NamedQuery(name="Resource.findByLinkContaining", query="SELECT a FROM Resource a WHERE a.link like :link")
})
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Resource.findAll";
    public static final String FIND_BY_PATH = "Resource.findByPath";
    public static final String FIND_BY_PATH_CONTAINING ="Resource.findByPathContaining";
    public static final String FIND_BY_LINK = "Resource.findByLink";
    public static final String FIND_BY_LINK_CONTAINING ="Resource.findByLinkContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="path"  , length=250 , nullable=false , unique=false)
    private String path; 

    @Column(name="link"  , length=250 , nullable=true , unique=false)
    private String link; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="media_type_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private MediaType mediaTypeId;  

    @Column(name="media_type_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer mediaTypeId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private ItemDef itemDefId;  

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @OneToMany (targetEntity=model.ResourceLang.class, fetch=FetchType.LAZY, mappedBy="resourceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ResourceLang> resourceLangResourceViaResourceId = new HashSet<ResourceLang>(); 

    public Resource() {
    }

    public Resource(
       Integer id,
       Integer mediaTypeId,
       String path,
       String link,
       Integer itemDefId) {
	 this(
       id,
       mediaTypeId,
       path,
       link,
       itemDefId
	 ,true);
	}
    
	public Resource(
       Integer id,
       Integer mediaTypeId,
       String path,
       String link,
       Integer itemDefId	
    , boolean setRelationship) {
       setId (id);
       setPath (path);
       setLink (link);
       if (setRelationship && mediaTypeId!=null) {
          this.mediaTypeId = new MediaType();
          this.mediaTypeId.setId(mediaTypeId);
	      setMediaTypeId_ (mediaTypeId);
       }
       if (setRelationship && itemDefId!=null) {
          this.itemDefId = new ItemDef();
          this.itemDefId.setId(itemDefId);
	      setItemDefId_ (itemDefId);
       }
    }

	public Resource flat() {
	   return new Resource(
          getId(),
          getMediaTypeId_(),
          getPath(),
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

    public String getPath() {
        return path;
    }
	
    public void setPath (String path) {
        this.path =  path;
    }

    public String getLink() {
        return link;
    }
	
    public void setLink (String link) {
        this.link =  link;
    }

    public MediaType getMediaTypeId () {
    	return mediaTypeId;
    }
	
    public void setMediaTypeId (MediaType mediaTypeId) {
    	this.mediaTypeId = mediaTypeId;
    }

    public Integer getMediaTypeId_() {
        return mediaTypeId_;
    }
	
    public void setMediaTypeId_ (Integer mediaTypeId) {
        this.mediaTypeId_ =  mediaTypeId;
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
