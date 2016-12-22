package Utils;

/**
 * Created by Paulo on 09-Sep-15.
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

    private String path;
    private boolean append_to_file = false;

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public WriteFile (String file_path) {
        path = file_path;
    }

    public WriteFile (String file_path, boolean append_value) {
        path = file_path;
        append_to_file = append_value;
    }

    public WriteFile (String file_path, boolean append_value, boolean isStream) throws IOException {
        path = file_path;
        append_to_file = append_value;
        fileWriter = new FileWriter (path, append_to_file);
        printWriter = new PrintWriter(fileWriter);
    }

    public void writeToFile (String textLine) throws IOException {
        FileWriter write = new FileWriter (path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", textLine);

        print_line.close();
    }

    public void writeStreamToFile (String textLine, boolean close) throws IOException {
        printWriter.printf("%s" + "%n", textLine);
        if (close)closeFile();
    }

    public void closeFile(){
        printWriter.close();
    }
}