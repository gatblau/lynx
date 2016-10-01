package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContentUserId implements Serializable {


    @Column(name="content_id"  ,nullable=false)
    private Integer contentId;

	public Integer getContentId() {
        return contentId;
    }
	
    public void setContentId (Integer contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return obj.toString().equals(this.toString());
    }
 
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
 
    @Override
    public String toString() {
        return "ContentUserId:"
        + ":" + contentId
        ;
    }
}
