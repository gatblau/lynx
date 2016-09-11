package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="SurveyAdmin")
@Table (name="\"survey_admin\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyAdmin.findAll", query="SELECT a FROM SurveyAdmin a")
})
public class SurveyAdmin implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyAdmin.findAll";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "respondent_id", referencedColumnName = "id", nullable = false, unique = false, insertable = true, updatable = true)
    private Respondent respondentId;

    @Column(name = "respondent_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer respondentId_;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "survey_def_id", referencedColumnName = "id", nullable = false, unique = true, insertable = true, updatable = true)
    private SurveyDef surveyDefId;

    @Column(name = "survey_def_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer surveyDefId_;

    public SurveyAdmin() {
    }

    public SurveyAdmin(
            Integer id,
            Integer respondentId,
            Integer surveyDefId) {
        this(
                id,
                respondentId,
                surveyDefId
                , true);
    }

    public SurveyAdmin(
            Integer id,
            Integer respondentId,
            Integer surveyDefId
            , boolean setRelationship) {
        setId(id);
        if (setRelationship && respondentId != null) {
            this.respondentId = new Respondent();
            this.respondentId.setId(respondentId);
        }
        if (setRelationship && surveyDefId != null) {
            this.surveyDefId = new SurveyDef();
            this.surveyDefId.setId(surveyDefId);
        }
    }

    public SurveyAdmin flat() {
        return new SurveyAdmin(
                getId(),
                getRespondentId_(),
                getSurveyDefId_()
                , false
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Respondent getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(Respondent respondentId) {
        this.respondentId = respondentId;
    }

    public Integer getRespondentId_() {
        return respondentId_;
    }

    public void setRespondentId_(Integer respondentId) {
        this.respondentId_ = respondentId;
    }

    public SurveyDef getSurveyDefId() {
        return surveyDefId;
    }

    public void setSurveyDefId(SurveyDef surveyDefId) {
        this.surveyDefId = surveyDefId;
    }

    public Integer getSurveyDefId_() {
        return surveyDefId_;
    }

    public void setSurveyDefId_(Integer surveyDefId) {
        this.surveyDefId_ = surveyDefId;
    }
}
