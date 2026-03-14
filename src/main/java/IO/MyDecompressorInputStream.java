package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private int glob = 0;
    private int repeat = 0;
    private int currentVal = 0;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        if (glob < 6) {
            int b = in.read();
            if ((byte) (b - (Byte.MAX_VALUE + 1)) == 127)
                glob++;
            return b;
        }

        if (repeat == 0) {
            currentVal = in.read();
            repeat = in.read();
        }

        repeat--;
        return currentVal;
    }
}
