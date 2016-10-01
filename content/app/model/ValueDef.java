package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ValueDef")
@Table (name="\"value_def\"")
@NamedQueries ({
    @NamedQuery(name="ValueDef.findAll", query="SELECT a FROM ValueDef a")
})
public class ValueDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ValueDef.findAll";

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemDef itemDefId;

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="value_type_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private ValueType valueTypeId;

    @Column(name="value_type_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer valueTypeId_;

    @OneToMany (targetEntity=model.Value.class, fetch=FetchType.LAZY, mappedBy="valueDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Value> valueValueDefViaValueDefId = new HashSet<Value>();

    @OneToMany (targetEntity=model.ValueDefLang.class, fetch=FetchType.LAZY, mappedBy="valueDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ValueDefLang> valueDefLangValueDefViaValueDefId = new HashSet<ValueDefLang>();

    public ValueDef() {
    }

    public ValueDef(
            Integer id,
            Integer itemDefId,
            Integer valueTypeId) {
        this(
                id,
                itemDefId,
                valueTypeId
                ,true);
    }

    public ValueDef(
            Integer id,
            Integer itemDefId,
            Integer valueTypeId
            , boolean setRelationship) {
        setId (id);
        if (setRelationship && itemDefId!=null) {
            this.itemDefId = new ItemDef();
            this.itemDefId.setId(itemDefId);
        }
        if (setRelationship && valueTypeId!=null) {
            this.valueTypeId = new ValueType();
            this.valueTypeId.setId(valueTypeId);
        }
    }

    public ValueDef flat() {
        return new ValueDef(
                getId(),
                getItemDefId_(),
                getValueTypeId_()
                , false
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId (Integer id) {
        this.id =  id;
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

    public ValueType getValueTypeId () {
        return valueTypeId;
    }

    public void setValueTypeId (ValueType valueTypeId) {
        this.valueTypeId = valueTypeId;
    }

    public Integer getValueTypeId_() {
        return valueTypeId_;
    }

    public void setValueTypeId_ (Integer valueTypeId) {
        this.valueTypeId_ =  valueTypeId;
    }

    public Set<Value> getValueValueDefViaValueDefId() {
        if (valueValueDefViaValueDefId == null){
            valueValueDefViaValueDefId = new HashSet<Value>();
        }
        return valueValueDefViaValueDefId;
    }

    public void setValueValueDefViaValueDefId (Set<Value> valueValueDefViaValueDefId) {
        this.valueValueDefViaValueDefId = valueValueDefViaValueDefId;
    }

    public void addValueValueDefViaValueDefId (Value element) {
        getValueValueDefViaValueDefId().add(element);
    }

    public Set<ValueDefLang> getValueDefLangValueDefViaValueDefId() {
        if (valueDefLangValueDefViaValueDefId == null){
            valueDefLangValueDefViaValueDefId = new HashSet<ValueDefLang>();
        }
        return valueDefLangValueDefViaValueDefId;
    }

    public void setValueDefLangValueDefViaValueDefId (Set<ValueDefLang> valueDefLangValueDefViaValueDefId) {
        this.valueDefLangValueDefViaValueDefId = valueDefLangValueDefViaValueDefId;
    }

    public void addValueDefLangValueDefViaValueDefId (ValueDefLang element) {
        getValueDefLangValueDefViaValueDefId().add(element);
    }
}