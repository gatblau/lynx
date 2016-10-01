package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name="ContentUser")
@Table (name="\"content_user\"")
@NamedQueries ({
	 @NamedQuery(name="ContentUser.findAll", query="SELECT a FROM ContentUser a")
	,@NamedQuery(name="ContentUser.findByCanRead", query="SELECT a FROM ContentUser a WHERE a.canRead = :canRead")
	,@NamedQuery(name="ContentUser.findByCanWrite", query="SELECT a FROM ContentUser a WHERE a.canWrite = :canWrite")
	,@NamedQuery(name="ContentUser.findByLastRead", query="SELECT a FROM ContentUser a WHERE a.lastRead = :lastRead")
	,@NamedQuery(name="ContentUser.findByLastWrite", query="SELECT a FROM ContentUser a WHERE a.lastWrite = :lastWrite")
})
public class ContentUser implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ContentUser.findAll";
    public static final String FIND_BY_CANREAD = "ContentUser.findByCanRead";
    public static final String FIND_BY_CANWRITE = "ContentUser.findByCanWrite";
    public static final String FIND_BY_LASTREAD = "ContentUser.findByLastRead";
    public static final String FIND_BY_LASTWRITE = "ContentUser.findByLastWrite";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="can_read"   , nullable=false , unique=false)
    private Boolean canRead; 

    @Column(name="can_write"   , nullable=false , unique=false)
    private Boolean canWrite; 

    @Column(name="last_read"   , nullable=true , unique=false)
    private Timestamp lastRead; 

    @Column(name="last_write"   , nullable=true , unique=false)
    private Timestamp lastWrite;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="user_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private User userId;

    @Column(name="user_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer userId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="content_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private Content contentId;

    @Column(name="content_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contentId_;

    public ContentUser() {
    }

    public ContentUser(
       Integer id,
       Integer contentId,
       Integer userId,
       Boolean canRead,
       Boolean canWrite,
       Timestamp lastRead,
       Timestamp lastWrite) {
	 this(
       id,
       contentId,
       userId,
       canRead,
       canWrite,
       lastRead,
       lastWrite
	 ,true);
	}
    
	public ContentUser(
       Integer id,
       Integer contentId,
       Integer userId,
       Boolean canRead,
       Boolean canWrite,
       Timestamp lastRead,
       Timestamp lastWrite	
    , boolean setRelationship) {
       setId (id);
       setCanRead (canRead);
       setCanWrite (canWrite);
       setLastRead (lastRead);
       setLastWrite (lastWrite);
       if (setRelationship && userId!=null) {
          this.userId = new User();
          this.userId.setId(userId);
	      setUserId_ (userId);
       }
       if (setRelationship && contentId!=null) {
          this.contentId = new Content();
          this.contentId.setId(contentId);
	      setContentId_ (contentId);
       }
    }

	public ContentUser flat() {
	   return new ContentUser(
          getId(),
          getContentId_(),
          getUserId_(),
          getCanRead(),
          getCanWrite(),
          getLastRead(),
          getLastWrite()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Boolean getCanRead() {
        return canRead;
    }
	
    public void setCanRead (Boolean canRead) {
        this.canRead =  canRead;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }
	
    public void setCanWrite (Boolean canWrite) {
        this.canWrite =  canWrite;
    }

    public Timestamp getLastRead() {
        return lastRead;
    }
	
    public void setLastRead (Timestamp lastRead) {
        this.lastRead =  lastRead;
    }

    public Timestamp getLastWrite() {
        return lastWrite;
    }
	
    public void setLastWrite (Timestamp lastWrite) {
        this.lastWrite =  lastWrite;
    }

    public User getUserId () {
    	return userId;
    }
	
    public void setUserId (User userId) {
    	this.userId = userId;
    }

    public Integer getUserId_() {
        return userId_;
    }
	
    public void setUserId_ (Integer userId) {
        this.userId_ =  userId;
    }
	
    public Content getContentId () {
    	return contentId;
    }
	
    public void setContentId (Content contentId) {
    	this.contentId = contentId;
    }

    public Integer getContentId_() {
        return contentId_;
    }
	
    public void setContentId_ (Integer contentId) {
        this.contentId_ =  contentId;
    }

    @javax.persistence.PrePersist
    public void prePersist_ () {
    }

    @javax.persistence.PreUpdate
    public void preUpdate_ () {
    }
}
