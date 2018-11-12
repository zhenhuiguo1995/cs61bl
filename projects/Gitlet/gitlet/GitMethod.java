package gitlet;

import java.io.File;
import java.io.Serializable;

/*
    GitMethod class has all the functionality
    that a git systems is required to have
    @author: Alfred
 */
public class GitMethod implements Serializable{
    /**
     * what attributes are needed in the gitMethod
     */
    /*
    present working directory -> String folder
    Staging directory: -> stageFolder
    current head
    HashTree<> : records the commit
    log message
    shaCode
     */




    /*
     * Initializes a git system
     * If it already exists: prints out "A git version control
     * system already exists"
     */
    public static void init() {
        File repo = new File(".gitlet");
        if (repo.exists()) {
            System.out.println("A git version control system already exists");
        } else {
            /*
            It seems more need to be done here!
             */
            repo.mkdir();
        }
        System.exit(0);
    }

    public static void add(String fileName) {
        return;
    }

    public static void commit(String message) {
        return;
    }

    public static void rm(String fileName) {
        return;
    }

    public static void log() {
        return;
    }

    public static void globalLog() {
        return;
    }

    public static void find() {
        return;
    }

    public static void status() {
        return;
    }

    public static void checkout(String[] args) {
        return;
    }

    public static void branch(String branchName) {
        return;
    }

    public static void merge(String arg) {
        return;
    }

    public static void rmBranch(String branchName) {
    }

    public static void reset(String commitId) {
    }
}
