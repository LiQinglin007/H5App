package h5app.comxiaomi.h5app.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 9:50<br>
 * UpdateDescription：<br>
 */
@Entity
public class WebUrlBean {

    @Id
    Long WebUrlId;
    String WebUrlTitle;
    String WebUrlContent;
    Long creatTime;
    Boolean defaultUrl;

    @Generated(hash = 2117643379)
    public WebUrlBean(Long WebUrlId, String WebUrlTitle, String WebUrlContent,
            Long creatTime, Boolean defaultUrl) {
        this.WebUrlId = WebUrlId;
        this.WebUrlTitle = WebUrlTitle;
        this.WebUrlContent = WebUrlContent;
        this.creatTime = creatTime;
        this.defaultUrl = defaultUrl;
    }

    @Generated(hash = 1249787967)
    public WebUrlBean() {
    }

    public Long getWebUrlId() {
        return this.WebUrlId;
    }

    public void setWebUrlId(Long WebUrlId) {
        this.WebUrlId = WebUrlId;
    }

    public String getWebUrlTitle() {
        return this.WebUrlTitle;
    }

    public void setWebUrlTitle(String WebUrlTitle) {
        this.WebUrlTitle = WebUrlTitle;
    }

    public String getWebUrlContent() {
        return this.WebUrlContent;
    }

    public void setWebUrlContent(String WebUrlContent) {
        this.WebUrlContent = WebUrlContent;
    }

    public Long getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Boolean getDefaultUrl() {
        return this.defaultUrl;
    }

    public void setDefaultUrl(Boolean defaultUrl) {
        this.defaultUrl = defaultUrl;
    }


}
