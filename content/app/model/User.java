package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="User")
@Table (name="\"user\"")
@NamedQueries ({
	 @NamedQuery(name="User.findAll", query="SELECT a FROM User a")
	,@NamedQuery(name="User.findByFirstname", query="SELECT a FROM User a WHERE a.firstname = :firstname")
	,@NamedQuery(name="User.findByFirstnameContaining", query="SELECT a FROM User a WHERE a.firstname like :firstname")
	,@NamedQuery(name="User.findByLastname", query="SELECT a FROM User a WHERE a.lastname = :lastname")
	,@NamedQuery(name="User.findByLastnameContaining", query="SELECT a FROM User a WHERE a.lastname like :lastname")
	,@NamedQuery(name="User.findByEmail", query="SELECT a FROM User a WHERE a.email = :email")
	,@NamedQuery(name="User.findByEmailContaining", query="SELECT a FROM User a WHERE a.email like :email")
	,@NamedQuery(name="User.findByTelephone", query="SELECT a FROM User a WHERE a.telephone = :telephone")
	,@NamedQuery(name="User.findByTelephoneContaining", query="SELECT a FROM User a WHERE a.telephone like :telephone")
	,@NamedQuery(name="User.findByPwdHash", query="SELECT a FROM User a WHERE a.pwdHash = :pwdHash")
	,@NamedQuery(name="User.findByPwdHashContaining", query="SELECT a FROM User a WHERE a.pwdHash like :pwdHash")
	,@NamedQuery(name="User.findByEnabled", query="SELECT a FROM User a WHERE a.enabled = :enabled")
	,@NamedQuery(name="User.findByActivationCode", query="SELECT a FROM User a WHERE a.activationCode = :activationCode")
	,@NamedQuery(name="User.findByActivationCodeContaining", query="SELECT a FROM User a WHERE a.activationCode like :activationCode")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_FIRSTNAME = "User.findByFirstname";
    public static final String FIND_BY_FIRSTNAME_CONTAINING ="User.findByFirstnameContaining";
    public static final String FIND_BY_LASTNAME = "User.findByLastname";
    public static final String FIND_BY_LASTNAME_CONTAINING ="User.findByLastnameContaining";
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_EMAIL_CONTAINING ="User.findByEmailContaining";
    public static final String FIND_BY_TELEPHONE = "User.findByTelephone";
    public static final String FIND_BY_TELEPHONE_CONTAINING ="User.findByTelephoneContaining";
    public static final String FIND_BY_PWDHASH = "User.findByPwdHash";
    public static final String FIND_BY_PWDHASH_CONTAINING ="User.findByPwdHashContaining";
    public static final String FIND_BY_ENABLED = "User.findByEnabled";
    public static final String FIND_BY_ACTIVATIONCODE = "User.findByActivationCode";
    public static final String FIND_BY_ACTIVATIONCODE_CONTAINING ="User.findByActivationCodeContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firstname"  , length=45 , nullable=false , unique=false)
    private String firstname;

    @Column(name="lastname"  , length=45 , nullable=false , unique=false)
    private String lastname;

    @Column(name="email"  , length=45 , nullable=false , unique=false)
    private String email; 

    @Column(name="telephone"  , length=45 , nullable=true , unique=false)
    private String telephone; 

    @Column(name="pwd_hash"  , length=70 , nullable=true , unique=false)
    private String pwdHash; 

    @Column(name="enabled"   , nullable=false , unique=false)
    private Boolean enabled; 

    @Column(name="activation_code"  , length=45 , nullable=true , unique=false)
    private String activationCode;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="country_id", referencedColumnName = "id" , nullable=true , unique=false , insertable=true, updatable=true) 
    private Country countryId;  

    @Column(name="country_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer countryId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="group_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Group groupId;  

    @Column(name="group_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer groupId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="preferred_language_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Language preferredLanguageId;  

    @Column(name="preferred_language_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer preferredLanguageId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="role_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Role roleId;  

    @Column(name="role_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer roleId_;

    @OneToMany (targetEntity=Content.class, fetch=FetchType.LAZY, mappedBy="lockedBy", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Content> contentUserViaLockedBy = new HashSet<Content>();

    @OneToMany (targetEntity=ContentAdmin.class, fetch=FetchType.LAZY, mappedBy="userId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentAdmin> contentAdminUserViaUserId = new HashSet<ContentAdmin>();

    @OneToMany (targetEntity=ContentUser.class, fetch=FetchType.LAZY, mappedBy="userId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentUser> contentUserUserViaUserId = new HashSet<ContentUser>();

    public User() {
    }

    public User(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean enabled,
       Integer preferredLanguageId,
       Integer countryId,
       String activationCode) {
	 this(
       id,
       firstname,
       lastname,
       email,
       telephone,
       pwdHash,
       roleId,
       groupId,
       enabled,
       preferredLanguageId,
       countryId,
       activationCode
	 ,true);
	}
    
	public User(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean enabled,
       Integer preferredLanguageId,
       Integer countryId,
       String activationCode	
    , boolean setRelationship) {
       setId (id);
       setFirstname (firstname);
       setLastname (lastname);
       setEmail (email);
       setTelephone (telephone);
       setPwdHash (pwdHash);
       setEnabled (enabled);
       setActivationCode (activationCode);
       if (setRelationship && countryId!=null) {
          this.countryId = new Country();
          this.countryId.setId(countryId);
	      setCountryId_ (countryId);
       }
       if (setRelationship && groupId!=null) {
          this.groupId = new Group();
          this.groupId.setId(groupId);
	      setGroupId_ (groupId);
       }
       if (setRelationship && preferredLanguageId!=null) {
          this.preferredLanguageId = new Language();
          this.preferredLanguageId.setId(preferredLanguageId);
	      setPreferredLanguageId_ (preferredLanguageId);
       }
       if (setRelationship && roleId!=null) {
          this.roleId = new Role();
          this.roleId.setId(roleId);
	      setRoleId_ (roleId);
       }
    }

	public User flat() {
	   return new User(
          getId(),
          getFirstname(),
          getLastname(),
          getEmail(),
          getTelephone(),
          getPwdHash(),
          getRoleId_(),
          getGroupId_(),
          getEnabled(),
          getPreferredLanguageId_(),
          getCountryId_(),
          getActivationCode()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname (String firstname) {
        this.firstname =  firstname;
    }

    public String getLastname() {
        return lastname;
    }
	
    public void setLastname (String lastname) {
        this.lastname =  lastname;
    }

    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }

    public String getTelephone() {
        return telephone;
    }
	
    public void setTelephone (String telephone) {
        this.telephone =  telephone;
    }

    public String getPwdHash() {
        return pwdHash;
    }
	
    public void setPwdHash (String pwdHash) {
        this.pwdHash =  pwdHash;
    }

    public Boolean getEnabled() {
        return enabled;
    }
	
    public void setEnabled (Boolean enabled) {
        this.enabled =  enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }
	
    public void setActivationCode (String activationCode) {
        this.activationCode =  activationCode;
    }

    public Country getCountryId () {
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;
    }

    public Integer getCountryId_() {
        return countryId_;
    }
	
    public void setCountryId_ (Integer countryId) {
        this.countryId_ =  countryId;
    }
	
    public Group getGroupId () {
    	return groupId;
    }
	
    public void setGroupId (Group groupId) {
    	this.groupId = groupId;
    }

    public Integer getGroupId_() {
        return groupId_;
    }
	
    public void setGroupId_ (Integer groupId) {
        this.groupId_ =  groupId;
    }
	
    public Language getPreferredLanguageId () {
    	return preferredLanguageId;
    }
	
    public void setPreferredLanguageId (Language preferredLanguageId) {
    	this.preferredLanguageId = preferredLanguageId;
    }

    public Integer getPreferredLanguageId_() {
        return preferredLanguageId_;
    }
	
    public void setPreferredLanguageId_ (Integer preferredLanguageId) {
        this.preferredLanguageId_ =  preferredLanguageId;
    }
	
    public Role getRoleId () {
    	return roleId;
    }
	
    public void setRoleId (Role roleId) {
    	this.roleId = roleId;
    }

    public Integer getRoleId_() {
        return roleId_;
    }
	
    public void setRoleId_ (Integer roleId) {
        this.roleId_ =  roleId;
    }

    public Set<Content> getContentUserViaLockedBy() {
        if (contentUserViaLockedBy == null){
            contentUserViaLockedBy = new HashSet<Content>();
        }
        return contentUserViaLockedBy;
    }

    public void setContentUserViaLockedBy (Set<Content> contentUserViaLockedBy) {
        this.contentUserViaLockedBy = contentUserViaLockedBy;
    }	
    
    public void addContentUserViaLockedBy (Content element) {
    	    getContentUserViaLockedBy().add(element);
    }

    public Set<ContentAdmin> getContentAdminUserViaUserId() {
        if (contentAdminUserViaUserId == null){
            contentAdminUserViaUserId = new HashSet<ContentAdmin>();
        }
        return contentAdminUserViaUserId;
    }

    public void setContentAdminUserViaUserId (Set<ContentAdmin> contentAdminUserViaUserId) {
        this.contentAdminUserViaUserId = contentAdminUserViaUserId;
    }	
    
    public void addContentAdminUserViaUserId (ContentAdmin element) {
    	    getContentAdminUserViaUserId().add(element);
    }

    public Set<ContentUser> getContentUserUserViaUserId() {
        if (contentUserUserViaUserId == null){
            contentUserUserViaUserId = new HashSet<ContentUser>();
        }
        return contentUserUserViaUserId;
    }

    public void setContentUserUserViaUserId (Set<ContentUser> contentUserUserViaUserId) {
        this.contentUserUserViaUserId = contentUserUserViaUserId;
    }	
    
    public void addContentUserUserViaUserId (ContentUser element) {
    	    getContentUserUserViaUserId().add(element);
    }

    @PrePersist
    public void prePersist_ () {
    }

    @PreUpdate
    public void preUpdate_ () {
    }
}
