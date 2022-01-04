import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class for compressing and decompressing files with runlengthencoding.
 * <strong>GP2-PA1</strong>
 * 
 * @author Verena Kauth
 * @version 1.0 - 31 Oct 2018
 *
 */
public class RunLengthEncoder {
    /**
     * FileInputStream to read in input file.
     */
    FileInputStream fiStream;
    /**
     * FileOutputStream to write output file.
     */
    FileOutputStream foStream;

    /**
     * Constructor for Encoder-Object.
     * 
     * @param in
     *            - filename of input file
     * @param out
     *            - filename of output file
     * @throws IOException
     *             - if something goes wrong with the files
     */
    RunLengthEncoder(String in, String out) throws IOException {
        // create input stream
        fiStream = new FileInputStream(in);
        // create output Stream
        foStream = new FileOutputStream(out);
    }

    /**
     * method for file compressing by runlengthencoding.
     * 
     * @throws IOException
     *             - if something goes wrong with the files
     */
    void compress() throws IOException {
        // count bytes of input stream
        int all = fiStream.available();
        // create array size of the the input stream
        byte[] input = new byte[all];

        // read content of input file into array
        fiStream.read(input);

        // start compression
        byte byteBefore = input[0];
        int occurence = 1;
        for (int i = 1; i < input.length; i++) {
            byte actualByte = input[i];
            if (actualByte == byteBefore) {
                occurence++;
            } else {
                if (byteBefore == (byte) 64) {
                    for (int y = occurence; y != 0; y--) {
                        foStream.write(byteBefore);
                        foStream.write(byteBefore);

                    }
                    occurence = 1;
                    byteBefore = actualByte;

                } else {

                    if (occurence > 0 && occurence < 4) {
                        for (int y = occurence; y != 0; y--) {
                            foStream.write((byte) byteBefore);

                        }
                        occurence = 1;
                        byteBefore = actualByte;
                    } else {

                        if (occurence > 3) {
                            if (occurence >= 127) {
                                int divided = occurence / 127;
                                int rest = occurence % 127;
                                foStream.write((byte) 64);

                                foStream.write((byte) byteBefore);
                                foStream.write((byte) rest);
                                for (int d = divided; d != 0; d--) {
                                    foStream.write((byte) 64);
                                    foStream.write((byte) byteBefore);
                                    foStream.write((byte) 127);
                                }
                            } else {
                                foStream.write((byte) 64);
                                foStream.write((byte) byteBefore);
                                foStream.write((byte) occurence);
                            }

                        }
                        occurence = 1;
                        byteBefore = actualByte;
                    }
                }
            }
            int endofArray = input.length;

            if (i == endofArray - 1) {
                foStream.write((byte) byteBefore);
            }

        }

        // close opened streams.
        close();

    }

    /**
     * method for decompressing compressed files.
     * 
     * @throws IOException
     *             - if something goes wrong with the files
     */
    void decompress() throws IOException {
        int all = fiStream.available();
        // Array für die Gesamtgroesse des Datenstroms erzeugen
        byte[] input = new byte[all];

        // Array füllen mit Inhalt der Datei
        fiStream.read(input);
        // Output starten
        for (int i = 0; i < input.length; i++) {
            byte byteBefore = input[i];
            if (byteBefore == (byte) 64) {
                byte secondAt = input[i + 1];
                if (secondAt == (byte) 64) {
                    foStream.write((byte) 64);
                    i = i + 1;
                } else {
                    int repeat = input[i + 2];
                    for (int z = 1; z <= repeat; z++) {
                        foStream.write(input[i + 1]);

                    }
                    i = i + 2;
                }
            } else {
                foStream.write(input[i]);

            }

        }
        close();
    }

    /**
     * close used streams.
     * 
     * @throws IOException
     *             - if something goes wrong with the files
     */
    void close() throws IOException {
        fiStream.close();
        foStream.close();
    }

}
