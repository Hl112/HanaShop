/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.log;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author HL
 */
public class LogDTO implements Serializable{
    private int logId;
    private int productId;
    private String userId;
    private Date updateTime;
    private String command;

    public LogDTO() {
    }

    public LogDTO(int productId, String userId, String command) {
        this.productId = productId;
        this.userId = userId;
        this.command = command;
    }
    

    public LogDTO(int logId, int productId, String userId, Date updateTime, String command) {
        this.logId = logId;
        this.productId = productId;
        this.userId = userId;
        this.updateTime = updateTime;
        this.command = command;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    
    
    
}
