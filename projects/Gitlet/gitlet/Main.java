package gitlet;

import java.io.File;

/* Driver class for Gitlet, the tiny stupid version-control system.
   @author Alfred
   References: https://github.com/lifesaver0129/CS61BL-Data-Structure/tree/master/proj2/gitlet
*/
public class Main {
    public static void main(String... args) {
        /*
        1. If a user doesn’t input any arguments, print the message Please enter a command. and exit.
        2. If a user inputs a command that doesn’t exist,
            print the message No command with that name exists. and exit.
        3. If a user inputs a command with the wrong number or format of operands,
        print the message Incorrect operands. and exit.
        4. If a user inputs a command that requires being in an initialized gitlet working directory
        (i.e., one containing a .gitlet subdirectory), but is not in such a directory,
        print the message Not in an initialized gitlet directory.
         */
        if (args.length == 0) {
            System.out.println("Please enter a command");
            System.exit(0);
        } else{
            GitMethod.checkPreviously();
            switch (args[0]){
                case "init":
                    if (args.length == 1) {
                        GitMethod.init();
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "add":
                    File repo = new File(".gitlet");
                    if (!repo.exists()) {
                        System.out.println("Not in an initialized gitlet directory.");
                        System.exit(0);
                    }
                    if (args.length == 2) {
                        GitMethod.add(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "commit":
                    if (args.length == 2) {
                        GitMethod.commit(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "rm":
                    if (args.length == 2) {
                        GitMethod.rm(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "log":
                    if (args.length == 1){
                        GitMethod.log();
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "global-log":
                    if (args.length == 1) {
                        GitMethod.globalLog();
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "find":
                    if (args.length == 2) {
                        GitMethod.find(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "status":
                    if (args.length == 1) {
                        GitMethod.status();
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "checkout":
                    if (args.length == 1 || args.length == 2) {
                        GitMethod.checkout(args);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "branch":
                    if (args.length == 2) {
                        GitMethod.branch(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                case "rm-branch":
                    if (args.length == 2) {
                        GitMethod.rmBranch(args[1]);
                    }
                    break;
                case  "reset":
                    if (args.length == 2) {
                        GitMethod.reset(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                case "merge":
                    if (args.length == 2) {
                        GitMethod.merge(args[1]);
                    } else {
                        System.out.println("Incorrect operands");
                    }
                    break;
                default:
                    System.out.println("No command with that name exists");
                    System.exit(0);
            }
        }
        GitMethod.checkAfterWards();
    }

}
