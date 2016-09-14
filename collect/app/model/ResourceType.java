package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ResourceType")
@Table (name="\"resource_type\"")
@NamedQueries ({
	 @NamedQuery(name="ResourceType.findAll", query="SELECT a FROM ResourceType a")
	,@NamedQuery(name="ResourceType.findByValue", query="SELECT a FROM ResourceType a WHERE a.value = :value")
	,@NamedQuery(name="ResourceType.findByValueContaining", query="SELECT a FROM ResourceType a WHERE a.value like :value")
	,@NamedQuery(name="ResourceType.findByIconPath", query="SELECT a FROM ResourceType a WHERE a.iconPath = :iconPath")
	,@NamedQuery(name="ResourceType.findByIconPathContaining", query="SELECT a FROM ResourceType a WHERE a.iconPath like :iconPath")
})
public class ResourceType implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ResourceType.findAll";
    public static final String FIND_BY_VALUE = "ResourceType.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING = "ResourceType.findByValueContaining";
    public static final String FIND_BY_ICONPATH = "ResourceType.findByIconPath";
    public static final String FIND_BY_ICONPATH_CONTAINING = "ResourceType.findByIconPathContaining";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", length = 45, nullable = false, unique = false)
    private String value;

    @Column(name = "icon_path", length = 250, nullable = true, unique = false)
    private String iconPath;

    @OneToMany(targetEntity = Resource.class, fetch = FetchType.LAZY, mappedBy = "resourceTypeId", cascade = CascadeType.REMOVE)
//, cascade=CascadeType.ALL)
    private Set<Resource> resourceResourceTypeViaResourceTypeId = new HashSet<Resource>();

    public ResourceType() {
    }

    public ResourceType(
            Integer id,
            String value,
            String iconPath) {
        this(
                id,
                value,
                iconPath
                , true);
    }

    public ResourceType(
            Integer id,
            String value,
            String iconPath
            , boolean setRelationship) {
        setId(id);
        setValue(value);
        setIconPath(iconPath);
    }

    public ResourceType flat() {
        return new ResourceType(
                getId(),
                getValue(),
                getIconPath()
                , false
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Set<Resource> getResourceResourceTypeViaResourceTypeId() {
        if (resourceResourceTypeViaResourceTypeId == null) {
            resourceResourceTypeViaResourceTypeId = new HashSet<Resource>();
        }
        return resourceResourceTypeViaResourceTypeId;
    }

    public void setResourceResourceTypeViaResourceTypeId(Set<Resource> resourceResourceTypeViaResourceTypeId) {
        this.resourceResourceTypeViaResourceTypeId = resourceResourceTypeViaResourceTypeId;
    }

    public void addResourceResourceTypeViaResourceTypeId(Resource element) {
        getResourceResourceTypeViaResourceTypeId().add(element);
    }
}
