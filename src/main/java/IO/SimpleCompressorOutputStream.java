package IO;

import java.io.*;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;
    private int last;
    private int counter;
    private int glob=0;



    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        {
            // maze array data
            if (glob >= 6) {
                if (b==last) {
                    counter++;
                    //System.out.println(counter/254>=1);
                }
                else {
                    int i = counter;
                    while (i / 254 >= 1) {
                        out.write(255 );
                        i = i - counter / 254;
                        //       System.out.println("255");
                    }
                    if (counter % 254 >= 0) {
                        out.write(0 );
                        //   System.out.println("0");


                    }


                    out.write(0);
//           System.out.println(counter);
                    //       System.out.println("else");
                    last = b;
                    counter = 1;
                }
            }else
            {
                out.write(b);
                if (b-(Byte.MAX_VALUE+1)==1 ){
                    glob++;
                }
            }



        }
    }

}