import java.io.IOException;
/**
 * starting class for file decompression.
 * <strong>GP2-PA1</strong>
 * 
 * @author Verena Kauth
 * @version 1.0 - 31 Oct 2018
 *
 */
public class DecompressionTester {
    /**
     * main method.
     * @param args - args[0] file name of compressed input file
     * args[1] file name of uncompressedoutput file
     * @throws IOException - if something goes wrong with the files
     */
    public static void main(String[] args) throws IOException {
        RunLengthEncoder myDecompression = new RunLengthEncoder(args[0], args[1]);
        myDecompression.decompress();

    }

}
