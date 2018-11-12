package gitlet;

import java.io.File;

/**
 * The Blob class represents the file object
 */
public class Blob {
    /**
     * Contains the following attributes.
     * filename, filepath, content, hashcode
     * Each attribute also has a corresponding get method
     */
    private File file;
    private String fileName;
    private String filePath;
    private byte[] content;
    private String shaCode;

    public void Blob(String fileName) {
        this.fileName = fileName;
        this.file = new File(this.fileName);
        this.filePath = System.getProperty("user.dir") + "/" + fileName;
        this.content = Utils.readContents(this.file);
        this.shaCode = Utils.sha1(this.content);
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return this.file;
    }

    public String getFilePath() {
        return filePath;
    }

    public byte[] getContent() {
        return content;
    }

    public String getShaCode() {
        return shaCode;
    }
}