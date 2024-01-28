package Observer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class User implements Observer {

    int id;
    private DataInputStream dis;
    private DataOutputStream dos;

    public User(int id,DataInputStream di, DataOutputStream dos){
        this.id = id;
        this.dis = di;
        this.dos = dos;
    }

    @Override
    public void update(String supdate) throws IOException {
        dos.writeUTF(supdate);
        dos.flush();
    }
}