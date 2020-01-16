package dz.trash.TrashBackend.Models;

public class test {
    int id;
    String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public test(int id, String msg) {

        this.id = id;
        this.msg = msg;
    }
}
