package main.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/23 0023.
 * 终端设备手表
 */
@Entity
@EntityListeners(EntityListeners.class)
public class Device extends BaseEntity{

    private String imei;
    private String ecode;
    private String deviceType;
    private String qrCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exFactoryDate;

    private String saleCountry;
    private String saleChannel;
    private  Status status;
    private ActivationStatus activationStatus;
    private String version;
//    private Holder holder;


    private Server server;
    private String imeiKey;
    private String captcha;//四位验证码
//    显示时长:
//    单位：分钟,如果返回验证码，在屏幕显示时长
    private Integer captchaDisplayTime;
    /**
     * 4位验证码生成时间
     */
    private Date captchaBuildTime;
    /**
     * 绑定码
     */
    private String bindCode;
    /**
     * 注册随机码
     */
    private String registCode;
    /**
     * 设备颜色
     */
    private Colour colour;

    private Integer flag;

    private Admin admin;

    public enum Colour{
        blue,
        red,
        orange,
        yellow
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getBindCode() {
        return bindCode;
    }

    public void setBindCode(String bindCode) {
        this.bindCode = bindCode;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public enum Status{
        UN_REGIST{
            public String label(){
                return "未注册";
            }
        },
        REGIST{
            public String label(){
               return "已注册";
            }
        };
        public String label(){
            return this.label();
        }
    }

    public enum ActivationStatus{
        UN_ACTIVATION{
            public String label(){
                return "未激活";
            }
        },
        ACTIVATION{
            public String label(){
                return "已激活";
            }
        };
        public String label(){
            return this.label();
        }

    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Date getExFactoryDate() {
        return exFactoryDate;
    }

    public void setExFactoryDate(Date exFactoryDate) {
        this.exFactoryDate = exFactoryDate;
    }

    public String getSaleCountry() {
        return saleCountry;
    }

    public void setSaleCountry(String saleCountry) {
        this.saleCountry = saleCountry;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getImeiKey() {
        return imeiKey;
    }

    public void setImeiKey(String imeiKey) {
        this.imeiKey = imeiKey;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Integer getCaptchaDisplayTime() {
        return captchaDisplayTime;
    }

    public void setCaptchaDisplayTime(Integer captchaDisplayTime) {
        this.captchaDisplayTime = captchaDisplayTime;
    }

    public Date getCaptchaBuildTime() {
        return captchaBuildTime;
    }

    public void setCaptchaBuildTime(Date captchaBuildTime) {
        this.captchaBuildTime = captchaBuildTime;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    @PrePersist
    public void prePersist(){
        if (this.getStatus()==null)
            this.setStatus(Status.UN_REGIST);
        if (this.getActivationStatus()==null)
            this.setActivationStatus(ActivationStatus.UN_ACTIVATION);
        if (this.getColour()==null)
            this.setColour(Colour.blue);
        if (this.getCreateDate()==null)
            this.setCreateDate(new Date());
        if (this.getModifyDate()==null)
            this.setModifyDate(new Date());
    }
}
