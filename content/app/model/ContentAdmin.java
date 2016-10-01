package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ContentAdmin")
@Table (name="\"content_admin\"")
@NamedQueries ({
	 @NamedQuery(name="ContentAdmin.findAll", query="SELECT a FROM ContentAdmin a")
})
public class ContentAdmin implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ContentAdmin.findAll";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = false, insertable = true, updatable = true)
    private User userId;

    @Column(name = "user_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer userId_;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "content_def_id", referencedColumnName = "id", nullable = false, unique = true, insertable = true, updatable = true)
    private ContentDef contentDefId;

    @Column(name = "content_def_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer contentDefId_;

    public ContentAdmin() {
    }

    public ContentAdmin(
            Integer id,
            Integer userId,
            Integer contentDefId) {
        this(
                id,
                userId,
                contentDefId
                , true);
    }

    public ContentAdmin(
            Integer id,
            Integer userId,
            Integer contentDefId
            , boolean setRelationship) {
        setId(id);
        if (setRelationship && userId != null) {
            this.userId = new User();
            this.userId.setId(userId);
        }
        if (setRelationship && contentDefId != null) {
            this.contentDefId = new ContentDef();
            this.contentDefId.setId(contentDefId);
        }
    }

    public ContentAdmin flat() {
        return new ContentAdmin(
                getId(),
                getUserId_(),
                getContentDefId_()
                , false
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Integer getUserId_() {
        return userId_;
    }

    public void setUserId_(Integer userId) {
        this.userId_ = userId;
    }

    public ContentDef getContentDefId() {
        return contentDefId;
    }

    public void setContentDefId(ContentDef contentDefId) {
        this.contentDefId = contentDefId;
    }

    public Integer getContentDefId_() {
        return contentDefId_;
    }

    public void setContentDefId_(Integer contentDefId) {
        this.contentDefId_ = contentDefId;
    }
}
