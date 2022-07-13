package zuche;

/*
    用户对象
 */

public class User {
    private String userName;
    private String password;
    private int userNo;


    public User(){

    }


    public User(String userName, String password, int userNo) {
        this.userName = userName;
        this.password = password;
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userNo=" + userNo +
                '}';
    }
}
