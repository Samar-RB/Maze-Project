package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int last = -1;
    private int counter = 0;
    private int glob = 0;
    private boolean inMazeData = false;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
        if (!inMazeData) {
            if ((byte) (b - (Byte.MAX_VALUE + 1)) == 127) {
                glob++;
            }
            if (glob == 6) {
                inMazeData = true;
            }
            return;
        }


        if (last == -1) {
            last = b;
            counter = 1;
        } else if (b == last) {
            counter++;
        } else {
            flushRun();
            last = b;
            counter = 1;
        }
    }

    private void flushRun() throws IOException {
        while (counter >= 254) {
            out.write(last);
            out.write(254);
            counter -= 254;
        }
        if (counter > 0) {
            out.write(last);
            out.write(counter);
        }
    }

    @Override
    public void close() throws IOException {
        if (counter > 0) {
            flushRun();
        }
        out.close();
    }
}
