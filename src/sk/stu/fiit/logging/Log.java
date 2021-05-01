/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.logging;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matba
 */
@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log {

    private Date date;
    private LogLevel level;

    private String classpath;
    private String method;
    private String message;

    public enum LogLevel {
        FINE, INFO, WARNING, SEVERE
    }

    public Log() {
    }

    public Log(LogLevel level, String classpath, String message) {
        this.date = new Date();
        this.level = level;
        this.classpath = classpath;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String cclass) {
        this.classpath = cclass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
