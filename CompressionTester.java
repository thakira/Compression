import java.io.IOException;

/**
 * starting class for file compression. <strong>GP2-PA1</strong>
 * 
 * @author Verena Kauth
 * @version 1.0 - 31 Oct 2018
 *
 */
public class CompressionTester {
    /**
     * main method.
     * 
     * @param args
     *            - args[0] file name of uncompressed input file args[1] file name
     *            of compressed output file
     * @throws IOException
     *             - if something goes wrong with the files
     */
    public static void main(String[] args) throws IOException {
        RunLengthEncoder myCompression = new RunLengthEncoder(args[0], args[1]);
        myCompression.compress();

    }
}
