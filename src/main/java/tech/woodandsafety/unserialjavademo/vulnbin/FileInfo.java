package tech.woodandsafety.unserialjavademo.vulnbin;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.*;

@ToString
@EqualsAndHashCode
public class FileInfo implements Serializable {

    private String filename;
    private String fileType;

    /**
     *  /!\ Don't call with user controlled data /!\
     * @param filename
     * @throws IOException
     */
    public FileInfo(String filename) throws IOException {
        this.filename = filename;
        initFileInfo(filename);
    }

    private void initFileInfo(String filename) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("/bin/sh");
        pb.command().add("-c");
        pb.command().add("file " + filename);
        Process process = pb.start();
        try(InputStreamReader isr = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            this.fileType = sb.toString();
        }
    }

    public String getFilename() {
        return filename;
    }

    private void writeObject(final ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.writeObject(this.filename);
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.filename = (String) in.readObject();
        initFileInfo(filename);
    }

    public String getFileType() {
        return fileType;
    }

}
