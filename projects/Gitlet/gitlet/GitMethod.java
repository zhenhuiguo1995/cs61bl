package gitlet;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
    current head -> a reference to the current blob -> a blob object???
    HashTree<> : records the commit
    log message
    shaCode
    currentBranch -> String "master" or created by user ""
     */

    public static String HEAD = null;
    public static String currentBranch = null;
    public static TreeSet<String> staged = new TreeSet<>();
    public static TreeSet<String> untracked = new TreeSet<>();
    public static TreeSet<String> removed = new TreeSet<>();
    public static TreeMap<String, String> allCommits = new TreeMap<>();
    public static TreeMap<String, String> branch = new TreeMap<>();

    public static void checkPreviously() {
        File gitlet = new File(".gielet/");
        if (gitlet.exists()) {
            HEAD = serialRead("head").getShaCode();
            currentBranch = serialRead("head").getBranch();
        }
    }

    public static void checkAfterWards() {
        sWriteSet("staged", staged);
        sWriteSet("removed", removed);
        sWriteMap("allCommits", allCommits);
        sWriteMap("branch", branch);
    }

    public static void serialWrite(String name, Commit com) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(".gielet/commits/" + name + ".ser")
            );
            output.writeObject(com);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Commit serialRead(String name) {
        Commit temp = null;
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(".gitlet/commits/" + name + ".ser"));
            temp = (Commit) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void sWriteSet(String name, TreeSet set) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(".gitlet/staged/" + name + ".ser")
            );
            output.writeObject(set);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static TreeSet sReadSet(String name) {
        TreeSet<String> temp = new TreeSet<>();
        try{
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(".gitlet/" + name + ".ser" )
            );
            temp = (TreeSet) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }


    public static void sWriteMap(String name, TreeMap map) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(".gitlet/" + name + ".ser")
            );
            output.writeObject(map);
            output.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static TreeMap sReadMap(String name) {
        TreeMap<String, String> temp = new TreeMap<>();
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(".gitlet/" + name + ".ser")
            );
            temp = (TreeMap) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static boolean isDiffVersion(File current, String currentName) {
        String curSha = Utils.sha1(Utils.readContents(current));
        Commit head = serialRead("head");
        if (head.getFileID() == null) {
            return true;
        }
        if (head.getFileID().containsKey(currentName)) {
            if (head.getFileID().get(currentName).equals(curSha)) {
                return false;
            }
        }
        return true;
    }

    public static void scan() {
        Commit head = serialRead(HEAD);
        File dir = new File(System.getProperty("user.dir"));
        File[] allFile = dir.listFiles();
        for (File file : allFile) {
            if (file.isDirectory() || file.isHidden() || staged.contains(file.getName())
                    || removed.contains(file.getName())) {
                continue;
            }
            if (head.fileID != null) {
                if (head.fileID.containsKey(file.getName())) {
                    continue;
                }
                if (serialRead(head.former).getFileID() != null) {
                    if (serialRead(head.former).getFileID().containsKey(file.getName())) {
                        continue;
                    }
                }
            }
            untracked.add(file.getName());
        }

    }
    /*
     * Initializes a git system
     * If it already exists: prints out "A git version control
     * system already exists"
     */
    public static void init() {
        File repo = new File(".gitlet");
        boolean check = repo.mkdir();
        if (check) {
            Commit initCommit = new Commit();
            File commits = new File(".gitlet/commits/");
            File blobs = new File(".gitlet/blobs/");
            File stageArea= new File(".gitlet/staged/");
            commits.mkdir();
            blobs.mkdir();
            stageArea.mkdir();
            HEAD = initCommit.getShaCode();
            serialWrite(initCommit.getShaCode(), initCommit);
            serialWrite("head", initCommit);
            allCommits.put("master", HEAD);
            branch.put("master", HEAD);
            sWriteMap("allCommits", allCommits);
            sWriteMap("branch", branch);

        } else {
            System.out.println("A git version control" +
                    "system already exists in the current directory.");
            System.exit(0);
        }
    }

    public static void add(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        File formerStaged = new File(".gitlet/staged/staged.ser");
        if (formerStaged.exists()) {
            staged = sReadSet("staged");
        }
        if (isDiffVersion(file, fileName)) {
            staged.add(fileName);
            try {
                Files.copy(Paths.get(fileName), Paths.get(".gitlet/staged/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sWriteSet("staged", staged);
        if (removed != null) {
            if (removed.contains(fileName)) {
                removed.remove(fileName);
            }
        }
    }

    public static void commit(String message) {
        if (removed.size() == 0 && staged.size() == 0) {
            System.out.println("No changes added to the commit");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        for (String name : staged) {
            String shaCode = Utils.sha1(
                    Utils.readContents(new File(".gitlet/staged/" + name))
            );
            map.put(name, shaCode);
        }
        Commit curr = new Commit(message, map);
        for (String name: staged) {
            try {
                Files.copy(
                        Paths.get(".gitlet/staged/" + name),
                        Paths.get(".gitlet/blobs/" + name + curr.getFileID().get(name))
                );
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                Files.delete(Paths.get(".gitlet/staged/" + name));
            } catch (IOException e) {
                System.out.println(e);
                return;
            }
        }
        curr.setBranch(serialRead("head").getBranch());
        HEAD = curr.getShaCode();
        staged = new TreeSet<>();
        removed = new TreeSet<>();
        allCommits.put(currentBranch, curr.getShaCode());
        branch.put(currentBranch, curr.getShaCode());
        serialWrite(curr.getShaCode(), curr);
        serialWrite("head", curr);
    }

    public static void rm(String fileName) {
        Commit pre = serialRead("head");
        if (staged.contains(fileName) && pre.fileID == null) {
            staged.remove(fileName);
            try {
                Files.delete(Paths.get(".gitlet/staged/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (pre.fileID != null && pre.fileID.containsKey(fileName)) {
            try {
                if (staged.contains(fileName)) {
                    staged.remove(fileName);
                    Files.delete(Paths.get(".gitley/staged/" + fileName));
                }
                removed.add(fileName);
                Files.delete(Paths.get(fileName));
            } catch (IOException e) {
                removed.add(fileName);
            }
        } else if (pre.fileID != null && !pre.fileID.containsKey(fileName)) {
            staged.remove(fileName);
            try {
                Files.delete(Paths.get(".gitlet/staged/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No reason to remove the file");
        }
        return;
    }

    public static void log() {
        Commit pre = serialRead("head");
        String tmp = null;
        while (pre.getFormer() != null) {
            pre.printlog();
            tmp = pre.getFormer();
            pre = serialRead(tmp);
        }
        pre.printlog();
    }

    public static void globalLog() {
        HashSet<String> done = new HashSet<>();
        for (String shatmp: allCommits.values()) {
            if (done.contains(shatmp)) {
                continue;
            }
            Commit curr = serialRead(shatmp);
            String temp = shatmp;
            while (curr.getFormer() != null && !done.contains(temp)) {
                curr.printlog();
                done.add(temp);
                temp =curr.getFormer();
                curr = serialRead(temp);
            }
            if (!done.contains(temp)) {
                curr.printlog();
                done.add(temp);
            }
        }
    }

    public static void find(String args) {
        HashSet<String> done = new HashSet<>();
        int found = 0;
        for (String shatmp: allCommits.values()) {
            if (done.contains(shatmp)) {
                continue;
            }
            Commit curr = serialRead(shatmp);
            String temp = shatmp;
            while (curr.getFormer() != null && !done.contains(temp)) {
                if (args.equals(curr.getDescription())) {
                    System.out.println(curr.getShaCode());
                    found += 1;
                }
                done.add(temp);
                temp = curr.getFormer();
                curr = serialRead(temp);
            }
            if (!done.contains(temp)) {
                if (args.equals(curr.getDescription())) {
                    System.out.println(curr.getShaCode());
                    found += 1;
                }
                done.add(temp);
            }
        }
        if (found == 0) {
            System.out.println("Found no commit with that message.");
        }
    }

    public static void status() {
        System.out.println("=== Branches ===");
        for (String temp : branch.keySet()) {
            if (temp.equals(currentBranch)) {
                System.out.println("*" + temp);
            } else {
                System.out.println(temp);
            }
        }
        System.out.println();
        System.out.println("=== Staged Files ===");
        for (String temp : staged) {
            System.out.println(temp);
        }
        System.out.println();
        System.out.println("=== Removed Files ===");
        for (String temp : removed) {
            System.out.println(temp);
        }
        System.out.println();
        System.out.println("=== Modifications Not Staged For Commit ==-");
        System.out.println();
        System.out.println("=== Untracked Files ===");
        System.out.println();
    }

    public static void branch(String branchName) {
        if (branch.containsKey(branchName)) {
            System.out.println("A branch with that name already exists");
        } else {
            allCommits.put(branchName, HEAD);
            branch.put(branchName, HEAD);
        }
    }

    public static void checkout(String[] args) {
        if (args.length == 3 && args[1].equals("--")) {
            GitMethod.checkout1(args[2], GitMethod.serialRead(GitMethod.HEAD));
        } else if (args.length == 4 && (args[2].equals("--"))) {
            GitMethod.checkout2(args[1], args[2]);
        } else if (args.length == 2) {
            GitMethod.checkout3(args[1]);
        } else {
            System.out.println("Incorrect operands.");
        }
    }

    public static void checkout1(String name, Commit curr) {
        if (curr.fileID.containsKey(name)) {
            try {
                Files.copy(Paths.get(".gitlet/blobs/" + name + curr.fileID.get(name)),
                    Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist in that commit");
        }
    }

    public static void checkout2(String id, String name) {
        HashSet<String> done = new HashSet<>();
        for (String shatmp : allCommits.values()) {
            if (done.contains(shatmp)) {
                continue;
            }
            Commit curr = serialRead(shatmp);
            String temp = shatmp;
            while (curr.getFormer() != null || !done.contains(temp)) {
                if (curr.getShaCode().equals(id) || curr.getShaCode()
                        .substring(0, id.length()).equals(id)) {
                    checkout1(name, curr);
                    return;
                }
                done.add(temp);
                temp = curr.getFormer();
                if (temp == null) {
                    System.out.println("No commits with that id exists.");
                    return;
                }
                curr = serialRead(temp);
            }
            done.add(temp);
        }
        System.out.println("No commits with that id exists");
    }

    public static void checkout3(String branchName) {
        if (!branch.containsKey(branchName)) {
            System.out.println("No such branch exists");
            return;
        }
        if (branchName.equals(currentBranch)) {
            System.out.println("No need to checkout the current branch");
        }
        String sha = branch.get(branchName);
        Commit nHead = serialRead(sha);
        scan();
        if (nHead.fileID != null) {
            for (String name : nHead.fileID.keySet()) {
                if (untracked.contains(name)) {
                    System.out.println("There is an untrakced file in the way" +
                            "delete it or add it.");
                    return;
                }
            }
        }
        Commit oHead = serialRead(HEAD);
        if (oHead.fileID != null) {
            for (String name : oHead.fileID.keySet()) {
                try {
                    Files.delete(Paths.get(name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        nHead.setBranch(branchName);
        serialWrite("head", nHead);
        if (nHead.fileID != null) {
            for (String name : nHead.fileID.keySet()) {
                try {
                    Files.copy(Paths.get(".gitlet/blobs/" + name +
                        nHead.fileID.get(name)),
                            Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        staged = new TreeSet<>();
    }

    public static void rmBranch(String branchName) {
        if (!branch.containsKey(branchName)) {
            System.out.println("A branch with that name does not exist.");
        } else if (currentBranch.equals(branchName)) {
            System.out.println("Cannot remove the current branch");
        } else {
            branch.remove(branchName, HEAD);
        }
    }

    public static void reset(String commitId) {
        Commit found = null;
        String branchName = null;
        HashSet<String> done = new HashSet<>();
        for (String shatmp : allCommits.values()) {
            if (done.contains(shatmp)) {
                continue;
            }
            Commit curr = serialRead(shatmp);
            String temp =  shatmp;
            while (curr.getFormer() != null || !done.contains(temp)) {
                if (curr.getShaCode().equals(commitId) || curr.getShaCode()
                        .substring(0, commitId.length()).equals(commitId)) {
                    found = curr;
                    branchName = found.getBranch();
                    break;
                }
                done.add(temp);
                temp = curr.getFormer();
                if (temp == null) {
                    break;
                }
                curr = serialRead(temp);
            }
            done.add(temp);
        }
        if (found == null) {
            System.out.println("No commit with that id exists");
            return;
        }
        HEAD = found.getShaCode();
        scan();
        for (String name : found.fileID.keySet()) {
            if (untracked.contains(name)) {
                System.out.println("There is an untracked file in the way;" +
                        "delete ir or add it first");
                return;
            }
        }
        staged = new TreeSet<>();
        removed = new TreeSet<>();
        for (String name : found.fileID.keySet()) {
            checkout1(name, found);
            if (!found.fileID.containsKey(name)) {
                try {
                    Files.delete(Paths.get(name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        branch.put(branchName, found.getShaCode());
        serialWrite("head", found);
    }

    public static void merge(String branchName) {
        if (!branch.containsKey(branchName)) {
            System.out.println("A branch with that name does not exist");
            return;
        }
        String splitPointSha = split(branchName);
        if (branch.get(branchName).equals(splitPointSha)) {
            System.out.println("Given branch is an ancestor of the current branch");
            return;
        } else if (branchName.equals(currentBranch)) {
            System.out.println("Cannot merge a branch with itself");
            return;
        } else if (removed.size() != 0 || staged.size() != 0) {
            System.out.println("You have uncommitted changes.");
            return;
        }
        scan();
        String branchSha = allCommits.get(branchName);
        Commit branchCom = serialRead(branchSha);
        String thisSha = HEAD;
        Commit thisCom = serialRead(HEAD);
        String currBranch = currentBranch;
        for (String name : branchCom.fileID.keySet()) {
            if (untracked.contains(name)) {
                System.out.println("There is an untracked file in thw way" +
                        "delete it or add it.");
                return;
            }
        }
        if (thisSha.equals(splitPointSha)) {
            checkout3(branchName);
            serialWrite("head", thisCom);
            HEAD = thisSha;
            currentBranch = currBranch;
            System.out.println("Current branch fast-forward.");
        }
        HashSet<String> difference = theDiff(serialRead(splitPointSha), thisCom, branchCom);
        for (String diff : difference) {
            mergeTheDiff(diff, thisCom, branchCom);
        }
        if (difference.size() > 0) {
            System.out.println("Encountered a merge conflict");
        } else {
            GitMethod.commit("Merged" + currBranch + "with" + branchName + ".");
        }

    }

    public static String split(String a) {
        HashSet<String> done = new HashSet<>();
        String shaa = branch.get(a);
        Commit coma;
        while (shaa != null) {
            coma = serialRead(shaa);
            done.add(shaa);
            shaa = coma.getFormer();
        }
        String shab = branch.get(currentBranch);
        Commit comb;
        while (shab != null) {
            comb = serialRead(shab);
            if (done.contains(shab)) {
                return comb.getShaCode();
            }
            shab = comb.getFormer();
        }
        return null;
    }

    public static HashSet theDiff(Commit splitPoint, Commit thisCom, Commit branchCom) {
        HashSet<String> difference = new HashSet<>();
        for (String name : branchCom.fileID.keySet()) {
            if (!splitPoint.fileID.containsKey(name)) {
                if (!thisCom.fileID.containsKey(name)) {
                    checkout2(branchCom.getShaCode(), name);
                    add(name);
                } else if (thisCom.fileID.containsKey(name)) {
                    difference.add(name);
                }
            } else if (splitPoint.fileID.containsKey(name)) {
                if (!splitPoint.fileID.get(name).equals(branchCom.fileID.get(name))
                        && (!thisCom.fileID.get(name).equals(splitPoint.fileID.get(name)))) {
                    difference.add(name);
                }
            }
        }
        for (String name : thisCom.fileID.keySet()) {
            if (splitPoint.fileID.containsKey(name)) {
                if (!branchCom.fileID.containsKey(name)
                        && splitPoint.fileID.get(name).equals(
                            thisCom.fileID.get(name))) {
                    rm(name);
                } else if (branchCom.fileID.containsKey(name)
                        && !splitPoint.fileID.get(name).equals(
                            branchCom.fileID.get(name))) {
                    difference.add(name);
                } else if (!branchCom.fileID.containsKey(name)
                        && !splitPoint.fileID.get(name).equals(
                                thisCom.fileID.get(name))) {
                    difference.add(name);
                }
            } else if (!splitPoint.fileID.containsKey(name)
                    && branchCom.fileID.containsKey(name)
                    && !branchCom.fileID.get(name).equals(thisCom.fileID.get(name))) {
                difference.add(name);
            }
        }
        return difference;
    }

    public static void mergeTheDiff(String diff, Commit thisCom,
                                    Commit branchCom) {
        File output = new File(diff);
        File currFile = new File(".gitlet/blobs/" + diff + thisCom.fileID.get(diff));
        File givenFile = new File("/gitlet/blobs/" + diff + branchCom.fileID.get(diff));
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] head = "<<<<<<< HEAD\n".getBytes();
            outputStream.write(head);
            if (currFile.exists()) {
                byte[] currToWrite = Utils.readContents(currFile);
                outputStream.write(currToWrite);
            }
            byte[] divide = "=======\n".getBytes();
            outputStream.write(divide);
            if (givenFile.exists()) {
                byte[] givenToWrite = Utils.readContents(givenFile);
                outputStream.write(givenToWrite);
            }
            byte[] end = ">>>>>>>\n".getBytes();
            outputStream.write(end);
            byte[] toWrite = outputStream.toByteArray();
            Utils.writeContents(output, toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
