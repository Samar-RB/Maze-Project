package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;
    private int glob;
    private int repeat;
    private boolean flag;
    public SimpleDecompressorInputStream(InputStream in){
       this.in=in;
        flag=true;
        glob    =0;
    }
    @Override
    public int read() throws IOException {
        if (glob >= 6) {
            if (repeat == 0) {
                repeat = in.read();
                flag = !flag;
            }

            if (flag)
                return 1;
            repeat--;
            return 0;
        }
        else {
            int i = in.read();
            if ((byte) (i - (Byte.MAX_VALUE + 1)) == (byte) 127)
                glob++;
            return i;
        }
    }
}
