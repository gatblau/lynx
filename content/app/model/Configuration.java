package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Configuration")
@Table(name="\"configuration\"")
@NamedQueries({
	 @NamedQuery(name="Configuration.findAll", query="SELECT a FROM Configuration a")
	,@NamedQuery(name="Configuration.findByKey", query="SELECT a FROM Configuration a WHERE a.key = :key")
	,@NamedQuery(name="Configuration.findByKeyContaining", query="SELECT a FROM Configuration a WHERE a.key like :key")
	,@NamedQuery(name="Configuration.findByValue", query="SELECT a FROM Configuration a WHERE a.value = :value")
	,@NamedQuery(name="Configuration.findByValueContaining", query="SELECT a FROM Configuration a WHERE a.value like :value")
})
public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Configuration.findAll";
    public static final String FIND_BY_KEY = "Configuration.findByKey";
    public static final String FIND_BY_KEY_CONTAINING ="Configuration.findByKeyContaining";
    public static final String FIND_BY_VALUE = "Configuration.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="Configuration.findByValueContaining";
	
    @Id
    @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="key"  , length=45 , nullable=false , unique=false)
    private String key; 

    @Column(name="value"  , length=300 , nullable=false , unique=false)
    private String value; 

    public Configuration() {
    }

    public Configuration(
       Integer id,
       String key,
       String value) {
	 this(
       id,
       key,
       value
	 ,true);
	}
    
	public Configuration(
       Integer id,
       String key,
       String value	
    , boolean setRelationship) {
       setId (id);
       setKey (key);
       setValue (value);
    }

	public Configuration flat() {
	   return new Configuration(
          getId(),
          getKey(),
          getValue()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getKey() {
        return key;
    }
	
    public void setKey (String key) {
        this.key =  key;
    }

    public String getValue() {
        return value;
    }
	
    public void setValue (String value) {
        this.value =  value;
    }
}
