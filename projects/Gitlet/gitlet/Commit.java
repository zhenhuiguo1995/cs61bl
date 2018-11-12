package gitlet;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The Commit class. For each commit, it should have the following attributes
 * String shaCode
 * String timeStamp
 * String message recording the user
 * String fileName
 * A reference to the blob
 * a reference to the parent commit
 * String former: records the previous commit
 * HashMap<></> fileID -> mapping each file to a corresponding ID
 * String branch -> current branch
 * HashSet<></> removed-> removed file and its ID
 */
public class Commit implements Serializable {
    public String shaCode;
    public String timeStamp;
    public String fileName;
    public String former;
    public String description;
    public String branch;
    public HashMap<String, String> fileID;
    public HashSet<String> removedFile;

    public Commit() {
        this.description = "initial commit";
        this.timeStamp = getTimeStamp();
        this.former = null;
        this.fileID = null;
        this.shaCode = Utils.sha1(description, timeStamp);
        this.branch = "master";
    }

    public Commit(String regDes, HashMap<String, String> files) {
        this.description = regDes;
        this.timeStamp = getTimeStamp();
        //this.former = GitMethod;
        this.fileID = files;
        //String allStaff = ;
        this.shaCode = Utils.sha1()

    }

    public String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String strChanging(HashMap file, String a, String b, String c) {

    }

    public String getShaCode() {
        return shaCode;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }
}