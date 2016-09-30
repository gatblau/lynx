package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Content")
@Table (name="\"content\"")
@NamedQueries ({
	 @NamedQuery(name="Content.findAll", query="SELECT a FROM Content a")
	,@NamedQuery(name="Content.findByUpdated", query="SELECT a FROM Content a WHERE a.updated = :updated")
	,@NamedQuery(name="Content.findByCreated", query="SELECT a FROM Content a WHERE a.created = :created")
	,@NamedQuery(name="Content.findByVersion", query="SELECT a FROM Content a WHERE a.version = :version")
})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
	public static final Integer __DEFAULT_VERSION = Integer.valueOf(1);

    public static final String FIND_ALL = "Content.findAll";
    public static final String FIND_BY_UPDATED = "Content.findByUpdated";
    public static final String FIND_BY_CREATED = "Content.findByCreated";
    public static final String FIND_BY_VERSION = "Content.findByVersion";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="updated"   , nullable=true , unique=false)
    private Timestamp updated; 

    @Column(name="created"   , nullable=false , unique=false)
    private Timestamp created; 

    @Column(name="version"   , nullable=false , unique=false)
    private Integer version;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="locked_by", referencedColumnName = "id" , nullable=true , unique=false , insertable=true, updatable=true) 
    private User lockedBy;

    @Column(name="locked_by"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer lockedBy_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="content_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private ContentDef contentDefId;

    @Column(name="content_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contentDefId_;

    @OneToMany (targetEntity=Section.class, fetch=FetchType.LAZY, mappedBy="contentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Section> sectionContentViaContentId = new HashSet<Section>();

    @OneToMany (targetEntity=ContentLang.class, fetch=FetchType.LAZY, mappedBy="contentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentLang> contentLangContentViaContentId = new HashSet<ContentLang>();

    @OneToMany (targetEntity=ContentUser.class, fetch=FetchType.LAZY, mappedBy="contentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentUser> contentUserContentViaContentId = new HashSet<ContentUser>();

    public Content() {
    }

    public Content(
       Integer id,
       Integer contentDefId,
       Timestamp updated,
       Timestamp created,
       Integer version,
       Integer lockedBy) {
	 this(
       id,
       contentDefId,
       updated,
       created,
       version,
       lockedBy
	 ,true);
	}
    
	public Content(
       Integer id,
       Integer contentDefId,
       Timestamp updated,
       Timestamp created,
       Integer version,
       Integer lockedBy	
    , boolean setRelationship) {
       setId (id);
       setUpdated (updated);
       setCreated (created);
       setVersion (version);
       if (setRelationship && lockedBy!=null) {
          this.lockedBy = new User();
          this.lockedBy.setId(lockedBy);
	      setLockedBy_ (lockedBy);
       }
       if (setRelationship && contentDefId!=null) {
          this.contentDefId = new ContentDef();
          this.contentDefId.setId(contentDefId);
	      setContentDefId_ (contentDefId);
       }
    }

	public Content flat() {
	   return new Content(
          getId(),
          getContentDefId_(),
          getUpdated(),
          getCreated(),
          getVersion(),
          getLockedBy_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Timestamp getUpdated() {
        return updated;
    }
	
    public void setUpdated (Timestamp updated) {
        this.updated =  updated;
    }

    public Timestamp getCreated() {
        return created;
    }
	
    public void setCreated (Timestamp created) {
        this.created =  created;
    }

    public Integer getVersion() {
        return version;
    }
	
    public void setVersion (Integer version) {
        this.version =  version;
    }

    public User getLockedBy () {
    	return lockedBy;
    }
	
    public void setLockedBy (User lockedBy) {
    	this.lockedBy = lockedBy;
    }

    public Integer getLockedBy_() {
        return lockedBy_;
    }
	
    public void setLockedBy_ (Integer lockedBy) {
        this.lockedBy_ =  lockedBy;
    }
	
    public ContentDef getContentDefId () {
    	return contentDefId;
    }
	
    public void setContentDefId (ContentDef contentDefId) {
    	this.contentDefId = contentDefId;
    }

    public Integer getContentDefId_() {
        return contentDefId_;
    }
	
    public void setContentDefId_ (Integer contentDefId) {
        this.contentDefId_ =  contentDefId;
    }

    public Set<Section> getSectionContentViaContentId() {
        if (sectionContentViaContentId == null){
            sectionContentViaContentId = new HashSet<Section>();
        }
        return sectionContentViaContentId;
    }

    public void setSectionContentViaContentId (Set<Section> sectionContentViaContentId) {
        this.sectionContentViaContentId = sectionContentViaContentId;
    }	
    
    public void addSectionContentViaContentId (Section element) {
    	    getSectionContentViaContentId().add(element);
    }

    public Set<ContentLang> getContentLangContentViaContentId() {
        if (contentLangContentViaContentId == null){
            contentLangContentViaContentId = new HashSet<ContentLang>();
        }
        return contentLangContentViaContentId;
    }

    public void setContentLangContentViaContentId (Set<ContentLang> contentLangContentViaContentId) {
        this.contentLangContentViaContentId = contentLangContentViaContentId;
    }	
    
    public void addContentLangContentViaContentId (ContentLang element) {
    	    getContentLangContentViaContentId().add(element);
    }

    public Set<ContentUser> getContentUserContentViaContentId() {
        if (contentUserContentViaContentId == null){
            contentUserContentViaContentId = new HashSet<ContentUser>();
        }
        return contentUserContentViaContentId;
    }

    public void setContentUserContentViaContentId (Set<ContentUser> contentUserContentViaContentId) {
        this.contentUserContentViaContentId = contentUserContentViaContentId;
    }	
    
    public void addContentUserContentViaContentId (ContentUser element) {
    	    getContentUserContentViaContentId().add(element);
    }

    @PrePersist
    public void prePersist_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }

    @PreUpdate
    public void preUpdate_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }
}
