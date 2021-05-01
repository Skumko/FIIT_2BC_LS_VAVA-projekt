/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.logging;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matba
 */
@XmlRootElement(name = "logs")
@XmlAccessorType(XmlAccessType.FIELD)
public class Logs {

    @XmlElement(name = "log")
    List<Log> logs = null;

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public static void log(Log.LogLevel level, String message, String classpath) {
        //parse logs.xml file to Logs object
        Logs logs = unmarshall();
        //create new Log
        Log newLog = new Log(level, classpath, message);
        //add new Log to existing logs
        logs.getLogs().add(newLog);
        //write logs to logs.xml file
        marshall(logs);
    }

    private static void marshall(Logs logs) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Logs.class
            );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File(Paths.get("logs.xml").toString());

            jaxbMarshaller.marshal(logs, file);
        } catch (JAXBException ex) {
            Logger.getLogger(Logs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private static Logs unmarshall() {
        Logs logs = new Logs();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Logs.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File file = new File("logs.xml");
            if (!file.exists()) {
                logs.setLogs(new ArrayList<>());
                return logs;
            }

            logs = (Logs) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException ex) {
            Logger.getLogger(Logs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return logs;
    }

    public static void main(String[] args) {

        Logs.marshall(
                Logs.unmarshall()
        );
    }
}
